
package id.vortexutility.command;

import id.vortexutility.VortexUtility;
import id.vortexutility.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class UtilityCommand implements CommandExecutor {

    private final VortexUtility plugin;

    public UtilityCommand(VortexUtility plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if (!sender.hasPermission("vortexutility.admin")) {
            sender.sendMessage(
                    MessageUtil.message(plugin, "no-permission")
            );
            return true;
        }

        if (args.length == 1 &&
                args[0].equalsIgnoreCase("reload")) {

            plugin.reloadPlugin();

            sender.sendMessage(
                    MessageUtil.message(plugin, "reloaded")
            );

            return true;
        }

        sender.sendMessage(MessageUtil.color(
                "&bVortexUtility &7Commands:\n" +
                "&f/vu reload &7- Reload configuration"
        ));

        return true;
    }
}
