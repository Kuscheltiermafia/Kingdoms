package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdatePlayerStats {

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
        PlayerStatModel playerStatModel = Kingdoms.playerStatModelIdentifier.get(p);
        initPlayerStatSlots(p);
        playerStatModel.resetStats();

        for (EquipmentSlot slot : playerStatSlots) {
            if(p.getInventory().getItem(slot) != null && p.getInventory().getItem(slot).hasItemMeta() && p.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().has(ItemHandler.STATS)) {
                if(!p.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().has(ItemHandler.FINAL_STATS)) {
                    ItemHandler.updateItem(p.getInventory().getItem(slot));
                }
                ItemStack currentItem = p.getInventory().getItem(slot);
                HashMap<Stat, Double> itemStatSet = GsonHandler.fromJson(currentItem.getItemMeta().getPersistentDataContainer().get(ItemHandler.FINAL_STATS, PersistentDataType.STRING), GsonHandler.STAT_MAP_TYPE);
                for(Stat stat : itemStatSet.keySet()) {
                    if(itemStatSet.get(stat) != null) {
                        playerStatModel.setStat(stat, playerStatModel.getStat(stat) + itemStatSet.get(stat));
                    }
                }
            }
        }
    }

}
