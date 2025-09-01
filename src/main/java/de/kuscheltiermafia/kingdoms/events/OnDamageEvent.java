package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.stats.Calculator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamageEvent implements Listener {

    @EventHandler
    public void onDamageEvent(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player damager = (Player) e.getDamager();
            Player target = (Player) e.getEntity();

            Calculator.damagePlayer(Calculator.calculateFinalDamage(damager), target);
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player target = (Player) e.getEntity();

            Calculator.damagePlayer((float) e.getDamage() / 2, target);
        }
    }
}
