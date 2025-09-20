package de.kuscheltiermafia.kingdoms.wizardry.spells;

import de.kuscheltiermafia.kingdoms.menus.GuiHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpellEventHandler {

    public static void castSpell(Player caster, ItemStack item, boolean gauntlet) {
        System.out.println("Casting spell from item: " + item);

        BaseSpell spell = SpellHandler.getSelectedSpell(item);
        if(spell != null) {
            spell.onUse(caster);
            if(!gauntlet) {
                spell.setCooldown(caster, spell.cooldown);
            }
        }
    }

    public static void quickSwapSpell(Player caster, ItemStack item) {
        System.out.println("Quick swapping spell from item: " + item);
        SpellHandler.cycleNextSpell(item);
    }

    public static void openSpellMenu(Player caster, ItemStack item) {
        System.out.println("Opening spell menu for item: " + item);
        GuiHandler.getGui("spellbook").open(caster, true);
    }

}
