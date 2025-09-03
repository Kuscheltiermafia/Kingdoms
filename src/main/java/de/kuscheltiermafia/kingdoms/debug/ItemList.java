package de.kuscheltiermafia.kingdoms.debug;

import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemList implements CommandExecutor {

    public static HashMap<Player, Integer> itemListPage = new HashMap<>();

    public static int[] spacers = new int[]{3, 4, 5, 6, 7, 8, 9, 18, 27, 36, 45, 17, 26, 35, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            Inventory itemList = Bukkit.createInventory(null, 9*6, "§4Itemlist");

            itemListPage.put(p, 0);

            fillItemlist(itemList, itemListPage.get(p), p);

            p.openInventory(itemList);
        }
        return true;
    }

    public static void fillItemlist(Inventory itemList, int currentPage, Player user) {

        int pageModi = 28 * (currentPage);

        for(int i = 0; i < itemList.getSize(); i++) {
            itemList.setItem(i, new ItemStack(Material.AIR));
        }

        if(itemListPage.get(user) == 0) {
            itemList.setItem(0, ItemHandler.getItem("no_page_down_indicator"));
        }else{itemList.setItem(0, ItemHandler.getItem("page_down_indicator"));}

        if((itemListPage.get(user) + 1) * 28 <= ItemHandler.itemList.size()) {
            itemList.setItem(2, ItemHandler.getItem("page_up_indicator"));
        }else{itemList.setItem(2, ItemHandler.getItem("no_page_up_indicator"));}

        itemList.setItem(1, new ItemBuilder().setCustomName(ChatColor.DARK_RED + "Current Page: " + (itemListPage.get(user) + 1)).setID("spacer").setMaterial(Material.BOOK).setMaxStackSize(1).temporary().build());

        for(int i = 0; i < spacers.length; i++) {
            int slot = spacers[i];
            itemList.setItem(slot, ItemHandler.getItem("spacer"));
        }

        for (int i = 0; i < 28; i++) {
            if(i + pageModi >= ItemHandler.itemList.size()) {
                break;
            }else {
                itemList.addItem(ItemHandler.itemList.get(i + pageModi));
            }
        }
    }
}
