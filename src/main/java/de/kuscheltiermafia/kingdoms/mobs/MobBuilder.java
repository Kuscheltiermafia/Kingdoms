package de.kuscheltiermafia.kingdoms.mobs;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R4.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import static de.kuscheltiermafia.kingdoms.mobs.MobHandler.mobStatModelIndentifier;

public class MobBuilder {

    EntityType type;
    String displayName;
    MobFamily[] families;

    double health;
    double defense;
    double damage;
    double strength;
    double speed;
    double ferocity;

    public MobBuilder setMobType(EntityType type) {
        this.type = type;
        return this;
    }

    public MobBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public MobBuilder setFamilies(MobFamily[] families) {
        this.families = families;
        return this;
    }

    public MobBuilder setHealth(double health) {
        this.health = health;
        return this;
    }

    public MobBuilder setDefense(double defense) {
        this.defense = defense;
        return this;
    }

    public MobBuilder setDamage(double damage) {
        this.damage = damage;
        return this;
    }

    public MobBuilder setStrength(double strength) {
        this.strength = strength;
        return this;
    }

    public MobBuilder setSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    public MobBuilder setFerocity(double ferocity) {
        this.ferocity = ferocity;
        return this;
    }

    public LivingEntity create(Location location) {
        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, type);

        MobStatModel statModel = new MobStatModel(health, defense, damage, strength, speed, ferocity);
        mobStatModelIndentifier.put(entity.getUniqueId(), statModel);

        Entity nmsEntity = ((CraftEntity) entity).getHandle();
        nmsEntity.addTag("kingdoms_mob");

        for (MobFamily family : families) {
            nmsEntity.addTag("family_" + family.toString().toLowerCase());
        }

        statModel.setActiveHealth(health);

        StringBuilder customName = new StringBuilder();
        for(MobFamily family : families) {
            customName.append(family.getColor()).append(family.getIcon()).append(" ");
        }

        customName.append(displayName);

        nmsEntity.addTag("actual_name_" + customName.toString().replaceAll(" ", "_"));

        customName.append(ChatColor.RESET).append(" ");
        customName.append(MobHandler.returnHealthIndicator(entity, true, 100.00));

        nmsEntity.setCustomName(Component.literal(customName.toString()));
        nmsEntity.setCustomNameVisible(true);

        return entity;
    }
}
