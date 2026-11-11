
package id.vortexutility.gui;

import id.vortexutility.VortexUtility;
import id.vortexutility.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public final class RTPMenu implements Listener {

    private final VortexUtility plugin;

    private static final String WORLD_TITLE =
            "§8RTP » Pilih World";

    private static final String DISTANCE_TITLE =
            "§8RTP » Pilih Jarak";

    private static final String META_WORLD =
            "vortexutility_rtp_world";

    public RTPMenu(VortexUtility plugin) {
        this.plugin = plugin;
    }

    public void openWorldMenu(Player player) {
        ConfigurationSection section =
                plugin.configs().rtp()
                        .getConfigurationSection("worlds");

        if (section == null) {
            MessageUtil.send(plugin, player, "rtp.no-worlds");
            return;
        }

        List<String> worlds = getEnabledWorlds();

        if (worlds.isEmpty()) {
            MessageUtil.send(plugin, player, "rtp.no-worlds");
            return;
        }

        Inventory inventory =
                Bukkit.createInventory(null, 27, WORLD_TITLE);

        fill(inventory);

        Material[] icons = {
                Material.GRASS_BLOCK,
                Material.NETHERRACK,
                Material.END_STONE
        };

        int[] slots = {11, 13, 15};

        for (int i = 0; i < worlds.size() && i < 3; i++) {
            inventory.setItem(
                    slots[i],
                    createItem(
                            icons[i],
                            "&b" + worlds.get(i),
                            List.of("&7Klik untuk memilih world")
                    )
            );
        }

        player.openInventory(inventory);
        MessageUtil.play(plugin, player, "open");
    }

    public void openDistanceMenu(Player player, String world) {
        Inventory inventory =
                Bukkit.createInventory(null, 27, DISTANCE_TITLE);

        fill(inventory);

        int[] distances = {500, 1000, 2500, 5000};
        int[] slots = {10, 12, 14, 16};

        for (int i = 0; i < distances.length; i++) {
            inventory.setItem(
                    slots[i],
                    createItem(
                            Material.ENDER_PEARL,
                            "&b" + distances[i] + " Blocks",
                            List.of(
                                    "&7World: &f" + world,
                                    "&eKlik untuk RTP"
                            )
                    )
            );
        }

        inventory.setItem(
                22,
                createItem(
                        Material.ARROW,
                        "&cKembali",
                        List.of("&7Pilih world lain")
                )
        );

        player.setMetadata(
                META_WORLD,
                new FixedMetadataValue(plugin, world)
        );

        player.openInventory(inventory);
    }

    private List<String> getEnabledWorlds() {
        List<String> result = new ArrayList<>();

        ConfigurationSection section =
                plugin.configs().rtp()
                        .getConfigurationSection("worlds");

        if (section == null) return result;

        for (String name : section.getKeys(false)) {
            if (section.getBoolean(name + ".enabled", false)
                    && Bukkit.getWorld(name) != null) {
                result.add(name);
            }
        }

        return result;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        String title = event.getView().getTitle();

        if (!title.equals(WORLD_TITLE)
                && !title.equals(DISTANCE_TITLE)) {
            return;
        }

        event.setCancelled(true);

        if (event.getClickedInventory() == null
                || event.getClickedInventory()
                != event.getView().getTopInventory()) {
            return;
        }

        int slot = event.getRawSlot();

        if (title.equals(WORLD_TITLE)) {
            List<String> worlds = getEnabledWorlds();
            int[] slots = {11, 13, 15};

            for (int i = 0; i < worlds.size() && i < 3; i++) {
                if (slot == slots[i]) {
                    openDistanceMenu(player, worlds.get(i));
                    return;
                }
            }

            return;
        }

        if (slot == 22) {
            openWorldMenu(player);
            return;
        }

        int[] slots = {10, 12, 14, 16};
        int[] distances = {500, 1000, 2500, 5000};

        for (int i = 0; i < slots.length; i++) {
            if (slot != slots[i]) continue;

            if (!player.hasMetadata(META_WORLD)) return;

            String world = player.getMetadata(META_WORLD)
                    .stream()
                    .filter(meta -> meta.getOwningPlugin() == plugin)
                    .findFirst()
                    .map(meta -> meta.asString())
                    .orElse(null);

            if (world == null) return;

            player.removeMetadata(META_WORLD, plugin);
            player.closeInventory();

            plugin.rtp().start(player, world, distances[i]);
            return;
        }
    }

    private void fill(Inventory inventory) {
        if (!plugin.getConfig().getBoolean(
                "gui.fill-empty-slots", true)) {
            return;
        }

        Material material = Material.matchMaterial(
                plugin.getConfig().getString(
                        "gui.filler.material",
                        "BLACK_STAINED_GLASS_PANE"
                )
        );

        if (material == null) {
            material = Material.BLACK_STAINED_GLASS_PANE;
        }

        ItemStack filler = createItem(
                material,
                plugin.getConfig().getString(
                        "gui.filler.name", " "
                ),
                List.of()
        );

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, filler);
        }
    }

    private ItemStack createItem(
            Material material,
            String name,
            List<String> lore
    ) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(MessageUtil.color(name));

            meta.setLore(
                    lore.stream()
                            .map(MessageUtil::color)
                            .toList()
            );

            item.setItemMeta(meta);
        }

        return item;
    }
}
