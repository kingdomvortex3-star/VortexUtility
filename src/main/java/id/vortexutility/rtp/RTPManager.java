
package id.vortexutility.rtp;

import id.vortexutility.VortexUtility;
import id.vortexutility.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public final class RTPManager {

    private final VortexUtility plugin;

    private final Set<UUID> searching =
            ConcurrentHashMap.newKeySet();

    private final ConcurrentHashMap<UUID, Long> cooldowns =
            new ConcurrentHashMap<>();

    private volatile int maxConcurrent;
    private volatile int cooldownSeconds;

    public RTPManager(VortexUtility plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        maxConcurrent = Math.max(
                1,
                plugin.configs().rtp().getInt(
                        "max-concurrent-searches", 2
                )
        );

        cooldownSeconds = Math.max(
                0,
                plugin.configs().rtp().getInt(
                        "cooldown-seconds", 60
                )
        );
    }

    public void start(
            Player player,
            String worldName,
            int requestedDistance
    ) {
        if (!Bukkit.isPrimaryThread()) {
            Bukkit.getScheduler().runTask(
                    plugin,
                    () -> start(player, worldName, requestedDistance)
            );
            return;
        }

        long now = System.currentTimeMillis();

        long readyAt = cooldowns.getOrDefault(
                player.getUniqueId(), 0L
        );

        if (readyAt > now) {
            player.sendMessage(
                    MessageUtil.message(plugin, "rtp.cooldown")
                            .replace(
                                    "%time%",
                                    String.valueOf(
                                            (readyAt - now + 999) / 1000
                                    )
                            )
            );
            return;
        }

        if (searching.size() >= maxConcurrent
                || !searching.add(player.getUniqueId())) {
            MessageUtil.send(plugin, player, "rtp.busy");
            return;
        }

        World world = Bukkit.getWorld(worldName);

        ConfigurationSection section =
                plugin.configs().rtp().getConfigurationSection(
                        "worlds." + worldName
                );

        if (world == null || section == null
                || !section.getBoolean("enabled", false)) {
            searching.remove(player.getUniqueId());
            MessageUtil.send(plugin, player, "rtp.world-disabled");
            return;
        }

        int centerX = section.getInt("center-x", 0);
        int centerZ = section.getInt("center-z", 0);

        int minX = section.getInt("min-x");
        int maxX = section.getInt("max-x");
        int minZ = section.getInt("min-z");
        int maxZ = section.getInt("max-z");

        int minDistance = Math.max(
                0,
                section.getInt("min-distance", 500)
        );

        int maxDistance = Math.max(
                minDistance,
                Math.min(
                        requestedDistance,
                        section.getInt("max-distance", requestedDistance)
                )
        );

        int attempts = Math.max(
                1,
                Math.min(30, section.getInt("attempts", 12))
        );

        int minPlayerDistance = Math.max(
                0,
                section.getInt("min-player-distance", 100)
        );

        if (minX >= maxX || minZ >= maxZ) {
            searching.remove(player.getUniqueId());

            player.sendMessage(MessageUtil.color(
                    "&cBatas RTP di rtp.yml tidak valid."
            ));

            return;
        }

        MessageUtil.send(plugin, player, "rtp.searching");

        tryCandidate(
                player,
                world,
                centerX,
                centerZ,
                minX,
                maxX,
                minZ,
                maxZ,
                minDistance,
                maxDistance,
                minPlayerDistance,
                attempts
        );
    }

    private void tryCandidate(
            Player player,
            World world,
            int centerX,
            int centerZ,
            int minX,
            int maxX,
            int minZ,
            int maxZ,
            int minDistance,
            int maxDistance,
            int minPlayerDistance,
            int remaining
    ) {
        if (!player.isOnline()) {
            searching.remove(player.getUniqueId());
            return;
        }

        if (remaining <= 0) {
            finishFailed(player);
            return;
        }

        ThreadLocalRandom random =
                ThreadLocalRandom.current();

        double angle = random.nextDouble(0, Math.PI * 2);

        int distance = random.nextInt(
                minDistance,
                Math.max(minDistance + 1, maxDistance + 1)
        );

        int x = centerX + (int) Math.round(
                Math.cos(angle) * distance
        );

        int z = centerZ + (int) Math.round(
                Math.sin(angle) * distance
        );

        if (x < minX || x > maxX || z < minZ || z > maxZ) {
            tryCandidate(
                    player, world,
                    centerX, centerZ,
                    minX, maxX, minZ, maxZ,
                    minDistance, maxDistance,
                    minPlayerDistance,
                    remaining - 1
            );
            return;
        }

        int chunkX = x >> 4;
        int chunkZ = z >> 4;

        // false = jangan meminta pembuatan chunk baru.
        world.getChunkAtAsync(chunkX, chunkZ, false)
                .whenComplete((chunk, error) -> {

                    Bukkit.getScheduler().runTask(plugin, () -> {

                        if (!player.isOnline()) {
                            searching.remove(player.getUniqueId());
                            return;
                        }

                        if (error != null || chunk == null) {
                            retry(
                                    player, world,
                                    centerX, centerZ,
                                    minX, maxX, minZ, maxZ,
                                    minDistance, maxDistance,
                                    minPlayerDistance,
                                    remaining
                            );
                            return;
                        }

                        Location safe = findSafeLocation(world, x, z);

                        if (safe == null
                                || tooCloseToPlayers(
                                        safe,
                                        minPlayerDistance,
                                        player
                                )) {
                            retry(
                                    player, world,
                                    centerX, centerZ,
                                    minX, maxX, minZ, maxZ,
                                    minDistance, maxDistance,
                                    minPlayerDistance,
                                    remaining
                            );
                            return;
                        }

                        player.teleportAsync(safe)
                                .whenComplete((success, teleportError) -> {

                                    Bukkit.getScheduler().runTask(
                                            plugin,
                                            () -> finishTeleport(
                                                    player,
                                                    safe,
                                                    success,
                                                    teleportError
                                            )
                                    );
                                });
                    });
                });
    }

    private void retry(
            Player player,
            World world,
            int centerX,
            int centerZ,
            int minX,
            int maxX,
            int minZ,
            int maxZ,
            int minDistance,
            int maxDistance,
            int minPlayerDistance,
            int remaining
    ) {
        tryCandidate(
                player,
                world,
                centerX,
                centerZ,
                minX,
                maxX,
                minZ,
                maxZ,
                minDistance,
                maxDistance,
                minPlayerDistance,
                remaining - 1
        );
    }

    private Location findSafeLocation(
            World world,
            int x,
            int z
    ) {
        int highest = world.getHighestBlockYAt(x, z);

        if (highest <= world.getMinHeight()
                || highest + 2 >= world.getMaxHeight()) {
            return null;
        }

        Block ground = world.getBlockAt(x, highest, z);
        Block feet = world.getBlockAt(x, highest + 1, z);
        Block head = world.getBlockAt(x, highest + 2, z);

        if (!ground.getType().isSolid()) return null;
        if (isHazard(ground.getType())) return null;

        if (!feet.isPassable() || !head.isPassable()) {
            return null;
        }

        if (isHazard(feet.getType())
                || isHazard(head.getType())) {
            return null;
        }

        return new Location(
                world,
                x + 0.5,
                highest + 1,
                z + 0.5
        );
    }

    private boolean isHazard(Material material) {
        return material == Material.LAVA
                || material == Material.MAGMA_BLOCK
                || material == Material.CACTUS
                || material == Material.FIRE
                || material == Material.SOUL_FIRE
                || material == Material.CAMPFIRE
                || material == Material.SOUL_CAMPFIRE
                || material == Material.POWDER_SNOW;
    }

    private boolean tooCloseToPlayers(
            Location location,
            int minimum,
            Player subject
    ) {
        if (minimum <= 0) return false;

        double squared = (double) minimum * minimum;

        for (Player other : location.getWorld().getPlayers()) {
            if (other.equals(subject)) continue;

            if (other.getLocation().distanceSquared(location)
                    < squared) {
                return true;
            }
        }

        return false;
    }

    private void finishFailed(Player player) {
        searching.remove(player.getUniqueId());

        if (!player.isOnline()) return;

        MessageUtil.send(plugin, player, "rtp.failed");
        MessageUtil.play(plugin, player, "fail");
    }

    private void finishTeleport(
            Player player,
            Location location,
            Boolean success,
            Throwable error
    ) {
        searching.remove(player.getUniqueId());

        if (!player.isOnline()) return;

        if (error != null || !Boolean.TRUE.equals(success)) {
            MessageUtil.send(plugin, player, "rtp.failed");
            return;
        }

        cooldowns.put(
                player.getUniqueId(),
                System.currentTimeMillis()
                        + cooldownSeconds * 1000L
        );

        MessageUtil.send(plugin, player, "rtp.success");

        player.sendMessage(MessageUtil.color(
                "&7X: &f" + location.getBlockX()
                + " &7Y: &f" + location.getBlockY()
                + " &7Z: &f" + location.getBlockZ()
        ));

        MessageUtil.play(plugin, player, "success");
    }
}
