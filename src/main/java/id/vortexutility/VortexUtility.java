
package id.vortexutility;

import id.vortexutility.command.*;
import id.vortexutility.config.ConfigManager;
import id.vortexutility.ec.ECManager;
import id.vortexutility.gui.RTPMenu;
import id.vortexutility.rtp.RTPManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class VortexUtility extends JavaPlugin {

    private ConfigManager configs;
    private RTPManager rtp;
    private RTPMenu rtpMenu;
    private ECManager ec;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        saveResource("messages.yml", false);
        saveResource("rtp.yml", false);
        saveResource("ec.yml", false);

        configs = new ConfigManager(this);
        configs.loadAll();

        rtp = new RTPManager(this);
        rtpMenu = new RTPMenu(this);
        ec = new ECManager(this);

        register("vortexutility", new UtilityCommand(this));
        register("rtp", new RTPCommand(this));
        register("ec", new ECCommand(this));

        getServer().getPluginManager().registerEvents(rtpMenu, this);
        getServer().getPluginManager().registerEvents(ec, this);

        getLogger().info("VortexUtility enabled!");
        getLogger().info("RTP and Custom Ender Chest loaded!");
    }

    private void register(
            String name,
            org.bukkit.command.CommandExecutor executor
    ) {
        PluginCommand command = getCommand(name);

        if (command == null) {
            getLogger().severe(
                    "Command missing: " + name
            );
            return;
        }

        command.setExecutor(executor);
    }

    public void reloadPlugin() {
        reloadConfig();

        configs.loadAll();
        rtp.reload();
        ec.reload();
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

    public ECManager ec() {
        return ec;
    }

    @Override
    public void onDisable() {
        if (ec != null) {
            ec.saveAllOpenInventories();
        }

        getLogger().info("VortexUtility disabled!");
    }
}
