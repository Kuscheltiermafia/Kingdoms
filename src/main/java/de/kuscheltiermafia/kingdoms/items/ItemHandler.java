package de.kuscheltiermafia.kingdoms.items;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class ItemHandler {

    public static NamespacedKey ID = new NamespacedKey(Kingdoms.getPlugin(), "kingdoms");
    public static NamespacedKey STATS = new NamespacedKey(Kingdoms.getPlugin(), "stats");
    public static NamespacedKey ENCHANTMENTS = new NamespacedKey(Kingdoms.getPlugin(), "enchantments");
    public static NamespacedKey GEMSTONES = new NamespacedKey(Kingdoms.getPlugin(), "gemstones");
    public static NamespacedKey TEMP_STORAGE = new NamespacedKey(Kingdoms.getPlugin(), "temporary_storage");

    public static ArrayList<ItemStack> itemList = new ArrayList<>();

    //Utils
    public static ItemStack convertToDisplayItem(ItemStack toSpacer, int amount) {
        ItemStack spacerItem = new ItemStack(toSpacer);
        ItemMeta meta = spacerItem.getItemMeta();
        assert meta != null;
        meta.getPersistentDataContainer().set(ID, PersistentDataType.STRING, "spacer");
        spacerItem.setItemMeta(meta);
        spacerItem.setAmount(amount);
        return spacerItem;
    }

    public static ItemStack getItemById(String id) {
        for (ItemStack item : itemList) {
            try {
                if (item.getItemMeta().getPersistentDataContainer().get(ID, PersistentDataType.STRING).equals(id)) {
                    return item;
                }
            }catch (Exception ignored) {
            }
        }
        return null;
    }

    public static boolean checkItemID(ItemStack item, String id) {
        try {
            if (item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ID, PersistentDataType.STRING).equals(id)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            return false;
        }
    }

    public static String getItemID(ItemStack item) {
        try {
            return item.getItemMeta().getPersistentDataContainer().get(ID, PersistentDataType.STRING);
        }catch (Exception ignored) {
            return "null";
        }
    }
}
