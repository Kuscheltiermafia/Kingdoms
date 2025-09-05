package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.stats.DamageCalculator;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.v1_21_R4.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class OnDamageEvent implements Listener {

    @EventHandler
    public void onDamageEvent(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player damager = (Player) e.getDamager();
            Player target = (Player) e.getEntity();

            ServerPlayer nmsDamager = ((CraftPlayer) damager).getHandle();

            DamageCalculator.damagePlayer(DamageCalculator.calculateFinalDamage(damager), target);
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        e.setCancelled(true);
        if (e.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) e.getEntity();
            target.damage(0);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setFoodLevel(20);
        e.setCancelled(true);
    }
}
