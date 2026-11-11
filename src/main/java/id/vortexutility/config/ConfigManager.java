
package id.vortexutility.config;

import id.vortexutility.VortexUtility;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public final class ConfigManager {

    private final VortexUtility plugin;

    private FileConfiguration messages;
    private FileConfiguration rtp;

    public ConfigManager(VortexUtility plugin) {
        this.plugin = plugin;
    }

    public void loadAll() {
        messages = load("messages.yml");
        rtp = load("rtp.yml");
    }

    private FileConfiguration load(String name) {
        File file = new File(plugin.getDataFolder(), name);

        if (!file.exists()) {
            plugin.saveResource(name, false);
        }

        return YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration messages() {
        return messages;
    }

    public FileConfiguration rtp() {
        return rtp;
    }
}
