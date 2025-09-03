package de.kuscheltiermafia.kingdoms.inventories;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.entity.Player;


public class GoldyRealmInv {

    public static void buildInv(Player p){

        p.getInventory().clear();
        p.getInventory().setItem(2, ItemHandler.getItem("descend_item"));

    }
}
