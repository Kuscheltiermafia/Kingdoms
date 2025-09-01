package de.kuscheltiermafia.kingdoms.inventories;

import org.bukkit.entity.Player;

import static de.kuscheltiermafia.kingdoms.items.Items.descend_item;

public class GoldyRealmInv {

    public static void buildInv(Player p){

        p.getInventory().clear();
        p.getInventory().setItem(2, descend_item);

    }
}
