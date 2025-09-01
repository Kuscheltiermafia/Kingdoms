package de.kuscheltiermafia.kingdoms.items;

import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.utils.GsonHandler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static de.kuscheltiermafia.kingdoms.items.ItemHandler.itemList;

public class ItemBuilder {

    String customName;
    String id;
    Material material;
    int maxStackSize;
    List<String> lore;
    boolean glint;
    boolean hideTooltip;
    boolean hideAdditionalTooltip;

    HashMap<Stat, Float> stats;

    boolean showInList;

    public ItemBuilder() {
        this.stats = new HashMap<>();
        this.lore = new ArrayList<>();
        this.maxStackSize = 64;
    }

    public ItemBuilder setID(String id) {
        this.id = id;
        return this;
    }

    public ItemBuilder setCustomName(String name) {
        this.customName = name;
        return this;
    }

    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setMaxStackSize(int size) {
        this.maxStackSize = size;
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        this.lore.add(line);
        return this;
    }

    public ItemBuilder addGlint() {
        this.glint = true;
        return this;
    }

    public ItemBuilder hideTooltip() {
        this.hideTooltip = true;
        return this;
    }

    public ItemBuilder hideAdditionalTooltip() {
        this.hideAdditionalTooltip = true;
        return this;
    }

    public ItemBuilder show() {
        this.showInList = true;
        return this;
    }

    //Methods to add Stats
    public ItemBuilder setHealth(float value) {
        stats.put(Stat.HEALTH, value);
        return this;
    }
    public ItemBuilder setDefense(float value) {
        stats.put(Stat.DEFENSE, value);
        return this;
    }
    public ItemBuilder setDamage(float value) {
        stats.put(Stat.DAMAGE, value);
        return this;
    }
    public ItemBuilder setStrength(float value) {
        stats.put(Stat.STRENGTH, value);
        return this;
    }
    public ItemBuilder setCritChance(float value) {
        stats.put(Stat.CRIT_CHANCE, value);
        return this;
    }
    public ItemBuilder setCritDamage(float value) {
        stats.put(Stat.CRIT_DAMAGE, value);
        return this;
    }
    public ItemBuilder setIntelligence(float value) {
        stats.put(Stat.INTELLIGENCE, value);
        return this;
    }
    public ItemBuilder setManaRegeneration(float value) {
        stats.put(Stat.MANA_REGENERATION, value);
        return this;
    }
    public ItemBuilder setSpellbound(float value) {
        stats.put(Stat.SPELLBOUND, value);
        return this;
    }
    public ItemBuilder setSpeed(float value) {
        stats.put(Stat.SPEED, value);
        return this;
    }
    public ItemBuilder setLuck(float value) {
        stats.put(Stat.LUCK, value);
        return this;
    }
    public ItemBuilder setOverheal(float value) {
        stats.put(Stat.OVERHEAL, value);
        return this;
    }
    public ItemBuilder setAbsorption(float value) {
        stats.put(Stat.ABSORPTION, value);
        return this;
    }
    public ItemBuilder setVeil(float value) {
        stats.put(Stat.VEIL, value);
        return this;
    }
    public ItemBuilder setBreakingSpeed(float value) {
        stats.put(Stat.BREAKING_SPEED, value);
        return this;
    }
    public ItemBuilder setFortune(float value) {
        stats.put(Stat.FORTUNE, value);
        return this;
    }

    public ItemStack build() {
        ItemStack genItem = new ItemStack(material);
        ItemMeta meta = genItem.getItemMeta();

        meta.setMaxStackSize(maxStackSize);
        meta.getPersistentDataContainer().set(ItemHandler.ID, PersistentDataType.STRING, id);
        meta.setDisplayName(customName);
        meta.setLore(lore);
        meta.setEnchantmentGlintOverride(glint);
        meta.setHideTooltip(hideTooltip);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        if (hideAdditionalTooltip) {
            meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        }

        if(!stats.isEmpty()) {
            meta.getPersistentDataContainer().set(ItemHandler.STATS, PersistentDataType.STRING, GsonHandler.toJson(stats));
        }

        genItem.setItemMeta(meta);

        if(showInList) {
            itemList.add(genItem);
        }

        return genItem;
    }
}
