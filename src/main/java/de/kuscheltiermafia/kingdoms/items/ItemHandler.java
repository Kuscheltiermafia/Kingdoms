package de.kuscheltiermafia.kingdoms.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemHandler {

//ItemStack Variables
    public static final ItemStack ASCEND_ITEM = new ItemStack(Material.ENDER_EYE);
    public static final ItemStack DESCEND_ITEM = new ItemStack(Material.ENDER_EYE);

//Executing ItemMeta Assignments
    public static void createItems(){

        createAscendItem();
        createDescendItem();

    }

//Assigning ItemMeta
    static void createAscendItem(){

        ItemMeta im = ASCEND_ITEM.getItemMeta();
        assert im != null;
        im.setDisplayName("§l§bAscend");
        im.setCustomModelData(101);
        ASCEND_ITEM.setItemMeta(im);

    }

    static void createDescendItem(){

        ItemMeta im = DESCEND_ITEM.getItemMeta();
        assert im != null;
        im.setDisplayName("§l§4Descend");
        im.setCustomModelData(102);
        DESCEND_ITEM.setItemMeta(im);

    }

}
