package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import org.bukkit.entity.Player;

public class Calculator {

    public static double calculateFinalDamage(Player p) {
        PlayerStatModel stats = Kingdoms.playerStatModelIdentifier.get(p);

        double crit = Math.random();

        double finalDamage = stats.getStat(Stat.DAMAGE) * (1 + stats.getStat(Stat.STRENGTH) / 100f);

        if(crit <= stats.getStat(Stat.CRIT_CHANCE)) {
            finalDamage *= (float) ((stats.getStat(Stat.CRIT_DAMAGE) + 1) * 0.5);
        }

        return finalDamage;
    }

    public static void damagePlayer(double dealtDamage, Player target) {
        PlayerStatModel targetStats = Kingdoms.playerStatModelIdentifier.get(target);

        double damageDealt = dealtDamage - targetStats.getStat(Stat.DEFENSE) * 1.25f;

        targetStats.setActiveHealth(targetStats.getActiveHealth() - damageDealt);
        if(targetStats.getActiveHealth() <= 0) {
            justFuckingDied(target);
        }
    }

    public static void justFuckingDied(Player target) {
        PlayerStatModel targetStats = Kingdoms.playerStatModelIdentifier.get(target);
        targetStats.setActiveHealth(targetStats.getStat(Stat.HEALTH));
        target.setHealth(20);
        LobbyTeleport.teleport(target);
    }
}
