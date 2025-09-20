package de.kuscheltiermafia.kingdoms.items.itemAbilities.abilities;

import de.kuscheltiermafia.kingdoms.items.itemAbilities.ItemAbility;
import de.kuscheltiermafia.kingdoms.stats.ManaCalculator;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class TestAbility extends ItemAbility {
    public TestAbility() {
        super("Test Ability", "test_ability", new String[] {"Just a test ability!"}, 10, 5, AbilityType.RIGHT_CLICK, AbilityDisplayType.VISIBLE_NORMAL);
    }

    @Override
    public void rightClick(Player p, Event e) {
        if(ManaCalculator.canUseMana(p, this.manaCost)) {
            ManaCalculator.useMana(p, this.manaCost);
            this.setCooldown(p, this.cooldown);
            p.sendMessage("Test Ability activated!");
        }
    }

}
