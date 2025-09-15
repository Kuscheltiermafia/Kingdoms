package de.kuscheltiermafia.kingdoms.mobs;

import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.kingdoms.stats.models.MobStatModel;
import net.minecraft.world.entity.Entity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R4.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.UUID;

public class MobHandler {

    public static HashMap<UUID, MobStatModel> mobStatModelIndentifier = new HashMap<>();

    public static boolean isCustomMob(LivingEntity entity) {
        Entity nmsEntity = ((CraftEntity) entity).getHandle();
        return nmsEntity.getTags().contains("kingdoms_mob");
    }

    public static void spawnTestEntity(Location location) {
        MobBuilder mobBuilder = new MobBuilder();
        mobBuilder.setMobType(EntityType.ZOMBIE)
                .setDisplayName(ChatColor.RED + "Test Zombie")
                .setFamilies(new MobFamily[]{MobFamily.UNDEAD})
                .setHealth(100000)
                .setDefense(20)
                .setDamage(15)
                .setStrength(10)
                .setSpeed(5)
                .setFerocity(0);
        mobBuilder.create(location);
    }

    public static String returnHealthIndicator(LivingEntity entity, boolean ignoreMap, Double health) {
        if (!isCustomMob(entity) || entity == null) return "";
        if (!mobStatModelIndentifier.containsKey(entity.getUniqueId())) return "";

        double activeHealth;
        StringBuilder healthIndicator = new StringBuilder();

        try {
            if (ignoreMap) {
                activeHealth = health;
            } else {
                MobStatModel stats = mobStatModelIndentifier.get(entity.getUniqueId());
                activeHealth = stats.getActiveHealth();
                health = stats.getStat(Stat.HEALTH, false);
            }
            healthIndicator.append(ChatColor.RED).append(Math.round(activeHealth));
            healthIndicator.append(ChatColor.GRAY).append("/");
            healthIndicator.append(ChatColor.RED).append(health);
        }catch (Exception e) {
            return "";
        }

        return healthIndicator.toString();
    }

    public static void updateMobHealthIndicator(LivingEntity entity) {
        if (!isCustomMob(entity) || entity == null) return;
        Entity nmsEntity = ((CraftEntity) entity).getHandle();
        String nameBase = " ";
        for(String tag : nmsEntity.getTags()) {
            if(tag.startsWith("actual_name")) {
                nameBase = tag.replace("actual_name_", "");
                nameBase.replace("_", " ");
                entity.setCustomNameVisible(true);
                break;
            }
        }
        entity.setCustomName(nameBase + " " + returnHealthIndicator(entity, false, 100.00));
        entity.setCustomNameVisible(true);
    }
}
