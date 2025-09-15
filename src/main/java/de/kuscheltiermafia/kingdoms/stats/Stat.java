package de.kuscheltiermafia.kingdoms.stats;

import org.bukkit.ChatColor;

public enum Stat {
    HEALTH("health", "Health", "❤", ChatColor.GREEN),
    DEFENSE("defense","Defense", "⛨", ChatColor.GRAY),
    DAMAGE("damage","Damage", "⚔", ChatColor.DARK_RED),
    STRENGTH("strength","Strength", "⚒", ChatColor.DARK_RED),
    CRIT_CHANCE("critChance","Crit Chance", "☣", ChatColor.BLUE),
    CRIT_DAMAGE("critDamage","Crit Damage", "☠", ChatColor.DARK_RED),
    FEROCITY("ferocity","Ferocity", "🗡", ChatColor.RED),
    LIFESTEAL("lifesteal","Lifesteal", "ৎ", ChatColor.DARK_RED),
    INTELLIGENCE("intelligence","Intelligence", "✎", ChatColor.AQUA),
    MANA_REGENERATION("manaRegeneration","Mana Regeneration", "ɸ", ChatColor.LIGHT_PURPLE),
    SPELLBOUND("spellbound","Spellbound", "ꖦ", ChatColor.DARK_PURPLE),
    MANA_STEAL("manaSteal","Mana Steal", "ᛃ", ChatColor.DARK_PURPLE),
    SPEED("speed","Speed", "⚡", ChatColor.WHITE),
    LUCK("luck","Luck", "♠", ChatColor.GREEN),
    VITALITY("vitality","Vitality", "❁", ChatColor.RED),
    OVERHEAL("overheal","Overheal", "❣", ChatColor.LIGHT_PURPLE),
    ABSORPTION("absorption","Absorption", "⬡", ChatColor.GOLD),
    VEIL("veil","Veil", "ღ", ChatColor.DARK_GRAY),
    BREAKING_SPEED("breakingSpeed","Breaking Speed", "⛏", ChatColor.YELLOW),
    FORTUNE("fortune","Fortune", "☘", ChatColor.GOLD),
    SPREAD("spread","Spread", "■", ChatColor.DARK_GREEN),;

    final String icon;
    final String displayName;
    final String codeID;
    final ChatColor color;

    Stat(String codeID, String displayName, String icon, ChatColor color) {
        this.displayName = displayName;
        this.icon = icon;
        this.codeID = codeID;
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCodeID() {
        return codeID;
    }

    public ChatColor getColor() {
        return color;
    }

    public static Stat getStatByID(String id) {
        for(Stat stat : Stat.values()) {
            if(stat.getCodeID().equalsIgnoreCase(id)) {
                return stat;
            }
        }
        return null;
    }
}
