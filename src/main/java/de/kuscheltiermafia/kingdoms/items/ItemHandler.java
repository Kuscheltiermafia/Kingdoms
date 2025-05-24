package de.kuscheltiermafia.kingdoms.items;

import de.kuscheltiermafia.kingdoms.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemHandler {

    public static NamespacedKey ID = new NamespacedKey(Main.getPlugin(), "ID");
    public static ArrayList<ItemStack> itemList = new ArrayList<>();

    public static ItemStack ascend_item;
    public static ItemStack descend_item;

    public static ItemStack iron_sword;

    public static ItemStack focken;

    //Debug Items
    public static ItemStack spacer;
    public static ItemStack page_up;
    public static ItemStack page_down;
    public static ItemStack no_page_up;
    public static ItemStack no_page_down;
    public static ItemStack placeholder;

    //Executing ItemMeta Assignments
    public static void innitItems(){
        //lobby items
        ascend_item = createItem(Material.ENDER_EYE, ChatColor.AQUA + "Ascend", "ascend_item", 1, null, true, false, false, true);
        descend_item = createItem(Material.ENDER_PEARL, ChatColor.AQUA + "Descend", "descend_item", 1, null, true, false, false, true);

        //weapons
        iron_sword = createItem(Material.IRON_SWORD, ChatColor.WHITE + "Iron Sword", "iron_sword", 1, null, true, false, false, true);
        ItemStats.addItemStats(iron_sword, 0f, 0f, 20000f, 0f, 0f, 0f, 20000f, 20000f, 0f, 0f, 0f, 0f, 0f);

        ArrayList<String> focken_lore = new ArrayList<String>();
        focken_lore.add("§7- " + ChatColor.LIGHT_PURPLE + "Fantastisches orientiertes cooles kurioses entanguliertes Neutron" + "§7 -");
        focken_lore.add("§7oder kurz, FOCKEN!");
        focken_lore.add("§7Keine Ahnung was das ist...");

        focken = createItem(Material.DARK_OAK_BUTTON, ChatColor.LIGHT_PURPLE + "Focken", "focken", 1, focken_lore, true, false, false, true);
        ItemStats.addItemStats(focken, 0f, 0f, 999999f, 999999f, 0f, 0f, 0f, 0f, 0f, 696969f, 0f, 0f, 999999f);

        //debug
        spacer = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", "spacer", 1, null, false, true, false, false);
        page_up = createItem(Material.ARROW, "§4Next Page", "page_up_indicator", 1, null, false, false, false, false);
        page_down = createItem(Material.ARROW, "§4Previous Page", "page_down_indicator", 1, null, false, false, false, false);
        no_page_down = createItem(Material.BARRIER, "§4No previous Page available", "page_up_indicator", 1, null, false, false, false, false);
        no_page_up = createItem(Material.BARRIER, "§4No next Page available", "no_page_up_indicator", 1, null, false, false, false, false);
        placeholder = createItem(Material.STRUCTURE_VOID, "§5§l§kA§r§7 PLACEHOLDER §r§5§l§kA", "placeholder", 1, null, true, false, false, true);
    }

    //Assigning ItemMeta
    public static ItemStack createItem(Material material, String name, String id, int stackSize, @Nullable List<String> lore, boolean glint, boolean hideTooltip, boolean hideAdditionalTooltip, boolean visible) {

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

        if(visible) {
            itemList.add(genItem);
        }

        for(int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getItemMeta().getPersistentDataContainer().get(ID, PersistentDataType.STRING).equals(id)) {
                System.out.println("Item with ID " + id + " already exists in itemList. Replacing it with " + id + ".");
            }
        }

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
