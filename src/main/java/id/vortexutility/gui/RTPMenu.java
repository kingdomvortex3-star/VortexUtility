
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
            "§8§lVORTEX §8» §bPILIH DUNIA";

    private static final String DISTANCE_TITLE =
            "§8§lVORTEX §8» §bPILIH JARAK";

    private static final String META_WORLD =
            "vortexutility_rtp_world";

    private static final int[] WORLD_SLOTS = {
            11, 13, 15
    };

    private static final int[] DISTANCE_SLOTS = {
            10, 12, 14, 16
    };

    private static final int[] DISTANCES = {
            500, 1000, 2500, 5000
    };

    private static final Material[] WORLD_ICONS = {
            Material.GRASS_BLOCK,
            Material.NETHERRACK,
            Material.END_STONE
    };

    public RTPMenu(VortexUtility plugin) {
        this.plugin = plugin;
    }

    /*
     * WORLD MENU
     */

    public void openWorldMenu(Player player) {

        List<String> worlds = getEnabledWorlds();

        if (worlds.isEmpty()) {
            MessageUtil.send(
                    plugin,
                    player,
                    "rtp.no-worlds"
            );
            return;
        }

        Inventory inventory = Bukkit.createInventory(
                null,
                27,
                WORLD_TITLE
        );

        fill(inventory);

        for (int i = 0; i < worlds.size() && i < 3; i++) {

            String world = worlds.get(i);

            inventory.setItem(
                    WORLD_SLOTS[i],
                    createItem(
                            WORLD_ICONS[i],
                            getWorldDisplayName(world),
                            getWorldLore(world)
                    )
            );
        }

        inventory.setItem(
                4,
                createItem(
                        Material.COMPASS,
                        "&b&lRANDOM TELEPORT",
                        List.of(
                                "&8ᴠᴏʀᴛᴇx ᴛʀᴀᴠᴇʟ sʏsᴛᴇᴍ",
                                "",
                                "&7Pilih dimensi untuk memulai",
                                "&7petualangan dan menemukan",
                                "&7tempat baru.",
                                "",
                                "&a✦ &fSafe Location",
                                "&a✦ &fAnti Duplicate Location",
                                "",
                                "&ePilih dunia di bawah!"
                        )
                )
        );

        player.openInventory(inventory);

        MessageUtil.play(
                plugin,
                player,
                "open"
        );
    }

    /*
     * DISTANCE MENU
     */

    public void openDistanceMenu(
            Player player,
            String world
    ) {
        Inventory inventory = Bukkit.createInventory(
                null,
                27,
                DISTANCE_TITLE
        );

        fill(inventory);

        String displayName = getWorldDisplayName(world);

        for (int i = 0; i < DISTANCES.length; i++) {

            int distance = DISTANCES[i];

            inventory.setItem(
                    DISTANCE_SLOTS[i],
                    createItem(
                            getDistanceIcon(distance),
                            getDistanceName(distance),
                            List.of(
                                    "&8ᴛᴇʟᴇᴘᴏʀᴛ ᴅɪsᴛᴀɴᴄᴇ",
                                    "",
                                    "&7Dimensi: " + displayName,
                                    "&7Jarak maksimum:",
                                    "&b" + formatNumber(distance)
                                            + " Blocks",
                                    "",
                                    "&7Tujuan teleportasi akan",
                                    "&7dipilih secara acak.",
                                    "",
                                    "&a✦ &fSafe Location",
                                    "&a✦ &fChunk Pregen Only",
                                    "",
                                    "&e➥ Klik untuk teleport!"
                            )
                    )
            );
        }

        inventory.setItem(
                4,
                createItem(
                        Material.ENDER_EYE,
                        "&b&lPILIH JARAK RTP",
                        List.of(
                                "&8ᴠᴏʀᴛᴇx ᴛʀᴀᴠᴇʟ sʏsᴛᴇᴍ",
                                "",
                                "&7World: " + displayName,
                                "&7Pilih jarak perjalananmu.",
                                "",
                                "&7Teleportasi menggunakan",
                                "&7lokasi aman yang tersedia.",
                                "",
                                "&eJangan bergerak saat countdown!"
                        )
                )
        );

        inventory.setItem(
                22,
                createItem(
                        Material.ARROW,
                        "&c&lKEMBALI",
                        List.of(
                                "&8ᴍᴇɴᴜ ɴᴀᴠɪɢᴀᴛɪᴏɴ",
                                "",
                                "&7Kembali ke pemilihan dunia.",
                                "",
                                "&e➥ Klik untuk kembali"
                        )
                )
        );

        player.setMetadata(
                META_WORLD,
                new FixedMetadataValue(
                        plugin,
                        world
                )
        );

        player.openInventory(inventory);

        MessageUtil.play(
                plugin,
                player,
                "open"
        );
    }

    /*
     * WORLD INFORMATION
     */

    private String getWorldDisplayName(String world) {

        return switch (world.toLowerCase()) {

            case "world" ->
                    "&a&lOVERWORLD";

            case "world_nether" ->
                    "&c&lTHE NETHER";

            case "world_the_end" ->
                    "&d&lTHE END";

            default ->
                    "&b&l" + world.toUpperCase();
        };
    }

    private List<String> getWorldLore(String world) {

        List<String> lore = new ArrayList<>();

        lore.add("&8ᴅɪᴍᴇɴsɪ ᴘᴇʀᴊᴀʟᴀɴᴀɴ");
        lore.add("");

        switch (world.toLowerCase()) {

            case "world" -> {
                lore.add("&7Jelajahi dunia utama");
                lore.add("&7dan temukan tempat baru.");
            }

            case "world_nether" -> {
                lore.add("&7Masuki dimensi Nether");
                lore.add("&7yang penuh bahaya.");
            }

            case "world_the_end" -> {
                lore.add("&7Jelajahi dimensi End");
                lore.add("&7dan wilayah misterius.");
            }

            default -> {
                lore.add("&7Jelajahi dimensi ini");
                lore.add("&7dan temukan tempat baru.");
            }
        }

        lore.add("");
        lore.add("&a✦ &fSafe Location");
        lore.add("&a✦ &fRandom Destination");
        lore.add("");
        lore.add("&e➥ Klik untuk memilih");

        return lore;
    }

    /*
     * DISTANCE INFORMATION
     */

    private Material getDistanceIcon(int distance) {

        if (distance <= 500) {
            return Material.ENDER_PEARL;
        }

        if (distance <= 1000) {
            return Material.ENDER_EYE;
        }

        if (distance <= 2500) {
            return Material.COMPASS;
        }

        return Material.NETHER_STAR;
    }

    private String getDistanceName(int distance) {

        if (distance <= 500) {
            return "&a&lSHORT DISTANCE";
        }

        if (distance <= 1000) {
            return "&b&lMEDIUM DISTANCE";
        }

        if (distance <= 2500) {
            return "&6&lLONG DISTANCE";
        }

        return "&d&lEXTREME DISTANCE";
    }

    private String formatNumber(int number) {
        return String.format(
                java.util.Locale.US,
                "%,d",
                number
        );
    }

    /*
     * ENABLED WORLDS
     */

    private List<String> getEnabledWorlds() {

        List<String> result = new ArrayList<>();

        ConfigurationSection section =
                plugin.configs().rtp()
                        .getConfigurationSection("worlds");

        if (section == null) return result;

        for (String name : section.getKeys(false)) {

            if (section.getBoolean(
                    name + ".enabled", false
            ) && Bukkit.getWorld(name) != null) {

                result.add(name);
            }
        }

        return result;
    }

    /*
     * INVENTORY CLICK
     */

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked()
                instanceof Player player)) {
            return;
        }

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

            for (int i = 0;
                 i < worlds.size() && i < 3;
                 i++) {

                if (slot == WORLD_SLOTS[i]) {

                    openDistanceMenu(
                            player,
                            worlds.get(i)
                    );

                    return;
                }
            }

            return;
        }

        if (slot == 22) {

            player.removeMetadata(
                    META_WORLD,
                    plugin
            );

            openWorldMenu(player);
            return;
        }

        for (int i = 0;
             i < DISTANCE_SLOTS.length;
             i++) {

            if (slot != DISTANCE_SLOTS[i]) {
                continue;
            }

            if (!player.hasMetadata(META_WORLD)) {
                return;
            }

            String world = player.getMetadata(META_WORLD)
                    .stream()
                    .filter(meta ->
                            meta.getOwningPlugin() == plugin
                    )
                    .findFirst()
                    .map(meta -> meta.asString())
                    .orElse(null);

            if (world == null) return;

            player.removeMetadata(
                    META_WORLD,
                    plugin
            );

            player.closeInventory();

            plugin.rtp().start(
                    player,
                    world,
                    DISTANCES[i]
            );

            return;
        }
    }

    /*
     * FILLER
     */

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
                        "gui.filler.name",
                        " "
                ),
                List.of()
        );

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, filler.clone());
        }
    }

    /*
     * ITEM CREATOR
     */

    private ItemStack createItem(
            Material material,
            String name,
            List<String> lore
    ) {
        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();

        if (meta != null) {

            meta.setDisplayName(
                    MessageUtil.color(name)
            );

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
