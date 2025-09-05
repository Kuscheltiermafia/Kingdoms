package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import org.bukkit.entity.Player;

public class ManaCalculator {

    public static void manaRegenTick(Player p) {
        PlayerStatModel stats = Kingdoms.playerStatModelIdentifier.get(p);

        double regenAmount = Math.ceil(stats.getStat(Stat.INTELLIGENCE) * 0.02 * (1 + (stats.getStat(Stat.MANA_REGENERATION) / 100)) * (1 + (stats.getStat(Stat.SPELLBOUND) / 1000)));

        if(stats.getActiveMana() + regenAmount <= stats.getMaxMana()) {
            stats.setActiveMana(stats.getActiveMana() + regenAmount);
        }else {
            stats.setActiveMana(stats.getMaxMana());
        }
    }

    public static boolean canUseMana(Player p, int amount) {
        PlayerStatModel stats = Kingdoms.playerStatModelIdentifier.get(p);
        if(stats.getActiveMana() >= amount) {
            return true;
        } else {
            return false;
        }
    }

    public static void useMana(Player p, int amount) {
        PlayerStatModel stats = Kingdoms.playerStatModelIdentifier.get(p);
        p.sendMessage("Used " + amount + " mana.");
        p.sendMessage("Mana before use: " + stats.getActiveMana());
        double newMana = stats.getActiveMana() - amount;
        stats.setActiveMana(newMana);
        p.sendMessage("Mana after use: " + stats.getActiveMana());
    }
}
