package de.kuscheltiermafia.kingdoms.wizardry.spells;

import de.kuscheltiermafia.kingdoms.stats.HealingCalculator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HealingSpell extends BaseSpell {
    public HealingSpell() {
        super("instant_health", "Healing Spell", 10, 5, ChatColor.RED, Material.GOLDEN_APPLE, null);
    }

    @Override
    public void onUse(Player caster) {

        for(int i = 0; i < 4; i++) {
            HealingCalculator.healingTick(caster);
        }
    }
}
