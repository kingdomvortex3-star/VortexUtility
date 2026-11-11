
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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public final class RTPManager {

    private final VortexUtility plugin;

    private final Set<UUID> searching =
            ConcurrentHashMap.newKeySet();

    private final Map<UUID, Long> cooldowns =
            new HashMap<>();

    /*
     * History disimpan berdasarkan UUID dan world.
     * Semua akses history dilakukan di main thread.
     */
    private final Map<String, Deque<Location>> history =
            new HashMap<>();

    private volatile int maxConcurrent;
    private volatile int cooldownSeconds;
    private volatile int maxAttempts;
    private volatile int historyLimit;
    private volatile int historyDistance;

    private volatile boolean historyEnabled;
    private volatile boolean asyncChunkLoading;

    public RTPManager(VortexUtility plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        ConfigurationSection config =
                plugin.configs().rtp();

        maxConcurrent = Math.max(
                1,
                config.getInt(
                        "max-concurrent-searches", 2
                )
        );

        cooldownSeconds = Math.max(
                0,
                config.getInt(
                        "cooldown-seconds", 60
                )
        );

        maxAttempts = Math.max(
                1,
                Math.min(
                        30,
                        config.getInt(
                                "performance.max-attempts", 12
                        )
                )
        );

        historyEnabled = config.getBoolean(
                "history.enabled", true
        );

        historyLimit = Math.max(
                1,
                Math.min(
                        50,
                        config.getInt(
                                "history.max-locations", 10
                        )
                )
        );

        historyDistance = Math.max(
                0,
                config.getInt(
                        "history.minimum-distance", 500
                )
        );

        asyncChunkLoading = config.getBoolean(
                "performance.async-chunk-loading", true
        );

        // generate-new-chunks selalu diperlakukan false.
        if (config.getBoolean(
                "performance.generate-new-chunks", false)) {

            plugin.getLogger().warning(
                    "RTP chunk generation is disabled internally."
            );
        }
    }

    public void start(
            Player player,
            String worldName,
            int requestedDistance
    ) {
        if (!Bukkit.isPrimaryThread()) {
            Bukkit.getScheduler().runTask(
                    plugin,
                    () -> start(
                            player,
                            worldName,
                            requestedDistance
                    )
            );
            return;
        }

        if (!player.isOnline()) return;

        UUID uuid = player.getUniqueId();

        // Cooldown check.
        long now = System.currentTimeMillis();

        long readyAt = cooldowns.getOrDefault(
                uuid, 0L
        );

        if (readyAt > now) {
            player.sendMessage(
                    MessageUtil.message(
                            plugin,
                            "rtp.cooldown"
                    ).replace(
                            "%time%",
                            String.valueOf(
                                    (readyAt - now + 999) / 1000
                            )
                    )
            );
            return;
        }

        // Concurrent RTP limit.
        if (searching.size() >= maxConcurrent
                || !searching.add(uuid)) {

            MessageUtil.send(
                    plugin,
                    player,
                    "rtp.busy"
            );

            return;
        }

        World world = Bukkit.getWorld(worldName);

        ConfigurationSection section =
                plugin.configs().rtp()
                        .getConfigurationSection(
                                "worlds." + worldName
                        );

        if (world == null
                || section == null
                || !section.getBoolean("enabled", false)) {

            searching.remove(uuid);

            MessageUtil.send(
                    plugin,
                    player,
                    "rtp.world-disabled"
            );

            return;
        }

        int centerX = section.getInt(
                "center-x", 0
        );

        int centerZ = section.getInt(
                "center-z", 0
        );

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
                        Math.max(0, requestedDistance),
                        section.getInt(
                                "max-distance",
                                requestedDistance
                        )
                )
        );

        int attempts = Math.max(
                1,
                Math.min(
                        maxAttempts,
                        section.getInt("attempts", 12)
                )
        );

        int minPlayerDistance = Math.max(
                0,
                section.getInt(
                        "min-player-distance", 100
                )
        );

        if (minX >= maxX
                || minZ >= maxZ
                || minDistance > maxDistance) {

            searching.remove(uuid);

            player.sendMessage(
                    MessageUtil.color(
                            "&cKonfigurasi batas RTP tidak valid."
                    )
            );

            return;
        }

        MessageUtil.send(
                plugin,
                player,
                "rtp.searching"
        );

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

        // Random angle.
        double angle = random.nextDouble(
                0,
                Math.PI * 2
        );

        // Random distance.
        int distance = random.nextInt(
                minDistance,
                maxDistance + 1
        );

        int x = centerX + (int) Math.round(
                Math.cos(angle) * distance
        );

        int z = centerZ + (int) Math.round(
                Math.sin(angle) * distance
        );

        // Pastikan masih dalam batas Chunky.
        if (x < minX || x > maxX
                || z < minZ || z > maxZ) {

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

        // Hindari lokasi yang pernah digunakan.
        if (isRecentlyUsed(world, x, z)) {
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

        int chunkX = x >> 4;
        int chunkZ = z >> 4;

        /*
         * Jika async loading dimatikan,
         * hanya gunakan chunk yang sudah loaded.
         */
        if (!asyncChunkLoading) {

            if (!world.isChunkLoaded(chunkX, chunkZ)) {
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

            checkCandidate(
                    player,
                    world,
                    x,
                    z,
                    centerX,
                    centerZ,
                    minX,
                    maxX,
                    minZ,
                    maxZ,
                    minDistance,
                    maxDistance,
                    minPlayerDistance,
                    remaining
            );

            return;
        }

        /*
         * false = tidak meminta generate chunk baru.
         * Chunk yang belum tersedia akan ditolak.
         */
        world.getChunkAtAsync(
                chunkX,
                chunkZ,
                false
        ).whenComplete((chunk, error) -> {

            Bukkit.getScheduler().runTask(
                    plugin,
                    () -> {

                        if (!player.isOnline()) {
                            searching.remove(
                                    player.getUniqueId()
                            );
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

                        checkCandidate(
                                player,
                                world,
                                x,
                                z,
                                centerX,
                                centerZ,
                                minX,
                                maxX,
                                minZ,
                                maxZ,
                                minDistance,
                                maxDistance,
                                minPlayerDistance,
                                remaining
                        );
                    }
            );
        });
    }

    private void checkCandidate(
            Player player,
            World world,
            int x,
            int z,
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

        Location safe = findSafeLocation(
                world,
                x,
                z
        );

        if (safe == null) {
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

        if (isRecentlyUsed(world, x, z)
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
                .whenComplete((success, error) -> {

                    Bukkit.getScheduler().runTask(
                            plugin,
                            () -> finishTeleport(
                                    player,
                                    safe,
                                    success,
                                    error
                            )
                    );
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

    /*
     * SAFE LOCATION CHECK
     */

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

        Block ground = world.getBlockAt(
                x, highest, z
        );

        Block feet = world.getBlockAt(
                x, highest + 1, z
        );

        Block head = world.getBlockAt(
                x, highest + 2, z
        );

        if (!ground.getType().isSolid()) return null;

        if (isHazard(ground.getType())) return null;

        if (!feet.isPassable()
                || !head.isPassable()) {
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
                || material == Material.POWDER_SNOW
                || material == Material.SWEET_BERRY_BUSH
                || material == Material.WITHER_ROSE;
    }

    /*
     * HISTORY CHECK
     */

    private String historyKey(
            UUID uuid,
            World world
    ) {
        return uuid + ":" + world.getUID();
    }

    private boolean isRecentlyUsed(
            World world,
            int x,
            int z
    ) {
        if (!historyEnabled || historyDistance <= 0) {
            return false;
        }

        // Riwayat dicek untuk player yang sedang RTP.
        // Pemeriksaan dilakukan melalui overload di bawah.
        return false;
    }

    private boolean isRecentlyUsed(
            Player player,
            World world,
            int x,
            int z
    ) {
        if (!historyEnabled || historyDistance <= 0) {
            return false;
        }

        Deque<Location> locations = history.get(
                historyKey(player.getUniqueId(), world)
        );

        if (locations == null || locations.isEmpty()) {
            return false;
        }

        double minimumSquared =
                (double) historyDistance * historyDistance;

        for (Location previous : locations) {
            double dx = x - previous.getX();
            double dz = z - previous.getZ();

            if (dx * dx + dz * dz < minimumSquared) {
                return true;
            }
        }

        return false;
    }

    private void rememberLocation(
            Player player,
            Location location
    ) {
        if (!historyEnabled) return;

        String key = historyKey(
                player.getUniqueId(),
                location.getWorld()
        );

        Deque<Location> locations =
                history.computeIfAbsent(
                        key,
                        ignored -> new ArrayDeque<>()
                );

        locations.addFirst(location.clone());

        while (locations.size() > historyLimit) {
            locations.removeLast();
        }
    }

    /*
     * PLAYER DISTANCE CHECK
     */

    private boolean tooCloseToPlayers(
            Location location,
            int minimum,
            Player subject
    ) {
        if (minimum <= 0) return false;

        double squared =
                (double) minimum * minimum;

        for (Player other :
                location.getWorld().getPlayers()) {

            if (other.equals(subject)) continue;

            if (other.getLocation().distanceSquared(location)
                    < squared) {
                return true;
            }
        }

        return false;
    }

    /*
     * FINISH
     */

    private void finishFailed(Player player) {
        searching.remove(player.getUniqueId());

        if (!player.isOnline()) return;

        MessageUtil.send(
                plugin,
                player,
                "rtp.failed"
        );

        MessageUtil.play(
                plugin,
                player,
                "fail"
        );
    }

    private void finishTeleport(
            Player player,
            Location location,
            Boolean success,
            Throwable error
    ) {
        searching.remove(player.getUniqueId());

        if (!player.isOnline()) return;

        if (error != null
                || !Boolean.TRUE.equals(success)) {

            MessageUtil.send(
                    plugin,
                    player,
                    "rtp.failed"
            );

            return;
        }

        cooldowns.put(
                player.getUniqueId(),
                System.currentTimeMillis()
                        + cooldownSeconds * 1000L
        );

        rememberLocation(player, location);

        MessageUtil.send(
                plugin,
                player,
                "rtp.success"
        );

        player.sendMessage(
                MessageUtil.color(
                        "&7X: &f" + location.getBlockX()
                        + " &7Y: &f" + location.getBlockY()
                        + " &7Z: &f" + location.getBlockZ()
                )
        );

        MessageUtil.play(
                plugin,
                player,
                "success"
        );
    }
}
