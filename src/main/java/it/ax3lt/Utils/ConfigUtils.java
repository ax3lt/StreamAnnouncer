package it.ax3lt.Utils;

import it.ax3lt.main.StreamAnnouncer;
import org.bukkit.ChatColor;

import java.util.Objects;

public class ConfigUtils {


    public static String getConfigString(String name) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(StreamAnnouncer.getInstance().getConfig().getString(name)));
    }
}
