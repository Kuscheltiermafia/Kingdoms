package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.stats.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import de.kuscheltiermafia.kingdoms.data.PlayerStats;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        //declare variables
        Player player = event.getPlayer();

        //teleport to lobby
        LobbyTeleport.teleport(player);

        //get player stats
        PlayerStats image = new PlayerStats();
        File file = new File(PlayerUtility.getFolderPath(player) + "stats.yml");

        if (file.exists()) {
            image.loadStats(file);
        }

        PlayerUtility.setPlayerImage(player, image);

        //checkStats
        if(!Kingdoms.playerStatModelIdentifier.containsKey(player)) {
            Kingdoms.playerStatModelIdentifier.put(player, new PlayerStatModel(20f, 0f, 10f, 2f, 0.05f, 1f, 4f, 4f, 0.1f, 0f, 1f, 0f, 0f));
            UpdatePlayerStats.updatePlayerStats(player);
        }else{
            UpdatePlayerStats.updatePlayerStats(player);
        }

    }

}
