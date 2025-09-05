package de.kuscheltiermafia.kingdoms.items.itemEnchants;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class EnchantmentHandler {

    public static HashMap<String, ItemEnchant> enchants = new HashMap<>();

    public static void registerEnchantments() {
        enchants.put("sharpness", new Sharpness());
        enchants.put("torment", new Torment());
    }

    public static boolean applyEnchantment(ItemStack item, ItemEnchant enchant, int level) {
        HashMap<String, Integer> itemEnchantments;
        if(item.getItemMeta().getPersistentDataContainer().has(ItemHandler.ENCHANTMENTS)) {
            itemEnchantments = GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ENCHANTMENTS, PersistentDataType.STRING), GsonHandler.ENCHANT_MAP_TYPE);
        }else{
            itemEnchantments = new HashMap<>();
        }
        if(level <= enchant.getMaxLevel()) {
            if(enchant.canApplyToItem(item)) {
                itemEnchantments.put(enchant.getEnchantmentID(), level);

                ItemMeta meta = item.getItemMeta();
                meta.getPersistentDataContainer().set(ItemHandler.ENCHANTMENTS, PersistentDataType.STRING, GsonHandler.toJson(itemEnchantments));
                item.setItemMeta(meta);

                enchant.onApply(item, level);

                return true;
            }else {return false;}
        }else {return false;}
    }

    public static void removeEnchantment(ItemStack item, ItemEnchant enchant) {
        if(item.getItemMeta().getPersistentDataContainer().has(ItemHandler.ENCHANTMENTS)) {
            HashMap<String, Integer> itemEnchantments = GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ENCHANTMENTS, PersistentDataType.STRING), GsonHandler.ENCHANT_MAP_TYPE);
            if(itemEnchantments.containsKey(enchant.getEnchantmentID())) {
                itemEnchantments.remove(enchant.getEnchantmentID());

                ItemMeta meta = item.getItemMeta();
                meta.getPersistentDataContainer().set(ItemHandler.ENCHANTMENTS, PersistentDataType.STRING, GsonHandler.toJson(itemEnchantments));
                item.setItemMeta(meta);

                enchant.onRemove(item);

                ItemHandler.updateItem(item);
            }
        }
    }

    public static void updateEnchantModifiers(ItemStack item) {
        if(item.getItemMeta().getPersistentDataContainer().has(ItemHandler.ENCHANTMENTS)) {
            HashMap<String, Integer> itemEnchantments = GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ENCHANTMENTS, PersistentDataType.STRING), GsonHandler.ENCHANT_MAP_TYPE);
            for(String enchantID : itemEnchantments.keySet()) {
                if(enchants.containsKey(enchantID)) {
                    ItemEnchant enchant = enchants.get(enchantID);
                    enchant.onApply(item, itemEnchantments.get(enchantID));
                }
            }
        }
    }
}
