package de.kuscheltiermafia.kingdoms.items;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.data.PlayerStats;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerUtility {

    private static Map<String, PlayerStats> playerImage = new HashMap<>();

    public static PlayerStats getPlayerImage(Player player) {
        if (!playerImage.containsKey(player.getUniqueId().toString())) {
            PlayerStats image = new PlayerStats();
            playerImage.put(player.getUniqueId().toString(), image);
            return image;
        }
        return playerImage.get(player.getUniqueId().toString());
    }

    public static void setPlayerImage(Player player, PlayerStats image) {
        // Garbage collection
        if (image == null) playerImage.remove(player.getUniqueId().toString());
        else playerImage.put(player.getUniqueId().toString(), image);
    }

    public static String getFolderPath(Player player) {
        return Kingdoms.getPlugin().getDataFolder().getAbsolutePath() + "/player/" + player.getUniqueId() + "/";
    }

}
