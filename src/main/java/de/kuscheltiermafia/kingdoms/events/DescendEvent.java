package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class DescendEvent implements Listener {

    @EventHandler
    public void onUseAscendItem(PlayerInteractEvent e){

        //declare variables
        Player p = e.getPlayer();

        if(ItemHandler.checkItemID(p.getItemInUse(), "descend_item")){

            //teleport
            LobbyTeleport.teleport(p);

        }

    }

}
