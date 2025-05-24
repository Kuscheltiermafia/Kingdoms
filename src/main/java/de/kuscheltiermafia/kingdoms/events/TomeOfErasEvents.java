package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.menus.TomeOfEras;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class TomeOfErasEvents implements Listener {
    @EventHandler
    public void onTomeOfErasUse(PlayerInteractEvent e) {
        if(ItemHandler.checkItemID(e.getItem(), "tome_of_eras")) {
            Player p = e.getPlayer();

            UpdatePlayerStats.updatePlayerStats(p);

            Inventory toe = TomeOfEras.createTOE();
            TomeOfEras.updateTOE(toe, p);

            p.openInventory(toe);
        }
    }
}
