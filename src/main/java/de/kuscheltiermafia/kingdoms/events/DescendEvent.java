package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

import static de.kuscheltiermafia.kingdoms.items.ItemHandler.DESCEND_ITEM;

public class DescendEvent implements Listener {

    @EventHandler
    public void onUseAscendItem(PlayerInteractEvent e){

        //declare variables
        Player p = e.getPlayer();

        if(Objects.equals(p.getItemInUse(), DESCEND_ITEM)){

            //teleport
            LobbyTeleport.teleport(p);

        }

    }

}
