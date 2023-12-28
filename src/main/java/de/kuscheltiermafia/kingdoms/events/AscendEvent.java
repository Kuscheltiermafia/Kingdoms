package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.Main;
import de.kuscheltiermafia.kingdoms.inventories.GoldyRealmInv;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static de.kuscheltiermafia.kingdoms.items.ItemHandler.ASCEND_ITEM;

public class AscendEvent implements Listener {

    @EventHandler
    public void onUseAscendItem(PlayerInteractEvent e){

        //declare variables
        Player p = e.getPlayer();

        if(Objects.equals(e.getItem(), ASCEND_ITEM)){

            //effects for transition
            PotionEffect fade1 = new PotionEffect(PotionEffectType.BLINDNESS, 60, 255, false, false, false);
            PotionEffect fade2 = new PotionEffect(PotionEffectType.SLOW, 40, 255, false, false, false);
            p.addPotionEffect(fade1);
            p.addPotionEffect(fade2);

            new BukkitRunnable(){
                @Override
                public void run() {
                    //teleport
                    Location GodlyRealmLoc = new Location(p.getWorld(), 54.5, -39.00, 127.5);
                    p.teleport(GodlyRealmLoc);

                    //build inventory
                    GoldyRealmInv.buildInv(p);

                }
            }.runTaskLater(Main.getPlugin(), 30);



        }

    }

}
