package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import org.bukkit.entity.Player;

public class HealingCalculator {

    public static void healingTick(Player p) {
        PlayerStatModel stats = Kingdoms.playerStatModelIdentifier.get(p);

        double regenAmount = stats.getStat(Stat.HEALTH) * 0.02 * (1 + (stats.getStat(Stat.VITALITY) / 100));

        if(stats.getActiveHealth() + regenAmount <= stats.getStat(Stat.HEALTH)) {
            stats.setActiveHealth(stats.getActiveHealth() + regenAmount);
        }else{
            stats.setActiveHealth(stats.getStat(Stat.HEALTH));
        }

    }
}
