package it.ax3lt.PlaceHolderApiExpansion.PlaceHolders;

import it.ax3lt.Handlers.AnnouncerHandler;
import it.ax3lt.Main.StreamAnnouncer;
import it.ax3lt.Utils.ConfigUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StatusPlaceholder extends PlaceholderExpansion {
    String identifier, author, version;

    public StatusPlaceholder(String identifier, String author, String version) {
        this.identifier = identifier;
        this.author = author;
        this.version = version;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (player == null) {
            return "";
        }

        String username = player.getName();

        // Check in config file if the user is linked
        ConfigurationSection linked_users = StreamAnnouncer.getInstance().getConfig().getConfigurationSection("linked_users");
        if (linked_users != null) {
            if (linked_users.contains(username)) {
                // Check if the user is online
                List<String> streams = linked_users.getStringList(username);
                if (streams != null && !streams.isEmpty()) {
                    for (String s : streams) {
                        if (AnnouncerHandler.streams.get(s) != null) {
                            return ConfigUtils.getConfigString("placeholders.live");
                        } else {
                            return ConfigUtils.getConfigString("placeholders.offline");
                        }
                    }
                }
            }
        }
        return ConfigUtils.getConfigString("link-inexistent");
    }

    @Override
    public @NotNull String getIdentifier() {
        return identifier;
    }

    @Override
    public @NotNull String getAuthor() {
        return author;
    }

    @Override
    public @NotNull String getVersion() {
        return version;
    }
}
