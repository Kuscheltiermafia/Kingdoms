package de.kuscheltiermafia.kingdoms.wizardry.spells;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static de.kuscheltiermafia.kingdoms.utils.GsonHandler.STRING_LIST_TYPE;

public class SpellHandler {

    static HashMap<String, BaseSpell> spellMap = new HashMap<>();

    public static void registerSpells() {
        spellMap.put("poison_bolt", new PoisonBoltSpell());
        spellMap.put("instant_health", new HealingSpell());
    }

    public static BaseSpell getSpellById(String id) {
        return spellMap.get(id);
    }

    public static boolean storeSpell(BaseSpell spell, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(ItemHandler.SPELLS, PersistentDataType.STRING)) meta.getPersistentDataContainer().set(ItemHandler.SPELLS, PersistentDataType.STRING, "");
        ArrayList<String> currentSpells = GsonHandler.fromJson(meta.getPersistentDataContainer().get(ItemHandler.SPELLS, PersistentDataType.STRING), STRING_LIST_TYPE);
        if(currentSpells == null) currentSpells = new ArrayList<>();
        if(currentSpells.size() >= getSpellTier(item).getMaxSpells()) return false;
        if(!currentSpells.contains(spell.getId())) currentSpells.add(spell.getId());
        meta.getPersistentDataContainer().set(ItemHandler.SPELLS, PersistentDataType.STRING, GsonHandler.toJson(currentSpells));
        item.setItemMeta(meta);
        ItemHandler.updateItem(item);
        return true;
    }

    public static void removeSpell(BaseSpell spell, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(ItemHandler.SPELLS, PersistentDataType.STRING)) return;
        ArrayList<String> currentSpells = GsonHandler.fromJson(meta.getPersistentDataContainer().get(ItemHandler.SPELLS, PersistentDataType.STRING), STRING_LIST_TYPE);
        if(currentSpells.contains(spell.getId())) currentSpells.remove(spell.getId());
        meta.getPersistentDataContainer().set(ItemHandler.SPELLS, PersistentDataType.STRING, GsonHandler.toJson(currentSpells));
        item.setItemMeta(meta);
        ItemHandler.updateItem(item);
    }

    public static List<String> getSpellSet(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(ItemHandler.SPELLS, PersistentDataType.STRING)) return new ArrayList<String>();
        return GsonHandler.fromJson(meta.getPersistentDataContainer().get(ItemHandler.SPELLS, PersistentDataType.STRING), STRING_LIST_TYPE);
    }

    public static boolean hasSpell(BaseSpell spell, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(ItemHandler.SPELLS, PersistentDataType.STRING)) return false;
        ArrayList<String> currentSpells = GsonHandler.fromJson(meta.getPersistentDataContainer().get(ItemHandler.SPELLS, PersistentDataType.STRING), STRING_LIST_TYPE);
        return currentSpells.contains(spell.getId());
    }

    public static void selectSpell(BaseSpell spell, ItemStack item) {
        ItemHandler.modifyStorage(item, "currentSpell", spell.getId());
        ItemHandler.updateItem(item);
    }

    public static BaseSpell getSelectedSpell(ItemStack item) {
        if(ItemHandler.getStorage(item, "currentSpell") != null) return getSpellById(ItemHandler.getStorage(item, "currentSpell"));
        return null;
    }

    public static void cycleNextSpell(ItemStack item) {
        BaseSpell currentSpell = getSelectedSpell(item);
        List<String> spellSet = getSpellSet(item);

        int spellIndex = spellSet.indexOf(currentSpell != null ? currentSpell.getId() : "");
        if((spellIndex + 1) > (spellSet.size() - 1)) spellIndex = 0;

        selectSpell(getSpellById(spellSet.get((spellIndex + 1))), item);
        ItemHandler.updateItem(item);
    }

    public static SpellTier getSpellTier(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(ItemHandler.ABILITIES, PersistentDataType.STRING)) return SpellTier.NOVICE;
        List<String> abilityIDs = GsonHandler.fromJson(meta.getPersistentDataContainer().get(ItemHandler.ABILITIES, PersistentDataType.STRING), STRING_LIST_TYPE);

        for(SpellTier tier : SpellTier.values()) {
            for(String id : abilityIDs) {
                if(id.contains(tier.name().toLowerCase())) {
                    return tier;
                }
            }
        }
        return SpellTier.NOVICE;
    }

    public enum SpellTier {
        NOVICE(3),
        APPRENTICE(4),
        ACQUAINTANCE(5),
        MAGE(6),
        ARCHMAGE(7),
        ANNIHILATION(8);

        private final int maxSpells;

        SpellTier(int maxSpells) {
            this.maxSpells = maxSpells;
        }

        public int getMaxSpells() {
            return maxSpells;
        }
    }
}
