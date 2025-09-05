package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.mobs.MobHandler;
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

import java.util.ArrayList;
import java.util.List;

import static de.kuscheltiermafia.kingdoms.mobs.MobHandler.updateMobHealthIndicator;

public class OnDamageEvent implements Listener {

    private List<EntityDamageEvent.DamageCause> normalDamageCauses = new ArrayList<>(List.of(
            EntityDamageEvent.DamageCause.DROWNING,
            EntityDamageEvent.DamageCause.FALL,
            EntityDamageEvent.DamageCause.FREEZE,
            EntityDamageEvent.DamageCause.FIRE,
            EntityDamageEvent.DamageCause.LAVA,
            EntityDamageEvent.DamageCause.LIGHTNING,
            EntityDamageEvent.DamageCause.SUFFOCATION,
            EntityDamageEvent.DamageCause.VOID
    ));

    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) e.getEntity();
            LivingEntity damager = (LivingEntity) e.getDamageSource().getCausingEntity();

            if(target instanceof Player && Kingdoms.playerStatModelIdentifier.containsKey(target)){
                if(MobHandler.isCustomMob(damager)) {
                    DamageCalculator.damagePlayer(DamageCalculator.calculateEntityDamage(damager), (Player) target);
                }
            }
            if(damager instanceof Player && Kingdoms.playerStatModelIdentifier.containsKey(damager)){
                if(MobHandler.isCustomMob(target)) {
                    DamageCalculator.damageEntity(DamageCalculator.calculateFinalDamage((Player) damager), target);
                    updateMobHealthIndicator(target);
                }
            }
        }
    }

    @EventHandler
    public void onDamageEvent(EntityDamageEvent e) {
        e.setCancelled(true);
        if (e.getEntity() instanceof Player && normalDamageCauses.contains(e.getCause())) {
            Player player = (Player) e.getEntity();
            DamageCalculator.damagePlayer(e.getFinalDamage(), player);
            ServerPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
            nmsPlayer.animateHurt(10);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setFoodLevel(20);
        e.setCancelled(true);
    }
}
