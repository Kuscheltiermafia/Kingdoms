package de.kuscheltiermafia.kingdoms.wizardry.spells;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpellTextGenerator {

    public static List<String> generateSpellAbilityLore(ItemMeta meta) {
        List<String> lore = new ArrayList<>();

        ItemStack parseMeta = new ItemStack(Material.BOOK);
        parseMeta.setItemMeta(meta);
        BaseSpell currentSpell = SpellHandler.getSelectedSpell(parseMeta);

        List<String> spells = SpellHandler.getSpellSet(parseMeta);

        if(currentSpell == null || spells == null) return lore;
        lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Current Spell: " + ChatColor.RESET + currentSpell.getColor() + currentSpell.getDisplayName());
        lore.add(" ");

        int startIndex = spells.indexOf(currentSpell.getId());

        for(int i = startIndex + 1; i < spells.size() - 1 + startIndex; i++) {
            int index;
            if(i < spells.size()) index = i;
            else index = i - spells.size();

            BaseSpell spell = SpellHandler.getSpellById(spells.get(index));
            if(!spell.equals(currentSpell)) {
                lore.add(ChatColor.GRAY + "> " + spell.getDisplayName() + " <");
            }
        }
        return lore;
    }
}
