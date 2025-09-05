package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class ItemStatCalculator {

    public static void addMultiplicativeModifier(ItemStack item, Stat stat, String source, double multiplier) {
        HashMap<Stat, HashMap<String, Double>> itemModifierMap = returnItemModifierMap(item, true);
        HashMap<String, Double> statModifiers = itemModifierMap.getOrDefault(stat, new HashMap<>());
        statModifiers.put(source, multiplier);
        itemModifierMap.put(stat, statModifiers);
        putItemModifierMap(item, true, itemModifierMap);
    }

    public static void removeMultiplicativeModifier(ItemStack item, Stat stat, String source) {
        HashMap<Stat, HashMap<String, Double>> itemModifierMap = returnItemModifierMap(item, true);
        HashMap<String, Double> statModifiers = itemModifierMap.getOrDefault(stat, new HashMap<>());
        statModifiers.remove(source);
        itemModifierMap.put(stat, statModifiers);
        putItemModifierMap(item, true, itemModifierMap);
    }

    public static void addAdditiveModifier(ItemStack item, Stat stat, String source, double addition) {
        HashMap<Stat, HashMap<String, Double>> itemModifierMap = returnItemModifierMap(item, false);
        HashMap<String, Double> statModifiers = itemModifierMap.getOrDefault(stat, new HashMap<>());
        statModifiers.put(source, addition);
        itemModifierMap.put(stat, statModifiers);
        putItemModifierMap(item, false, itemModifierMap);
    }

    public static void removeAdditiveModifier(ItemStack item, Stat stat, String source) {
        HashMap<Stat, HashMap<String, Double>> itemModifierMap = returnItemModifierMap(item, false);
        HashMap<String, Double> statModifiers = itemModifierMap.getOrDefault(stat, new HashMap<>());
        statModifiers.remove(source);
        itemModifierMap.put(stat, statModifiers);
        putItemModifierMap(item, false, itemModifierMap);
    }

    private static HashMap<Stat, HashMap<String, Double>> returnItemModifierMap(ItemStack item, boolean multiplicative) {
        HashMap<Stat, HashMap<String, Double>> itemModifierSet;
        if(item.getItemMeta().getPersistentDataContainer().has(ItemHandler.STAT_MULTIPLICATIVE) || item.getItemMeta().getPersistentDataContainer().has(ItemHandler.STAT_ADDITITVE)) {
            if (multiplicative) {
                itemModifierSet = GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.STAT_MULTIPLICATIVE, PersistentDataType.STRING), GsonHandler.ITEM_STAT_MODIFIER_TYPE);
            } else {
                itemModifierSet = GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.STAT_ADDITITVE, PersistentDataType.STRING), GsonHandler.ITEM_STAT_MODIFIER_TYPE);
            }
            return itemModifierSet != null ? itemModifierSet : new HashMap<>();
        }
        return new HashMap<>();
    }

    private static void putItemModifierMap(ItemStack item, boolean multiplicative, HashMap<Stat, HashMap<String, Double>> modifierMap) {
        if(multiplicative) {
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(ItemHandler.STAT_MULTIPLICATIVE, PersistentDataType.STRING, GsonHandler.toJson(modifierMap));
            item.setItemMeta(meta);
        }else{
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(ItemHandler.STAT_ADDITITVE, PersistentDataType.STRING, GsonHandler.toJson(modifierMap));
            item.setItemMeta(meta);
        }
    }

    public static HashMap<Stat, Double> returnFinalStatMap(ItemStack item) {
        HashMap<Stat, Double> finalStatMap = GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.STATS, PersistentDataType.STRING), GsonHandler.STAT_MAP_TYPE);
        if(finalStatMap == null) finalStatMap = new HashMap<>();

        for(Stat stat : returnItemModifierMap(item, false).keySet()) {
            double addition = 0;
            for(double add : returnItemModifierMap(item, false).get(stat).values()) {
                addition += add;
            }
            finalStatMap.put(stat, finalStatMap.getOrDefault(stat, 0.0) + addition);
        }

        for(Stat stat : returnItemModifierMap(item, true).keySet()) {
            double multiplier = 1;
            for(double mult : returnItemModifierMap(item, true).get(stat).values()) {
                multiplier *= mult;
            }
            finalStatMap.put(stat, finalStatMap.getOrDefault(stat, 0.0) * multiplier);
        }
        return finalStatMap;
    }
}
