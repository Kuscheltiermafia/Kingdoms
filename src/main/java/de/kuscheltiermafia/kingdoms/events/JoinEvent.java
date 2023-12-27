package de.kuscheltiermafia.kingdoms.events;

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

    }

}
