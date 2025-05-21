package de.kuscheltiermafia.kingdoms.teleports;

import de.kuscheltiermafia.kingdoms.Main;
import de.kuscheltiermafia.kingdoms.inventories.LobbyInv;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyTeleport {

    public static void teleport(Player p){

        //effects for transition
        PotionEffect fade1 = new PotionEffect(PotionEffectType.BLINDNESS, 60, 255, false, false, false);
        PotionEffect fade2 = new PotionEffect(PotionEffectType.SLOWNESS, 40, 255, false, false, false);
        p.addPotionEffect(fade1);
        p.addPotionEffect(fade2);

        new BukkitRunnable(){

            @Override
            public void run() {

                //teleport
                Location PortalLoc = new Location(p.getWorld(), 28.5, 0.00, 39.5, -90F, 0F);
                p.teleport(PortalLoc);

                //build inventory
                LobbyInv.buildInv(p);
            }
        }.runTaskLater(Main.getPlugin(), 30);

    }

}
