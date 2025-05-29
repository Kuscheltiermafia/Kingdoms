package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuUtils {
    public static void fillWithSpacers(Inventory inv) {
        for(int i = 0; i < inv.getSize(); i++) {
            if(inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
                inv.setItem(i, new ItemStack(ItemHandler.spacer));
            }
        }
    }
    public static void placeReturnButton(Inventory inv) {
        inv.setItem(45, new ItemStack(Material.AIR));
        inv.setItem(45, ItemHandler.createItem(Material.ARROW, ChatColor.RED + "Go Back", "back_button_toe_main", 1, null, false, false, false, false));
    }
}
