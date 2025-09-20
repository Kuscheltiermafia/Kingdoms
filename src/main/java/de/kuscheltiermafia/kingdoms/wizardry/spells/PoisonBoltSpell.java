package de.kuscheltiermafia.kingdoms.wizardry.spells;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class PoisonBoltSpell extends BaseSpell {

    public PoisonBoltSpell() {
        super("poison_bolt", "Poison Bolt", 15, 4, ChatColor.GREEN, Material.GREEN_DYE, new ArrayList<String>(){{
            add("Shoots a bolt of poison");
            add("that deals damage over time.");
        }});
    }

    @Override
    public void onUse(Player caster) {
        Location loc = caster.getLocation();
        loc.add(0, 1.5, 0);

        for (double i = 0; i < 2; i += 0.05) {
            Vector direction = loc.getDirection().multiply(i / 2);
            loc.add(direction);

            if (!loc.getBlock().getType().isAir()) {
                break;
            }

            loc.getWorld().getNearbyEntities(loc, 2, 2, 2).forEach(entity -> {
                if (entity instanceof LivingEntity) {
                    if (entity != caster) {
                        ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 1, true, true, true));
                    }
                }
            });

            loc.getWorld().spawnParticle(Particle.COMPOSTER, loc, 2);
        }
    }
}
