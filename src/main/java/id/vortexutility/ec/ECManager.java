
package id.vortexutility.ec;

import id.vortexutility.VortexUtility;
import id.vortexutility.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ECManager implements Listener {

    private final VortexUtility plugin;
    private final File dataFolder;

    private YamlConfiguration config;

    public ECManager(VortexUtility plugin) {
        this.plugin = plugin;
        this.dataFolder = new File(
                plugin.getDataFolder(),
                "data/enderchests"
        );

        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        reload();
    }

    public void reload() {
        File file = new File(plugin.getDataFolder(), "ec.yml");

        if (!file.exists()) {
            plugin.saveResource("ec.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public int getCapacity(Player player) {
        ConfigurationSection tiers =
                config.getConfigurationSection("tiers");

        if (tiers == null) return 9;

        int highest = 0;
        int capacity = 9;

        for (String key : tiers.getKeys(false)) {
            ConfigurationSection tier =
                    tiers.getConfigurationSection(key);

            if (tier == null) continue;

            String permission =
                    tier.getString("permission", "");

            int slots = tier.getInt("slots", 9);

            int level;

            try {
                level = Integer.parseInt(key);
            } catch (NumberFormatException exception) {
                continue;
            }

            if (permission.isBlank()
                    || slots < 9
                    || slots > 54
                    || slots % 9 != 0) {
                continue;
            }

            if (player.hasPermission(permission)
                    && level > highest) {
                highest = level;
                capacity = slots;
            }
        }

        return capacity;
    }

    public void open(Player player) {
        if (!config.getBoolean("settings.enabled", true)) {
            send(player, "disabled");
            return;
        }

        if (!player.hasPermission("vortexutility.ec")) {
            send(player, "no-permission");
            return;
        }

        int capacity = getCapacity(player);

        ECMenuHolder holder =
                new ECMenuHolder(player.getUniqueId());

        String title = color(
                config.getString(
                        "settings.title",
                        "&8Vortex Ender Chest"
                )
        );

        Inventory inventory = Bukkit.createInventory(
                holder,
                capacity,
                title
        );

        holder.setInventory(inventory);

        ItemStack[] stored = loadItems(player.getUniqueId());

        for (int i = 0; i < capacity; i++) {
            inventory.setItem(i, stored[i]);
        }

        player.openInventory(inventory);
        send(player, "opened");
    }

    private ItemStack[] loadItems(UUID uuid) {
        ItemStack[] items = new ItemStack[54];

        File file = getFile(uuid);

        if (!file.exists()) {
            return items;
        }

        YamlConfiguration data =
                YamlConfiguration.loadConfiguration(file);

        List<?> list = data.getList("items");

        if (list == null) return items;

        for (int i = 0; i < Math.min(54, list.size()); i++) {
            Object object = list.get(i);

            if (object instanceof ItemStack item) {
                items[i] = item;
            }
        }

        return items;
    }

    public void save(ECMenuHolder holder, Inventory inventory) {
        UUID uuid = holder.getOwner();

        // Pertahankan seluruh item lama, termasuk slot terkunci.
        ItemStack[] stored = loadItems(uuid);
        ItemStack[] visible = inventory.getContents();

        for (int i = 0; i < visible.length && i < 54; i++) {
            stored[i] = visible[i];
        }

        YamlConfiguration data = new YamlConfiguration();

        List<ItemStack> list = new ArrayList<>();

        for (ItemStack item : stored) {
            list.add(item);
        }

        data.set("items", list);

        try {
            data.save(getFile(uuid));
        } catch (IOException exception) {
            plugin.getLogger().severe(
                    "Gagal menyimpan EC " + uuid
                            + ": " + exception.getMessage()
            );
        }
    }

    private File getFile(UUID uuid) {
        return new File(dataFolder, uuid + ".yml");
    }

    private void send(Player player, String key) {
        String prefix = config.getString(
                "messages." + key,
                "&cMessage not found."
        );

        player.sendMessage(color(
                plugin.getConfig().getString(
                        "settings.prefix",
                        "&8[&bVortexUtility&8] "
                ) + prefix
        ));
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes(
                '&',
                text
        );
    }

    private ECMenuHolder getHolder(Inventory inventory) {
        if (inventory == null) return null;

        if (inventory.getHolder() instanceof ECMenuHolder holder
                && holder.getOwner() != null) {
            return holder;
        }

        return null;
    }

    private void scheduleSave(ECMenuHolder holder, Inventory inventory) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            if (!plugin.isEnabled()) return;
            save(holder, inventory);
        });
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ECMenuHolder holder =
                getHolder(event.getView().getTopInventory());

        if (holder == null) return;

        scheduleSave(holder, event.getView().getTopInventory());
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        ECMenuHolder holder =
                getHolder(event.getView().getTopInventory());

        if (holder == null) return;

        scheduleSave(holder, event.getView().getTopInventory());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        ECMenuHolder holder = getHolder(event.getInventory());

        if (holder == null) return;

        save(holder, event.getInventory());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Inventory inventory =
                event.getPlayer().getOpenInventory().getTopInventory();

        ECMenuHolder holder = getHolder(inventory);

        if (holder != null) {
            save(holder, inventory);
        }
    }

    public void saveAllOpenInventories() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Inventory inventory =
                    player.getOpenInventory().getTopInventory();

            ECMenuHolder holder = getHolder(inventory);

            if (holder != null) {
                save(holder, inventory);
            }
        }
    }
}
