package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.debug.ItemList;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static de.kuscheltiermafia.kingdoms.debug.ItemList.itemListPage;

public class InventoryEvents implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            if (ItemHandler.checkItemID(e.getCurrentItem(), "spacer") || ItemHandler.checkItemID(e.getCurrentItem(), "placeholder") || ItemHandler.checkItemID(e.getCurrentItem(), "no_page_up_indicator") || ItemHandler.checkItemID(e.getCurrentItem(), "no_page_down_indicator")) {
                e.setCancelled(true);
            }else if(ItemHandler.checkItemID(e.getCurrentItem(), "page_up_indicator")) {
                e.setCancelled(true);
                ItemHandler.clearItem(p, "page_up_indicator", 1);
                itemListPage.put(p, itemListPage.get(p) + 1);
                ItemList.fillItemlist(e.getInventory(), itemListPage.get(p), p);
            }else if(ItemHandler.checkItemID(e.getCurrentItem(), "page_down_indicator")) {
                e.setCancelled(true);
                ItemHandler.clearItem(p, "page_down_indicator", 1);
                itemListPage.put(p, itemListPage.get(p) - 1);
                ItemList.fillItemlist(e.getInventory(), itemListPage.get(p), p);
            }
        }catch (Exception ignored){}
    }
}
