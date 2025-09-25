package de.kuscheltiermafia.kingdoms.items;

import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.*;

import static de.kuscheltiermafia.kingdoms.items.ItemHandler.itemList;

public class ItemBuilder {

    String customName;
    String id;
    ItemRarity rarity;
    ItemType type;
    ItemLevel level;
    int currentLevelProgress;
    Material material;
    String skullTexture;
    int maxStackSize = 64;
    List<String> lore;
    boolean glint;
    boolean hideTooltip;
    boolean hideAdditionalTooltip;
    boolean hideRarity;
    boolean hideType;

    HashMap<Stat, Double> stats;
    HashMap<Gemstone, Integer> gemstones;
    Set<String> abilityKeys;

    boolean showInList;
    boolean tempItem;

    public ItemBuilder() {
        this.lore = new ArrayList<>();
        this.stats = new HashMap<>();
        this.abilityKeys = new HashSet<>();
        this.gemstones = new HashMap<>();
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

    public ItemBuilder setSkullTexture(String texture) {
        this.skullTexture = texture;
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
    public ItemBuilder setHealth(double value) {
        stats.put(Stat.HEALTH, value);
        return this;
    }
    public ItemBuilder setDefense(double value) {
        stats.put(Stat.DEFENSE, value);
        return this;
    }
    public ItemBuilder setDamage(double value) {
        stats.put(Stat.DAMAGE, value);
        return this;
    }
    public ItemBuilder setStrength(double value) {
        stats.put(Stat.STRENGTH, value);
        return this;
    }
    public ItemBuilder setCritChance(double value) {
        stats.put(Stat.CRIT_CHANCE, value);
        return this;
    }
    public ItemBuilder setCritDamage(double value) {
        stats.put(Stat.CRIT_DAMAGE, value);
        return this;
    }
    public ItemBuilder setIntelligence(double value) {
        stats.put(Stat.INTELLIGENCE, value);
        return this;
    }
    public ItemBuilder setManaRegeneration(double value) {
        stats.put(Stat.MANA_REGENERATION, value);
        return this;
    }
    public ItemBuilder setSpellbound(double value) {
        stats.put(Stat.SPELLBOUND, value);
        return this;
    }
    public ItemBuilder setSpeed(double value) {
        stats.put(Stat.SPEED, value);
        return this;
    }
    public ItemBuilder setLuck(double value) {
        stats.put(Stat.LUCK, value);
        return this;
    }
    public ItemBuilder setOverheal(double value) {
        stats.put(Stat.OVERHEAL, value);
        return this;
    }
    public ItemBuilder setAbsorption(double value) {
        stats.put(Stat.ABSORPTION, value);
        return this;
    }
    public ItemBuilder setVeil(double value) {
        stats.put(Stat.VEIL, value);
        return this;
    }
    public ItemBuilder setBreakingSpeed(double value) {
        stats.put(Stat.BREAKING_SPEED, value);
        return this;
    }
    public ItemBuilder setFortune(double value) {
        stats.put(Stat.FORTUNE, value);
        return this;
    }
    public ItemBuilder setSpread(double value) {
        stats.put(Stat.SPREAD, value);
        return this;
    }
    public ItemBuilder setLifesteal(double value) {
        stats.put(Stat.LIFESTEAL, value);
        return this;
    }

    public ItemBuilder addAbilityKey(String abilityKey) {
        this.abilityKeys.add(abilityKey);
        return this;
    }

    public ItemBuilder setRarity(ItemRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public ItemBuilder setType(ItemType type) {
        this.type = type;
        return this;
    }

    public ItemBuilder setLevel(ItemLevel level) {
        this.level = level;
        return this;
    }

    public ItemBuilder setGemstone(Gemstone gemstone, int grade) {
        this.gemstones.put(gemstone, grade);
        return this;
    }

    public ItemBuilder setCurrentLevelProgress(int progress) {
        this.currentLevelProgress = progress;
        return this;
    }

    public ItemBuilder hideRarity() {
        this.hideRarity = true;
        return this;
    }

    public ItemBuilder hideType() {
        this.hideType = true;
        return this;
    }

    public ItemBuilder temporary(boolean hideNormals) {
        this.tempItem = true;
        if(hideNormals) {
            this.hideRarity = true;
            this.hideType = true;
        }
        return this;
    }

    public ItemStack build() {
        ItemStack genItem = new ItemStack(material);
        ItemMeta meta = genItem.getItemMeta();

        if(this.lore == null) {
            this.lore = new ArrayList<>();
        }

        if (this.maxStackSize <= 0) {
            this.maxStackSize = 1;
        }

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

        if(skullTexture != null && !skullTexture.isEmpty() && material.equals(Material.PLAYER_HEAD)) {
            SkullMeta skullMeta = (SkullMeta) meta;
            try {
                PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
                PlayerTextures textures = profile.getTextures();
                URL newTexture = new URL(new String(Base64.getDecoder().decode(skullTexture)).substring(28).replace("}}}", "").replace('"', ' ').trim());
                textures.setSkin(newTexture);
                profile.setTextures(textures);
                skullMeta.setOwnerProfile(profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            meta = skullMeta;
        }

        if(ItemLevel.canLevel(type)) {
            meta.getPersistentDataContainer().set(ItemHandler.LEVEL, PersistentDataType.STRING, level != null ? level.levelID : ItemLevel.BASIC.levelID);
            meta.getPersistentDataContainer().set(ItemHandler.CURRENT_LEVEL_PROGRESS, PersistentDataType.INTEGER, currentLevelProgress != 0 ? currentLevelProgress : 0);

            meta.getPersistentDataContainer().set(ItemHandler.GEMSTONES, PersistentDataType.STRING, gemstones != null || !gemstones.isEmpty() ? GsonHandler.toJson(gemstones) : GsonHandler.toJson(new HashMap<Gemstone, Integer>()));
        }

        if(!stats.isEmpty()) {
            meta.getPersistentDataContainer().set(ItemHandler.STATS, PersistentDataType.STRING, GsonHandler.toJson(stats));
        }

        if(!abilityKeys.isEmpty()) {
            meta.getPersistentDataContainer().set(ItemHandler.ABILITIES, PersistentDataType.STRING, GsonHandler.toJson(abilityKeys));
        }

        if(rarity != null) {
            meta.getPersistentDataContainer().set(ItemHandler.RARITY, PersistentDataType.STRING, rarity.getId());
            meta.setDisplayName(rarity.color + customName);
        } else {
            meta.getPersistentDataContainer().set(ItemHandler.RARITY, PersistentDataType.STRING, ItemRarity.COMMON.getId());
            meta.setDisplayName(ItemRarity.COMMON.color + customName);
        }

        if(type != null) {
            meta.getPersistentDataContainer().set(ItemHandler.TYPE, PersistentDataType.STRING, type.getId());
        } else {
            meta.getPersistentDataContainer().set(ItemHandler.TYPE, PersistentDataType.STRING, ItemType.MISC.getId());
        }

        if(hideRarity) ItemHandler.modifyStorage(genItem, "hideRarity", "true");
        if(hideType) ItemHandler.modifyStorage(genItem, "hideType", "true");

        genItem.setItemMeta(meta);
        ItemHandler.updateItem(genItem);

        if(showInList) {
            itemList.add(genItem);
        }

        if(!tempItem) {
            ItemHandler.itemMap.put(id, genItem);
        }
        return genItem;
    }
}
