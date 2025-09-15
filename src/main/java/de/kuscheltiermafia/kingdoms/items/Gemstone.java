package de.kuscheltiermafia.kingdoms.items;

import com.google.common.reflect.TypeToken;
import de.kuscheltiermafia.kingdoms.mobs.MobFamily;
import de.kuscheltiermafia.kingdoms.stats.ItemStatCalculator;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public enum Gemstone {

    RUBY("Ruby", "ruby", ChatColor.RED, "♡", Stat.HEALTH, MobFamily.UNDEAD),
    SAPPHIRE("Sapphire", "sapphire", ChatColor.BLUE, "⸙", Stat.MANA_REGENERATION, MobFamily.WITHERED),
    TIGERS_EYE("Tiger's Eye", "tiger_eye", ChatColor.GREEN, "❂", Stat.CRIT_CHANCE, MobFamily.BEAST),
    OBSIDIAN("Obsidian", "obsidian", ChatColor.DARK_PURPLE, "⛥", Stat.STRENGTH, MobFamily.VOIDBORN),
    AMETHYST("Amethyst", "amethyst", ChatColor.LIGHT_PURPLE, "⚔", Stat.CRIT_DAMAGE, MobFamily.ENDEST),
    CELESTITE("Celestite", "celestite", ChatColor.AQUA, "☆", Stat.SPEED, MobFamily.WINGED),
    TOPAZ("Topaz", "topaz", ChatColor.GOLD, "♦", Stat.DEFENSE, MobFamily.BROKEN),
    HEMATITE("Hematite", "hematite", ChatColor.DARK_RED, "⏹", Stat.SPREAD, MobFamily.TOIL),
    AQUAMARINE("Aquamarine", "aquamarine", ChatColor.BLUE, "۞", Stat.MANA_STEAL, MobFamily.AQUATIC),
    QUARTZ("Quartz", "quartz", ChatColor.DARK_RED, "ᦠ", Stat.VITALITY, MobFamily.HUMANOID),
    AMBER("Amber", "amber", ChatColor.YELLOW, "☘", Stat.FORTUNE, MobFamily.ARTHROPOD),
    MOLDAVITE("Moldavite", "moldavite", ChatColor.DARK_GRAY, "⚛", Stat.LIFESTEAL, MobFamily.REANIMATED),
    SODALITE("Sodalite", "sodalite", ChatColor.DARK_AQUA, "ᛃ", Stat.FEROCITY, MobFamily.PSYCHIC),
    IOLITE("Iolite", "iolite", ChatColor.DARK_BLUE, "⚚", Stat.INTELLIGENCE, MobFamily.ARCANE),
    FIRE_OPAL("Fire Opal", "fire_opal", ChatColor.DARK_RED, "†", Stat.DAMAGE, MobFamily.INFERNAL),
    SUNSTONE("Sunstone", "sunstone", ChatColor.GOLD, "Ϟ", Stat.SPELLBOUND, MobFamily.CELESTIAL),
    PEARL("Pearl", "pearl", ChatColor.WHITE, "⛏", Stat.BREAKING_SPEED, MobFamily.ROCKY),
    SERPENTINE("Serpentine", "serpentine", ChatColor.GREEN, "࿊", Stat.OVERHEAL, MobFamily.PLAGUED),
    JADE("Jade", "jade", ChatColor.DARK_GREEN, "⃠", Stat.VEIL, MobFamily.GUARDIAN),
    SMOKY_QUARTZ("Smoky Quartz", "smokey_quartz", ChatColor.DARK_GRAY, "⛨", Stat.ABSORPTION, MobFamily.DEPTHS),
    FLUORITE("Fluorite", "fluorite", ChatColor.LIGHT_PURPLE, "🍀", Stat.LUCK, MobFamily.SPECTRAL),
    SPINEL("Spinel", "spinel", ChatColor.RED, "ⵥ", Stat.MANA_STEAL, MobFamily.DEMONIC),
    PYRITE("Pyrite", "pyrite", ChatColor.YELLOW, "✠", Stat.DEFENSE, MobFamily.CONSTRUCT),
    KUNZITE("Kunzite", "kunzite", ChatColor.LIGHT_PURPLE, "☣", Stat.FEROCITY, MobFamily.TIMEWARPED),
    MOONSTONE("Moonstone", "moonstone", ChatColor.AQUA, "⚒", Stat.BREAKING_SPEED, MobFamily.LUNAR),
    LABRADORITE("Labradorite", "labradorite", ChatColor.DARK_BLUE, "࿈", Stat.DAMAGE, MobFamily.SHADOW),
    RHODOCHROSITE("Rhodochrosite", "rhodochrosite", ChatColor.RED, "☦", Stat.STRENGTH, MobFamily.MANIAC),
    CHRYSOBERYL("Chrysoberyl", "chrysoberyl", ChatColor.BLUE, "⌁", Stat.INTELLIGENCE, MobFamily.GHOSTLY),;

    final String displayName;
    final String codeID;
    final ChatColor color;
    final String icon;
    final Stat stat;
    final MobFamily family;

    Gemstone(String displayName, String codeID, ChatColor color, String icon, Stat stat, MobFamily family) {
        this.displayName = displayName;
        this.codeID = codeID;
        this.color = color;
        this.icon = icon;
        this.stat = stat;
        this.family = family;
    }

    public static HashMap<Gemstone, Integer> returnGemGrade(ItemStack item) {
        return GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.GEMSTONES, PersistentDataType.STRING), new TypeToken<HashMap<Gemstone, Integer>>() {}.getType()) == null ? new HashMap<>() : GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.GEMSTONES, PersistentDataType.STRING), new TypeToken<HashMap<Gemstone, Integer>>() {}.getType());
    }
    public static HashMap<Gemstone, Integer> returnGemGrade(ItemMeta meta) {
        return GsonHandler.fromJson(meta.getPersistentDataContainer().get(ItemHandler.GEMSTONES, PersistentDataType.STRING), new TypeToken<HashMap<Gemstone, Integer>>() {}.getType());
    }

    public static ChatColor returnGemGradeColor(int grade) {
        if(grade == 0) {
            return ChatColor.DARK_RED;
        } else if(grade >= 1 && grade <= 35) {
            return ChatColor.RED;
        } else if(grade >= 36 && grade <= 64) {
            return ChatColor.BLUE;
        } else if(grade <= 99 && grade >= 65) {
            return ChatColor.LIGHT_PURPLE;
        } else if(grade == 100) {
            return ChatColor.AQUA;
        }
        return ChatColor.DARK_GRAY;
    }

    public static void modifyGemGrade(ItemStack item, Gemstone gemstone, int grade, boolean remove) {
        ItemMeta meta = item.getItemMeta();
        HashMap<Gemstone, Integer> gemstoneMap = GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.GEMSTONES, PersistentDataType.STRING), new TypeToken<HashMap<Gemstone, Integer>>() {}.getType());
        if(gemstoneMap == null) gemstoneMap = new HashMap<>();
        gemstoneMap.put(gemstone, grade);
        if(remove) gemstoneMap.remove(gemstone);
        meta.getPersistentDataContainer().set(ItemHandler.GEMSTONES, PersistentDataType.STRING, GsonHandler.toJson(gemstoneMap));
        item.setItemMeta(meta);
        ItemHandler.updateItem(item);
    }

    public static void updateGemGradeStatBoosts(ItemStack item) {
        HashMap<Gemstone, Integer> gemGradeSet = returnGemGrade(item);
        if(gemGradeSet != null && !gemGradeSet.isEmpty()) {
            for(Gemstone gemstone : gemGradeSet.keySet()) {
                if(gemstone == null) continue;
                try {
                    int grade = gemGradeSet.get(gemstone);
                    ItemStatCalculator.addAdditiveModifier(item, gemstone.getStat(), gemstone.getCodeID(), grade);
                }catch (Exception e) {
                    continue;
                }
            }
        }
    }

    public static Gemstone getByCodeID(String codeID) {
        for(Gemstone gemstone : Gemstone.values()) {
            if(gemstone.codeID.equalsIgnoreCase(codeID)) {
                return gemstone;
            }
        }
        return null;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Stat getStat() {
        return stat;
    }

    public String getCodeID() {
        return codeID;
    }
}
