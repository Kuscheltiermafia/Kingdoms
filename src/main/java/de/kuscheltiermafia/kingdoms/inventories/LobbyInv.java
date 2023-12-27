package de.kuscheltiermafia.kingdoms.inventories;

import org.bukkit.entity.Player;

import static de.kuscheltiermafia.kingdoms.items.ItemHandler.ASCEND_ITEM;

public class LobbyInv {

    public static void buildInv(Player p){

        //reset inventory
        p.getInventory().clear();

        //build inventory
        p.getInventory().setItem(2, ASCEND_ITEM);

    }

}
