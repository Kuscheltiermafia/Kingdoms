package de.kuscheltiermafia.kingdoms.mobs;

import org.bukkit.ChatColor;

public enum MobFamily {

    //ADD ALL ICONS!!!

    VOIDBORN("Voidborn", "voidborn", "⚚", ChatColor.DARK_PURPLE),
    ENDEST("Endest", "endest", "☢", ChatColor.LIGHT_PURPLE),
    WINGED("Winged", "winged", "☁", ChatColor.AQUA),
    UNDEAD("Undead", "undead", "☣", ChatColor.DARK_GRAY),
    BEAST("Beast", "beast", "⚕", ChatColor.GOLD),
    WITHERED("Withered", "withered", "⛧", ChatColor.DARK_RED),
    TOIL("Toil", "toil", "🪓", ChatColor.GREEN),
    AQUATIC("Aquatic", "aquatic", "💧", ChatColor.BLUE),
    ARTHROPOD("Arthropod", "arthropod", "🕷", ChatColor.YELLOW),
    HUMANOID("Humanoid", "humanoid", "😐", ChatColor.RED),
    BROKEN("Broken", "broken", "o", ChatColor.GRAY),
    REANIMATED("Reanimated", "reanimated", "m", ChatColor.DARK_AQUA),
    CONSTRUCT("Construct", "construct", "c", ChatColor.WHITE),
    GHOSTLY("Ghostly", "ghostly", "g", ChatColor.DARK_BLUE),
    MANIAC("Maniac", "maniac", "i", ChatColor.LIGHT_PURPLE),
    DEMONIC("Demonic", "demonic", "d", ChatColor.DARK_RED),
    ROCKY("Rocky", "rocky", "☭", ChatColor.DARK_GRAY),
    GUARDIAN("Guardian", "guardian", "f", ChatColor.DARK_GREEN),
    DEPTHS("Depths", "depths", "j", ChatColor.BLUE),
    CELESTIAL("Celestial", "celestial", "s", ChatColor.GOLD),
    INFERNAL("Infernal", "infernal", "🔥", ChatColor.RED),
    PLAGUED("Plagued", "plagued", "☣", ChatColor.DARK_GREEN),
    SHADOW("Shadow", "shadow", "∞", ChatColor.DARK_PURPLE),
    ARCANE("Arcane", "arcane", "ᚠ", ChatColor.LIGHT_PURPLE),
    TIMEWARPED("Timewarped", "timewarped", "y", ChatColor.LIGHT_PURPLE),
    PSYCHIC("Psychic", "psychic", "v", ChatColor.RED),
    LUNAR("Lunar", "lunar", "1", ChatColor.AQUA),
    SPECTRAL("Spectral", "spectral", "2", ChatColor.DARK_BLUE),;

    final String displayName;
    final String codeID;
    final String icon;
    final ChatColor color;

    MobFamily(String displayName, String codeID, String icon, ChatColor color) {
        this.displayName = displayName;
        this.codeID = codeID;
        this.icon = icon;
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCodeID() {
        return codeID;
    }

    public String getIcon() {
        return icon;
    }

    public ChatColor getColor() {
        return color;
    }
}
