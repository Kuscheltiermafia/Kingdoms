package de.kuscheltiermafia.kingdoms.stats;

public enum Stat {
    HEALTH("Health", "❤"),
    DEFENSE("Defense", "⛨"),
    INTELLIGENCE("Intelligence", "✎"),
    MANA_REGENERATION("Mana Regeneration", "ɸ"),
    CRIT_CHANCE("Crit Chance", "☣"),
    CRIT_DAMAGE("Crit Damage", "☠"),
    DAMAGE("Damage", "⚔"),
    STRENGTH("Strength", "⚒"),
    SPEED("Speed", "⚡"),
    LUCK("Luck", "♠"),
    BREAKING_SPEED("Breaking Speed", "⛏"),
    FORTUNE("Fortune", "☘"),
    SPELLBOUND("Spellbound", "۞");

    final String icon;
    final String displayName;

    Stat(String displayName, String icon) {
        this.displayName = displayName;
        this.icon = icon;

        System.out.println("Loaded Stats");
    }
}
