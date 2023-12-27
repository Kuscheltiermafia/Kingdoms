package de.kuscheltiermafia.kingdoms.inventories;

import org.bukkit.entity.Player;

import static de.kuscheltiermafia.kingdoms.items.ItemHandler.DESCEND_ITEM;

public class GoldyRealmInv {

    public static void buildInv(Player p){

        p.getInventory().clear();
        p.getInventory().setItem(2, DESCEND_ITEM);

    }
}
