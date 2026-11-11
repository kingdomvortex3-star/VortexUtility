
package id.vortexutility.util;

import id.vortexutility.VortexUtility;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public final class MessageUtil {

    private MessageUtil() {}

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String message(VortexUtility plugin, String path) {
        String prefix = plugin.getConfig().getString(
                "settings.prefix", "&8[&bVortexUtility&8] ");

        String body = plugin.configs().messages()
                .getString(path, "&cMessage not found: " + path);

        return color(prefix + body);
    }

    public static void send(VortexUtility plugin,
                            Player player,
                            String path) {
        player.sendMessage(message(plugin, path));
    }

    public static void play(VortexUtility plugin,
                            Player player,
                            String path) {
        if (!plugin.getConfig().getBoolean("sound.enabled", true)) {
            return;
        }

        String name = plugin.getConfig().getString("sound." + path);

        if (name == null || name.isBlank()) return;

        try {
            player.playSound(
                    player.getLocation(),
                    Sound.valueOf(name.toUpperCase()),
                    1.0f,
                    1.0f
            );
        } catch (IllegalArgumentException exception) {
            plugin.getLogger().warning("Invalid sound: " + name);
        }
    }
}
