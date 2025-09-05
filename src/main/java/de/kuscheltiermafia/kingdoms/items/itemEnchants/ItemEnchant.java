package de.kuscheltiermafia.kingdoms.items.itemEnchants;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.items.ItemType;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class ItemEnchant {

    public String enchantmentName;
    public String enchantmentID;
    public EnchantType enchantType;
    public String[] description;
    public ItemType[] applicableItemTypes;
    public int maxLevel;

    public ItemEnchant(String enchantmentName, String enchantmentID, EnchantType enchantmentType, String[] description, int maxLevel, ItemType[] applicableItemTypes) {
        this.enchantmentName = enchantmentName;
        this.enchantmentID = enchantmentID;
        this.description = description;
        this.maxLevel = maxLevel;
        this.applicableItemTypes = applicableItemTypes;
        this.enchantType = enchantmentType;
    }

    public void onApply(ItemStack item, int level) {
        // Enchantment effect logic to be implemented in subclasses
    }

    public void onRemove(ItemStack item) {
        // Logic for when the enchantment is removed, to be implemented in subclasses
    }

    public void onTrigger(ItemStack item, int level) {
        // Logic for when the enchantment is triggered, to be implemented in subclasses
    }

    protected int returnEnchantmentLevel(ItemStack item, ItemEnchant enchant) {
        HashMap<String, Integer> itemEnchantments = GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ENCHANTMENTS, PersistentDataType.STRING), GsonHandler.ENCHANT_MAP_TYPE);
        if(itemEnchantments == null || !itemEnchantments.containsKey(enchant.getEnchantmentID())) return 0;
        return itemEnchantments.get(enchant.getEnchantmentID());
    }

    public boolean canApplyToItem(ItemStack item) {
        String itemTypeID = item.getItemMeta().getPersistentDataContainer().get(ItemHandler.TYPE, PersistentDataType.STRING);
        if(itemTypeID == null) return false;
        ItemType itemType = ItemType.getById(itemTypeID);
        for(ItemType type : this.applicableItemTypes) {
            if(type == itemType) return true;
        }
        return false;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public String getEnchantmentID() {
        return enchantmentID;
    }

    public static enum EnchantType {
        NORMAL(ChatColor.BLUE),
        SPECIAL(ChatColor.RED),
        ULTIMATE(ChatColor.LIGHT_PURPLE);

        final ChatColor color;

        EnchantType(ChatColor color) {
            this.color = color;
        }

        public ChatColor getColor() {
            return color;
        }
    }
}
