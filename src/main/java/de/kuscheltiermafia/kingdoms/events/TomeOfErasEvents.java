package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.menus.EnderChest;
import de.kuscheltiermafia.kingdoms.menus.SkillsPage;
import de.kuscheltiermafia.kingdoms.menus.TomeOfEras;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static de.kuscheltiermafia.kingdoms.debug.ItemList.fillItemlist;
import static de.kuscheltiermafia.kingdoms.debug.ItemList.itemListPage;

public class TomeOfErasEvents implements Listener {
    @EventHandler
    public void onTomeOfErasUse(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (ItemHandler.checkItemID(e.getItem(), "tome_of_eras")) {
                Player p = e.getPlayer();
                e.setCancelled(true);

                UpdatePlayerStats.updatePlayerStats(p);

                Inventory toe = TomeOfEras.createTOE();
                TomeOfEras.updateTOE(toe, p);

                p.openInventory(toe);
            }
        }
    }

    @EventHandler
    public void toeInteractEvents(InventoryClickEvent e) {
        if (e.getView().getTitle().contains("Tome of Eras") && e.getWhoClicked() instanceof Player) {
            ItemStack clickedItem = e.getCurrentItem();
            Player p = (Player) e.getWhoClicked();

            if (ItemHandler.checkItemID(clickedItem, "view_skills_button")) {
                e.setCancelled(true);
                Inventory skillList = SkillsPage.createSkillsList(p);
                SkillsPage.updateSkillsList(skillList, p);
                p.openInventory(skillList);
            }

            if (ItemHandler.checkItemID(clickedItem, "back_button_toe_main")) {
                e.setCancelled(true);

                UpdatePlayerStats.updatePlayerStats(p);

                Inventory toe = TomeOfEras.createTOE();
                TomeOfEras.updateTOE(toe, p);

                p.openInventory(toe);
            }

            if (ItemHandler.checkItemID(clickedItem, "unlocked_spells_button")) {
                e.setCancelled(true);
            }

            if (ItemHandler.checkItemID(clickedItem, "view_era_button")) {
                e.setCancelled(true);
            }

            if (ItemHandler.checkItemID(clickedItem, "guide_button")) {
                e.setCancelled(true);
            }

            if (ItemHandler.checkItemID(clickedItem, "ender_chest_button")) {
                e.setCancelled(true);

                p.openInventory(EnderChest.openEnderChest(p));
            }

            if(ItemHandler.checkItemID(clickedItem, "open_item_list")) {
                e.setCancelled(true);

                Inventory itemList = Bukkit.createInventory(null, 9*6, "§4Itemlist");

                itemListPage.put(p, 0);

                fillItemlist(itemList, itemListPage.get(p), p);

                p.openInventory(itemList);
            }
        }
    }

    @EventHandler
    public void onTomeOfErasClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().contains("Tome of Eras")) {
            Player p = (Player) e.getPlayer();

            if(e.getView().getTitle().contains("Ender Chest")) {
                EnderChest.closeEnderChest(e.getInventory(), p);
            }

        }
    }
}
