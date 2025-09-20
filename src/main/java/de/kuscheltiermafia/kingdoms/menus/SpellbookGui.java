package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
import de.kuscheltiermafia.kingdoms.wizardry.spells.BaseSpell;
import de.kuscheltiermafia.kingdoms.wizardry.spells.SpellHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SpellbookGui extends InventoryGui {

    public SpellbookGui() {
        super("spellbook", 3, ChatColor.DARK_PURPLE + "Your Spellbook");
    }

    @Override
    public void update(Player p) {
        int[] validSlotsUneven = new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17};
        int[] validSlotsEven = new int[]{9, 10, 11, 12, 14, 15, 16, 17};

        ItemStack currentItem = p.getInventory().getItemInMainHand();

        List<String> currentSpells;
        currentSpells = SpellHandler.getSpellSet(currentItem);

        int maxSpells = SpellHandler.getSpellTier(currentItem).getMaxSpells();

        int selectedSpellIndex = 0;
        int currentSlot;

        for(int i = 0; i < maxSpells; i++) {


            if(maxSpells % 2 == 0) currentSlot = validSlotsEven[i + (validSlotsEven.length - maxSpells / 2)];
            else currentSlot = validSlotsUneven[i + (validSlotsUneven.length - (maxSpells / 2))];

            if(i < currentSpells.size()) {
                BaseSpell currentSpell = SpellHandler.getSpellById(currentSpells.get(i));
                ItemBuilder spellItem = new ItemBuilder().setMaterial(currentSpell.getDisplayItem()).setID(currentSpell.getId() + "_spell_qbutton_menuindicator").setCustomName(currentSpell.getColor() + currentSpell.getDisplayName()).temporary();

                if(SpellHandler.getSelectedSpell(currentItem).equals(currentSpell)) {
                    spellItem.addGlint();
                    selectedSpellIndex = i;
                }
                setItem(currentSlot, spellItem.build());
            }else{
                setItem(currentSlot, new ItemBuilder().setMaterial(Material.STRUCTURE_VOID).setCustomName(ChatColor.DARK_GRAY + "No Spell").setID("spacer").temporary().build());
            }
        }
        setItem(selectedSpellIndex - 9, new ItemBuilder().setMaterial(Material.LIME_STAINED_GLASS_PANE).setCustomName(ChatColor.GREEN + "Selected Spell").setID("spacer").temporary().build());
        setItem(selectedSpellIndex + 9, new ItemBuilder().setMaterial(Material.LIME_STAINED_GLASS_PANE).setCustomName(ChatColor.GREEN + "Selected Spell").setID("spacer").temporary().build());

        fillEmptySlots(FillType.BORDER);
    }
}
