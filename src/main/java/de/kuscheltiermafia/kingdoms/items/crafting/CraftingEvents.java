package de.kuscheltiermafia.kingdoms.items.crafting;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class CraftingEvents implements Listener {

    public static HashMap<Player, CraftingHandler> inCrafting = new HashMap<>();

    @EventHandler
    public void onCraftingTable(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.CRAFTING_TABLE)) {
            e.setCancelled(true);
            CraftingHandler craftingTable = new CraftingHandler(e.getPlayer());
            inCrafting.put(e.getPlayer(), craftingTable);
        }
    }

    @EventHandler
    public void onCraftingClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().contains("Crafting Table")) {
            if (inCrafting.containsKey(e.getPlayer())) {
                inCrafting.get(e.getPlayer()).closeInventory();
                inCrafting.remove(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void onCrafting(InventoryClickEvent e) {
        if(e.getView().getTitle().contains("Crafting Table")) {
            if(inCrafting.containsKey(e.getWhoClicked())) {
                if(e.getCurrentItem() != null && e.getSlot() == 23 && !e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(ItemHandler.ID, PersistentDataType.STRING).equals("spacer")) {
                    inCrafting.get(e.getWhoClicked()).consumeIngredients();
                }
                Bukkit.getScheduler().runTaskLater(Kingdoms.getPlugin(), () -> {
                    inCrafting.get(e.getWhoClicked()).updateOutput();
                }, 2L);
            }
        }
    }
}
