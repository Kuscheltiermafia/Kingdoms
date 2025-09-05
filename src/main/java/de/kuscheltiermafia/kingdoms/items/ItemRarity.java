package de.kuscheltiermafia.kingdoms.items;

import org.bukkit.ChatColor;

public enum ItemRarity {

    COMMON("Common", "common", ChatColor.WHITE),
    UNCOMMON("Uncommon", "uncommon", ChatColor.GREEN),
    RARE("Rare", "rare", ChatColor.BLUE),
    EPIC("Epic", "epic", ChatColor.DARK_PURPLE),
    LEGENDARY("Legendary", "legendary", ChatColor.GOLD),
    MYTHIC("Mythic", "mythic", ChatColor.LIGHT_PURPLE),
    MYSTIC("Mystic", "mystic", ChatColor.DARK_RED),
    DIVINE("Divine", "divine", ChatColor.AQUA),
    GODLY("Godly", "godly", ChatColor.RED),
    SPECIAL("Special", "special", ChatColor.YELLOW);

    final String displayName;
    final ChatColor color;
    final String id;

    ItemRarity(String displayName, String id, ChatColor color) {
        this.displayName = displayName;
        this.color = color;
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public ChatColor getColor() {
        return color;
    }

    public static ItemRarity getById(String id) {
        for(ItemRarity rarity : ItemRarity.values()) {
            if(rarity.getId().equalsIgnoreCase(id)) {
                return rarity;
            }
        }
        return COMMON;
    }
}
