
package id.vortexutility;

import id.vortexutility.command.*;
import id.vortexutility.config.ConfigManager;
import id.vortexutility.gui.RTPMenu;
import id.vortexutility.rtp.RTPManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class VortexUtility extends JavaPlugin {

    private ConfigManager configs;
    private RTPManager rtp;
    private RTPMenu rtpMenu;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("messages.yml", false);
        saveResource("rtp.yml", false);

        configs = new ConfigManager(this);
        configs.loadAll();

        rtp = new RTPManager(this);
        rtpMenu = new RTPMenu(this);

        register("vortexutility", new UtilityCommand(this));
        register("rtp", new RTPCommand(this));
        register("ec", new ECCommand(this));

        getServer().getPluginManager().registerEvents(rtpMenu, this);

        getLogger().info("VortexUtility enabled!");
    }

    private void register(String name,
                          org.bukkit.command.CommandExecutor executor) {
        PluginCommand command = getCommand(name);

        if (command == null) {
            getLogger().severe("Command missing: " + name);
            return;
        }

        command.setExecutor(executor);
    }

    public void reloadPlugin() {
        reloadConfig();
        configs.loadAll();
        rtp.reload();
    }

    public ConfigManager configs() {
        return configs;
    }

    public RTPManager rtp() {
        return rtp;
    }

    public RTPMenu rtpMenu() {
        return rtpMenu;
    }

    @Override
    public void onDisable() {
        getLogger().info("VortexUtility disabled!");
    }
}
