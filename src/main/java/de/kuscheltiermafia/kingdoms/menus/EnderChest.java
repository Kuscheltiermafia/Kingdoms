package de.kuscheltiermafia.kingdoms.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class EnderChest {

    private static int[] size = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};

    public static Inventory openEnderChest(Player p) {
        Inventory enderChest = Bukkit.createInventory(null, 9 * 6, ChatColor.LIGHT_PURPLE + "Tome of Eras - Ender Chest");

        MenuUtils.fillWithSpacers(enderChest);

        for (int i : size) {
            enderChest.setItem(i, new ItemStack(Material.AIR));
            enderChest.setItem(i, p.getEnderChest().getItem(Arrays.binarySearch(size, i)));
        }

        MenuUtils.placeReturnButton(enderChest);

        return enderChest;
    }

    public static void closeEnderChest(Inventory enderChest, Player p) {
        for (int i : size) {
            p.getEnderChest().setItem(Arrays.binarySearch(size, i), enderChest.getItem(i));
        }
    }
}
