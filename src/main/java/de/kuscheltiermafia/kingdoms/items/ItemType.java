package de.kuscheltiermafia.kingdoms.items;

import org.bukkit.ChatColor;

public enum ItemType {
    SWORD("Sword", "sword", ChatColor.RED, "⚔"),
    BOW("Bow", "bow", ChatColor.GREEN, "🏹"),
    SPELLCASTER("Spellcaster", "spellcaster", ChatColor.LIGHT_PURPLE, "🔮"),
    DAGGER("Dagger", "dagger", ChatColor.DARK_AQUA, "🗡"),
    AXE("Axe", "axe", ChatColor.GOLD, "🪓"),
    HELMET("Helmet", "helmet", ChatColor.BLUE, "🪖"),
    CHESTPLATE("Chestplate", "chestplate", ChatColor.BLUE, "🛡"),
    LEGGINGS("Leggings", "leggings", ChatColor.BLUE, "👖"),
    BOOTS("Boots", "boots", ChatColor.BLUE, "👢"),
    PICKAXE("Pickaxe", "pickaxe", ChatColor.YELLOW, "⛏"),
    SHOVEL("Shovel", "shovel", ChatColor.YELLOW, "🪤"),
    HOE("Hoe", "hoe", ChatColor.YELLOW, "🌾"),
    GAUNTLET("Gauntlet", "gauntlet", ChatColor.DARK_RED, "🥊"),
    INGREDIENT("Crafting Ingredient", "ingredient", ChatColor.GRAY, "🪵"),
    MISC("Miscellaneous Item", "misc", ChatColor.WHITE, "📦");

    final String displayName;
    final ChatColor color;
    final String icon;
    final String codeId;

    ItemType(String displayName, String id, ChatColor color, String icon) {
        this.displayName = displayName;
        this.color = color;
        this.icon = icon;
        this.codeId = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return codeId;
    }

    public static ItemType getById(String id) {
        for(ItemType type : ItemType.values()) {
            if(type.getId().equalsIgnoreCase(id)) {
                return type;
            }
        }
        return MISC;
    }

}
