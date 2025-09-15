package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.mobs.MobHandler;
import de.kuscheltiermafia.kingdoms.stats.models.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.models.StatModel;
import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R4.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_21_R4.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.Random;

public class DamageCalculator {

    public static void onDamageByEntity(LivingEntity damager, LivingEntity target) {
        StatModel damagerStats = getStatModel(damager);
        StatModel targetStats = getStatModel(target);

        boolean isCritical;

        if(targetStats == null) return;

        double damage = damagerStats.getStat(Stat.DAMAGE, true) * (1 + damagerStats.getStat(Stat.STRENGTH, true) / 100);

        double crit = Math.random();

        if(crit <= (damagerStats.getStat(Stat.CRIT_CHANCE, true) / 100)) {
            damage *= ((damagerStats.getStat(Stat.CRIT_DAMAGE, true) / 100) + 1);
            isCritical = true;
        } else {
            isCritical = false;
        }

        double ferocity = damagerStats.getStat(Stat.FEROCITY, true);

        if (ferocity > 0) {
            targetStats.setActiveHealth(targetStats.getActiveHealth() - (damage));

            int hitsCount = 0;
            int fullHits = (int) ferocity / 100;
            int remainder = (int) ferocity % 100;

            for (int i = 0; i < fullHits; i++) {
                hitsCount++;
                double finalDamageDealt = damage;
                Bukkit.getScheduler().runTaskLater(Kingdoms.getPlugin(), () -> {
                    applyDamage(target, isCritical, finalDamageDealt);
                }, hitsCount * 2L);
            }
            if (remainder > 0) {
                double finalDamagePartial = damage * (remainder / 100.0);
                Bukkit.getScheduler().runTaskLater(Kingdoms.getPlugin(), () -> {
                    applyDamage(target, isCritical, finalDamagePartial);
                }, (hitsCount + 1) * 2L);
            }
        } else {
            applyDamage(target, isCritical, damage);
        }
    }

    public static void damagePlayer(double damage, Player target) {
        applyDamage(target, false, damage);
    }

    private static StatModel getStatModel(LivingEntity entity) {
        if (entity instanceof Player) {
            return Kingdoms.playerStatModelIdentifier.get((Player) entity);
        } else if (MobHandler.isCustomMob(entity)) {
            return MobHandler.mobStatModelIndentifier.get(entity.getUniqueId());
        }
        return null;
    }

    private static void applyDamage(LivingEntity target, boolean isCritical, double damage) {
        StatModel targetStats = getStatModel(target);
        if(targetStats == null) return;

        targetStats.setActiveHealth(targetStats.getActiveHealth() - (damage));

        playDamageAnimation(target);
        spawnDamageText(target.getLocation().add(0,1,0), damage, isCritical);
        isDead(target);

        if(MobHandler.isCustomMob(target)) {
            MobHandler.updateMobHealthIndicator(target);
        }
    }

    private static void isDead(LivingEntity target) {
        StatModel targetStats = getStatModel(target);
        net.minecraft.world.entity.LivingEntity nmsTarget = ((CraftLivingEntity) target).getHandle();

        assert targetStats != null;

        if (targetStats.getActiveHealth() <= 0) {
            if (target instanceof Player) {
                justFuckingDied((Player) target);
                return;
            }

            if (MobHandler.isCustomMob(target)) {
                MobHandler.mobStatModelIndentifier.remove(target.getUniqueId());
                nmsTarget.die(nmsTarget.damageSources().generic());
                Bukkit.getScheduler().runTaskLater(Kingdoms.getPlugin(), target::remove, 15L);
                return;
            }

            target.remove();
        }
    }

    private static void playDamageAnimation(LivingEntity target) {
        net.minecraft.world.entity.LivingEntity nmsTarget = ((CraftLivingEntity) target).getHandle();

        ClientboundHurtAnimationPacket packet = new ClientboundHurtAnimationPacket(nmsTarget.getId(), 0);
        for(Player player : Bukkit.getOnlinePlayers()) {
            ServerPlayer nmsEntityPlayer = ((CraftPlayer) player).getHandle();
            nmsEntityPlayer.connection.send(packet);
        }

    }

    public static void justFuckingDied(Player target) {
        PlayerStatModel targetStats = Kingdoms.playerStatModelIdentifier.get(target);
        targetStats.setActiveHealth(targetStats.getStat(Stat.HEALTH, false));
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
