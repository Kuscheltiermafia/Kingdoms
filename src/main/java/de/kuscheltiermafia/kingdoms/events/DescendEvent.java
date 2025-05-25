package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.menus.TomeOfEras;
import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DescendEvent implements Listener {

    @EventHandler
    public void onUseDescendItem(PlayerInteractEvent e){

        //declare variables
        Player p = e.getPlayer();

        if(ItemHandler.checkItemID(p.getItemInHand(), "descend_item")){
            //teleport
            e.setCancelled(true);
            AscendEvent.godlyRealm.put(p, false);
            LobbyTeleport.teleport(p);

        }

    }

    @EventHandler
    public void onUseAscendInv(InventoryClickEvent e){
        //declare variables
        Player p = (Player) e.getWhoClicked();

        try {
            if (ItemHandler.checkItemID(e.getCurrentItem(), "descend_item") && p.getOpenInventory().getTitle().equals(ChatColor.LIGHT_PURPLE + "Tome of Eras")) {
                e.setCancelled(true);
                AscendEvent.godlyRealm.put(p, false);
                LobbyTeleport.teleport(p);

            }
        }catch (Exception ignored) {}
    }

}
