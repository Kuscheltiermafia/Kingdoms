package de.kuscheltiermafia.kingdoms.stats;

import org.bukkit.ChatColor;

public enum Stat {
    HEALTH("health", "Health", "❤", ChatColor.GREEN),
    DEFENSE("defense","Defense", "⛨", ChatColor.GRAY),
    INTELLIGENCE("intelligence","Intelligence", "✎", ChatColor.AQUA),
    MANA_REGENERATION("manaRegeneration","Mana Regeneration", "ɸ", ChatColor.LIGHT_PURPLE),
    CRIT_CHANCE("critChance","Crit Chance", "☣", ChatColor.BLUE),
    CRIT_DAMAGE("critDamage","Crit Damage", "☠", ChatColor.DARK_RED),
    DAMAGE("damage","Damage", "⚔", ChatColor.DARK_RED),
    STRENGTH("strength","Strength", "⚒", ChatColor.DARK_RED),
    SPEED("speed","Speed", "⚡", ChatColor.WHITE),
    LUCK("luck","Luck", "♠", ChatColor.GREEN),
    BREAKING_SPEED("breakingSpeed","Breaking Speed", "⛏", ChatColor.YELLOW),
    FORTUNE("fortune","Fortune", "☘", ChatColor.GOLD),
    SPELLBOUND("spellbound","Spellbound", "۞", ChatColor.DARK_PURPLE);

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
}
