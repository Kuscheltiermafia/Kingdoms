package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuUtils {
    public static void fillWithSpacers(Inventory inv) {
        for(int i = 0; i < inv.getSize(); i++) {
            if(inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
                inv.setItem(i, ItemHandler.getItem("spacer"));
            }
        }
    }

    public static void placeReturnButton(Inventory inv) {
        inv.setItem(45, new ItemStack(Material.AIR));
        inv.setItem(45, new ItemBuilder().setMaterial(Material.ARROW).setID("back_button_toe_main").setCustomName(ChatColor.RED + "Go Back").setMaxStackSize(1).build());
    }

    public static void clearInventory(Inventory inv) {
        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, null);
        }
    }
}
