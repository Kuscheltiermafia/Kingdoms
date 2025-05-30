package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.inventories.GoldyRealmInv;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.menus.TomeOfEras;
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

import java.util.HashMap;

public class AscendEvent implements Listener {

    public static HashMap<Player, Boolean> godlyRealm = new HashMap<>();

    @EventHandler
    public void onUseAscendItem(PlayerInteractEvent e){
        //declare variables
        Player p = e.getPlayer();

        if(ItemHandler.checkItemID(p.getItemInHand(), "ascend_item")){

            e.setCancelled(true);
            godlyRealm.put(p, true);

            //effects for transition
            PotionEffect fade1 = new PotionEffect(PotionEffectType.BLINDNESS, 60, 255, false, false, false);
            PotionEffect fade2 = new PotionEffect(PotionEffectType.SLOWNESS, 40, 255, false, false, false);
            p.addPotionEffect(fade1);
            p.addPotionEffect(fade2);

            new BukkitRunnable(){
                @Override
                public void run() {
                    //teleport
                    Location GodlyRealmLoc = new Location(p.getWorld(), 54.5, -39.00, 127.5);
                    p.teleport(GodlyRealmLoc);

                    //build inventory
//                    GoldyRealmInv.buildInv(p);

                }
            }.runTaskLater(Kingdoms.getPlugin(), 30);

        }
    }

    @EventHandler
    public void onUseAscendInv(InventoryClickEvent e){
        //declare variables
        Player p = (Player) e.getWhoClicked();

        try {
            if (ItemHandler.checkItemID(e.getCurrentItem(), "ascend_item") && p.getOpenInventory().getTitle().equals(ChatColor.LIGHT_PURPLE + "Tome of Eras")) {

                e.setCancelled(true);
                godlyRealm.put(p, true);

                //effects for transition
                PotionEffect fade1 = new PotionEffect(PotionEffectType.BLINDNESS, 60, 255, false, false, false);
                PotionEffect fade2 = new PotionEffect(PotionEffectType.SLOWNESS, 40, 255, false, false, false);
                p.addPotionEffect(fade1);
                p.addPotionEffect(fade2);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        //teleport
                        Location GodlyRealmLoc = new Location(p.getWorld(), 54.5, -39.00, 127.5);
                        p.teleport(GodlyRealmLoc);

                        //build inventory
//                    GoldyRealmInv.buildInv(p);

                    }
                }.runTaskLater(Kingdoms.getPlugin(), 30);

            }
        }catch (Exception ignored) {}
    }
}
