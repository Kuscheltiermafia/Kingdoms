package de.kuscheltiermafia.kingdoms;

import de.kuscheltiermafia.kingdoms.items.itemAbilities.AbilityHandler;
import de.kuscheltiermafia.kingdoms.overlays.ActionBarHandler;
import de.kuscheltiermafia.kingdoms.stats.HealingCalculator;
import de.kuscheltiermafia.kingdoms.stats.ManaCalculator;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class RepeatingTasks {

    public static void updateEachTick() {
        new BukkitRunnable() {
            @Override
            public void run() {
                ActionBarHandler.actionBarUpdate();
            }
        }.runTaskTimer(Kingdoms.getPlugin(), 0L, 1);
    }

    public static void updateEachSecond() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Kingdoms.playerStatModelIdentifier.keySet().forEach(HealingCalculator::healingTick);
                Kingdoms.playerStatModelIdentifier.keySet().forEach(ManaCalculator::manaRegenTick);
                Bukkit.getOnlinePlayers().forEach(AbilityHandler::updatePlayerCooldowns);
            }
        }.runTaskTimer(Kingdoms.getPlugin(), 0L, 20);
    }
}
