package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.items.ItemType;
import de.kuscheltiermafia.kingdoms.stats.models.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdatePlayerStats implements Listener {

    public static ArrayList<EquipmentSlot> playerStatSlots = new ArrayList<>();

    public static void initPlayerStatSlots(Player p) {
        playerStatSlots.clear();
        playerStatSlots.add(EquipmentSlot.HAND);
        playerStatSlots.add(EquipmentSlot.OFF_HAND);
        playerStatSlots.add(EquipmentSlot.HEAD);
        playerStatSlots.add(EquipmentSlot.CHEST);
        playerStatSlots.add(EquipmentSlot.LEGS);
        playerStatSlots.add(EquipmentSlot.FEET);
    }

    public static void updatePlayerStats(Player p) {
        initPlayerStatSlots(p);
        PlayerStatModel playerStatModel = Kingdoms.playerStatModelIdentifier.get(p);
        playerStatModel.initFinalStats();

        for (EquipmentSlot slot : playerStatSlots) {
            if(p.getInventory().getItem(slot) != null && p.getInventory().getItem(slot).hasItemMeta() && p.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().has(ItemHandler.STATS)) {
                if(!p.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().has(ItemHandler.FINAL_STATS)) {
                    ItemHandler.updateItem(p.getInventory().getItem(slot));
                }
                ItemStack currentItem = p.getInventory().getItem(slot);
                HashMap<Stat, Double> itemStatSet = GsonHandler.fromJson(currentItem.getItemMeta().getPersistentDataContainer().get(ItemHandler.FINAL_STATS, PersistentDataType.STRING), GsonHandler.STAT_MAP_TYPE);
                for(Stat stat : itemStatSet.keySet()) {
                    if(ItemType.getById(currentItem.getItemMeta().getPersistentDataContainer().get(ItemHandler.TYPE, PersistentDataType.STRING)).isValidSlot(slot)) {
                        playerStatModel.setStat(stat, playerStatModel.getStat(stat, true) + itemStatSet.get(stat), true);
                    }

                }
            }
        }

        playerStatModel.initializeIndirectStats(false);
    }

    @EventHandler
    public void onHotbarSwap(PlayerSwapHandItemsEvent e) {
        Bukkit.getScheduler().runTaskLater(Kingdoms.getPlugin(), () -> {
            updatePlayerStats(e.getPlayer());
        }, 2L);
    }

    @EventHandler
    public void swapHotbar(PlayerItemHeldEvent e) {
        Bukkit.getScheduler().runTaskLater(Kingdoms.getPlugin(), () -> {
            Player p = e.getPlayer();
            updatePlayerStats(p);
        }, 2L);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player && e.getCurrentItem() != null) {
            Bukkit.getScheduler().runTaskLater(Kingdoms.getPlugin(), () -> {
                Player p = (Player) e.getWhoClicked();
                updatePlayerStats(p);
            }, 2L);
        }
    }

    @EventHandler
    public void onArmorChange(PlayerInteractEvent e) {
        if(e.getPlayer() instanceof Player && e.getItem() != null) {
            if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(ItemHandler.TYPE, PersistentDataType.STRING) != null) {
                if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(ItemHandler.TYPE, PersistentDataType.STRING).equals("helmet")) {
                    Player p = e.getPlayer();
                    e.setCancelled(true);
                    ItemStack oldHelmet = p.getInventory().getHelmet();
                    ItemStack newHelmet = p.getInventory().getItemInMainHand();
                    p.getInventory().setHelmet(newHelmet);
                    p.getInventory().setItemInMainHand(oldHelmet);

                    Bukkit.getScheduler().runTaskLater(Kingdoms.getPlugin(), () -> {
                        updatePlayerStats(p);
                    }, 2L);
                }
            }
        }
    }
}
