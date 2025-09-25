package de.kuscheltiermafia.kingdoms.wizardry.spells;

import de.kuscheltiermafia.kingdoms.menus.GuiHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpellEventHandler {

    public static void castSpell(Player caster, ItemStack item, boolean gauntlet) {
        BaseSpell spell = SpellHandler.getSelectedSpell(item);
        if(spell != null) {
            spell.onUse(caster);
            if(!gauntlet) {
                spell.setCooldown(caster, spell.cooldown);
            }
        }
    }

    public static void quickSwapSpell(Player caster, ItemStack item) {
        SpellHandler.cycleNextSpell(item);
    }

    public static void openSpellMenu(Player caster, ItemStack item) {
        GuiHandler.createGui("spellbook").open(caster, true);
    }

}
