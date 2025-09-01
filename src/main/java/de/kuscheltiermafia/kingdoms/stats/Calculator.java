package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.teleports.LobbyTeleport;
import org.bukkit.entity.Player;

public class Calculator {

    public static float calculateFinalDamage(Player p) {
        PlayerStatModel stats = Kingdoms.playerStatModelIdentifier.get(p);

        double crit = Math.random();

        float finalDamage = stats.getStat(Stat.DAMAGE) * (1 + stats.getStat(Stat.STRENGTH) / 100f);

        if(crit <= stats.getStat(Stat.CRIT_CHANCE)) {
            finalDamage *= (float) ((stats.getStat(Stat.CRIT_DAMAGE) + 1) * 0.5);
        }

        return finalDamage;
    }

    public static void damagePlayer(float dealtDamage, Player target) {
        PlayerStatModel targetStats = Kingdoms.playerStatModelIdentifier.get(target);

        float damageDealt = dealtDamage - targetStats.getStat(Stat.DEFENSE) * 1.25f;

        targetStats.setActualHealth(targetStats.getActualHealth() - damageDealt);
        if(targetStats.getActualHealth() <= 0) {
            justFuckingDied(target);
        }
    }

    public static void justFuckingDied(Player target) {
        PlayerStatModel targetStats = Kingdoms.playerStatModelIdentifier.get(target);
        targetStats.setActualHealth(targetStats.getHealth());
        target.setHealth(20);
        LobbyTeleport.teleport(target);
    }
}
