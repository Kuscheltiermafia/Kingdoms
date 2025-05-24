package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.data.PlayerStats;
import de.kuscheltiermafia.kingdoms.items.PlayerUtility;
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

    }

}
