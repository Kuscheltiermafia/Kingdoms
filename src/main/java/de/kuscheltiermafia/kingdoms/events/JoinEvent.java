package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.Main;
import de.kuscheltiermafia.kingdoms.stats.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        //declare variables
        Player p = e.getPlayer();

        //teleport to lobby
        LobbyTeleport.teleport(p);

        //checkStats
        if(!Main.playerStatModelIdentifier.containsKey(p)) {
            Main.playerStatModelIdentifier.put(p, new PlayerStatModel(20f, 0f, 10f, 2f, 0.05f, 1f, 4f, 4f, 0.1f, 0f, 1f, 0f, 0f));
            UpdatePlayerStats.updatePlayerStats(p);
        }else{
            UpdatePlayerStats.updatePlayerStats(p);
        }

    }

}
