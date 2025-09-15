package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.stats.models.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        //declare variables
        Player player = event.getPlayer();

        //teleport to lobby
        LobbyTeleport.teleport(player);

        //get player stats
        if (!PlayerUtility.initPlayer(player)) return;

        //checkStats
        if(!Kingdoms.playerStatModelIdentifier.containsKey(player)) {
            Kingdoms.playerStatModelIdentifier.put(player, new PlayerStatModel());
            UpdatePlayerStats.updatePlayerStats(player);
        }else{
            UpdatePlayerStats.updatePlayerStats(player);
        }

    }

}
