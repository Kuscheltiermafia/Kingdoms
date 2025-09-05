package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.mobs.MobHandler;
import de.kuscheltiermafia.kingdoms.mobs.MobStatModel;
import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.gameevent.GameEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R4.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_21_R4.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Random;

public class DamageCalculator {

    public static double calculateFinalDamage(Player p) {
        PlayerStatModel stats = Kingdoms.playerStatModelIdentifier.get(p);

        double crit = Math.random();

        double finalDamage = stats.getStat(Stat.DAMAGE) * (1 + stats.getStat(Stat.STRENGTH) / 100);

        if(crit <= (stats.getStat(Stat.CRIT_CHANCE) / 100)) {
            finalDamage *= ((stats.getStat(Stat.CRIT_DAMAGE) / 100) + 1);
        }

        return finalDamage;
    }

    public static double calculateEntityDamage(LivingEntity entity) {
        if(!MobHandler.isCustomMob(entity)) {
            return 1;
        }
        MobStatModel stats = MobHandler.mobStatModelIndentifier.get(entity.getUniqueId());

        double finalDamage = stats.getStat(Stat.DAMAGE) * (1 + stats.getStat(Stat.STRENGTH) / 100);

        return finalDamage;
    }

    public static void damagePlayer(double dealtDamage, Player target) {
        PlayerStatModel targetStats = Kingdoms.playerStatModelIdentifier.get(target);
        net.minecraft.world.entity.LivingEntity nmsEntity = ((CraftLivingEntity) target).getHandle();

        double damageDealt = dealtDamage - targetStats.getStat(Stat.DEFENSE) * 1.25f;
        if(damageDealt < 0) damageDealt = 0;

        spawnDamageText(target.getLocation().add(0,1,0), damageDealt, true);

        targetStats.setActiveHealth(targetStats.getActiveHealth() - damageDealt);

        ClientboundHurtAnimationPacket packet = new ClientboundHurtAnimationPacket(nmsEntity.getId(), 0);
        for(Player player : Bukkit.getOnlinePlayers()) {
            ServerPlayer nmsEntityPlayer = ((CraftPlayer) player).getHandle();
            nmsEntityPlayer.connection.send(packet);
        }

        if(targetStats.getActiveHealth() <= 0) {
            justFuckingDied(target);
        }
    }

    public static void damageEntity(double dealtDamage, LivingEntity target) {
        if(!MobHandler.isCustomMob(target)) {
            return;
        }
        net.minecraft.world.entity.LivingEntity nmsEntity = ((CraftLivingEntity) target).getHandle();
        MobStatModel targetStats = MobHandler.mobStatModelIndentifier.get(target.getUniqueId());

        double damageDealt = dealtDamage - targetStats.getStat(Stat.DEFENSE) * 1.25f;
        if(damageDealt < 0) damageDealt = 0;

        spawnDamageText(target.getLocation().add(0,1,0), damageDealt, true);

        targetStats.setActiveHealth(targetStats.getActiveHealth() - damageDealt);

        ClientboundHurtAnimationPacket packet = new ClientboundHurtAnimationPacket(nmsEntity.getId(), 0);
        for(Player player : Bukkit.getOnlinePlayers()) {
            ServerPlayer nmsEntityPlayer = ((CraftPlayer) player).getHandle();
            nmsEntityPlayer.connection.send(packet);
        }

        if(targetStats.getActiveHealth() <= 0) {
            MobHandler.mobStatModelIndentifier.remove(target.getUniqueId());
            nmsEntity.die(nmsEntity.damageSources().generic());
            Bukkit.getScheduler().runTaskLater(Kingdoms.getPlugin(), target::remove, 15L);
        }
    }

    public static void justFuckingDied(Player target) {
        PlayerStatModel targetStats = Kingdoms.playerStatModelIdentifier.get(target);
        targetStats.setActiveHealth(targetStats.getStat(Stat.HEALTH));
        target.setHealth(20);
        LobbyTeleport.teleport(target);
    }

    private static void spawnDamageText(Location location, double damage, boolean isCritical) {
        Random random = new Random();
        double xOffset = (random.nextDouble() - 0.5) * 0.6;
        double yOffset = (random.nextDouble()) * 0.5;
        double zOffset = (random.nextDouble() - 0.5) * 0.6;
        location.add(xOffset, yOffset, zOffset);

        TextDisplay display = (TextDisplay) location.getWorld().spawnEntity(location, EntityType.TEXT_DISPLAY);
        display.setText((isCritical ? ChatColor.RED : ChatColor.YELLOW) + String.format("%.1f", damage));
        display.setBillboard(Display.Billboard.CENTER);
        display.setSeeThrough(true);
        display.setShadowed(true);
        display.setInterpolationDuration(10);
        Vector3f scale = new Vector3f(0.5f, 0.5f, 0.5f);
        Vector3f translation = new Vector3f(0f, 0.5f, 0f);
        AxisAngle4f rotation = new AxisAngle4f(0, 1, 0, 0);
        Transformation transformation = new Transformation(translation, rotation, scale, rotation);
        display.setTransformation(transformation);

        Bukkit.getScheduler().runTaskTimer(Kingdoms.getPlugin(), new Runnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks++ > 15) {
                    display.remove();
                    return;
                }
                location.add(0, 0.03, 0);
                display.teleport(location);
            }
        }, 0L, 1L);
    }

    public static enum DamageType {
        TRUE,
        PHYSICAL,
        MAGICAL
    }
}
