package de.kuscheltiermafia.kingdoms.items;

import de.kuscheltiermafia.kingdoms.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class ItemHandler {

    public static NamespacedKey ID = new NamespacedKey(Main.getPlugin(), "ID");
    public static ArrayList<ItemStack> itemList = new ArrayList<>();

    public static ItemStack ascend_item;
    public static ItemStack descend_item;

    //Executing ItemMeta Assignments
    public static void innitItems(){
        ascend_item = createItem(Material.ENDER_EYE, ChatColor.AQUA + "Ascend", "ascend_item", 1, null, true, false, false);
        descend_item = createItem(Material.ENDER_PEARL, ChatColor.AQUA + "Ascend", "ascend_item", 1, null, true, false, false);
    }

    //Assigning ItemMeta
    public static ItemStack createItem(Material material, String name, String id, int stackSize, @Nullable List<String> lore, boolean glint, boolean hideTooltip, boolean hideAdditionalTooltip) {

        ItemStack genItem = new ItemStack(material);
        ItemMeta meta = genItem.getItemMeta();

        meta.setMaxStackSize(stackSize);
        meta.getPersistentDataContainer().set(ID, PersistentDataType.STRING, id);
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.setEnchantmentGlintOverride(glint);
        meta.setHideTooltip(hideTooltip);

        if (hideAdditionalTooltip) {
            meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        }

        genItem.setItemMeta(meta);

        itemList.add(genItem);
        return genItem;
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
        if(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ID, PersistentDataType.STRING).equals(id)) {
            return true;
        }else{
            return false;
        }
    }

}
