package de.kuscheltiermafia.kingdoms.inventories;

import org.bukkit.entity.Player;

import static de.kuscheltiermafia.kingdoms.items.ItemHandler.descend_item;

public class GoldyRealmInv {

    public static void buildInv(Player p){

        p.getInventory().clear();
        p.getInventory().setItem(2, descend_item);

    }
}
