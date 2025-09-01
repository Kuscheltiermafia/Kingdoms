package de.kuscheltiermafia.kingdoms.data;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import org.bukkit.entity.Player;

import java.io.File;
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
        String path = Kingdoms.getPlugin().getDataFolder().getAbsolutePath() + "/player/" + player.getUniqueId() + "/";
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        return path;
    }


    public static boolean initPlayer(Player player) {
        try {
            boolean success = PlayerStats.setupConfig(player);
            if (!success) {
                player.kickPlayer("Could not load your player data. Please try again. If this problem persists, please contact an administrator.");
                Kingdoms.getPlugin().getLogger().warning("Could not load player data for " + player.getName());
                return false;
            }
        }catch (Exception e) {
            Kingdoms.getPlugin().getLogger().warning("Could not load player data for " + player.getName());
            return false;
        }
        return true;
    }

}
