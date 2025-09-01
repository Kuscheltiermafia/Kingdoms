package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.debug.ItemList;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.items.Items;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
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
            if (ItemHandler.checkItemID(e.getCurrentItem(), "spacer") || ItemHandler.checkItemID(e.getCurrentItem(), "placeholder")) {
                e.setCancelled(true);
            }else if(e.getCurrentItem().equals(Items.page_up)) {
                e.setCancelled(true);
                p.getInventory().remove(Items.page_up);
                itemListPage.put(p, itemListPage.get(p) + 1);
                ItemList.fillItemlist(e.getInventory(), itemListPage.get(p), p);
            }else if(e.getCurrentItem().equals(Items.page_down)) {
                e.setCancelled(true);
                p.getInventory().remove(Items.page_down);
                itemListPage.put(p, itemListPage.get(p) - 1);
                ItemList.fillItemlist(e.getInventory(), itemListPage.get(p), p);
            }
        }catch (Exception ignored){}
    }
}
