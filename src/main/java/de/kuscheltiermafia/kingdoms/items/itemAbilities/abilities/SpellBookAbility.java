package de.kuscheltiermafia.kingdoms.items.itemAbilities.abilities;

import de.kuscheltiermafia.kingdoms.items.itemAbilities.ItemAbility;
import de.kuscheltiermafia.kingdoms.wizardry.spells.SpellEventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpellBookAbility extends ItemAbility {

    int maxSpells;

    public SpellBookAbility(int maxSpells) {
        super("spell_caster", "Spell Book", new String[]{"Allows the use of " + maxSpells + " spells."}, 0, 0, AbilityType.GENERAL_CLICK, AbilityDisplayType.SPELL);
        this.maxSpells = maxSpells;
    }

    @Override
    public void onGeneralClick(Player player, Event event) {
        PlayerInteractEvent e = (PlayerInteractEvent) event;
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if(player.isSneaking()) {
                SpellEventHandler.openSpellMenu(player, e.getItem());
            }
            else {
                SpellEventHandler.castSpell(player, e.getItem(), false);
            }
        }
        if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            SpellEventHandler.quickSwapSpell(player, e.getItem());
        }
    }
}
