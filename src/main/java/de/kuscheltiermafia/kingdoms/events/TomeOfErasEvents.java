package de.kuscheltiermafia.kingdoms.events;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.menus.GuiHandler;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TomeOfErasEvents implements Listener {
    @EventHandler
    public void onTomeOfErasUse(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (ItemHandler.checkItemID(e.getItem(), "tome_of_eras")) {
                Player p = e.getPlayer();
                e.setCancelled(true);

                UpdatePlayerStats.updatePlayerStats(p);

                GuiHandler.getGui("tome_of_eras_main").open(p, true);
            }
        }
    }
}
