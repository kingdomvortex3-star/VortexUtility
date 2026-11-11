
package id.vortexutility.command;

import id.vortexutility.VortexUtility;
import id.vortexutility.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class RTPCommand implements CommandExecutor {

    private final VortexUtility plugin;

    public RTPCommand(VortexUtility plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(
                    MessageUtil.message(plugin, "player-only")
            );
            return true;
        }

        if (!player.hasPermission("vortexutility.rtp")) {
            MessageUtil.send(plugin, player, "no-permission");
            return true;
        }

        plugin.rtpMenu().openWorldMenu(player);
        return true;
    }
}
