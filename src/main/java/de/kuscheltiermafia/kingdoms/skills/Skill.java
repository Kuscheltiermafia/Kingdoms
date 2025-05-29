package de.kuscheltiermafia.kingdoms.skills;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Skill {
    COMBAT("combat", "Combat", "⚔", ChatColor.RED, Material.DIAMOND_SWORD),
    MINING("mining", "Mining", "⛏", ChatColor.GRAY, Material.IRON_PICKAXE),
    FORAGING("foraging", "Foraging", "🌳", ChatColor.GREEN, Material.WOODEN_AXE),
    ENCHANTING("enchanting", "Enchanting", "✎", ChatColor.AQUA, Material.ENCHANTED_BOOK),
    FARMING("farming", "Farming", "🌾", ChatColor.YELLOW, Material.NETHERITE_HOE),
    FISHING("fishing", "Fishing", "🐟", ChatColor.BLUE, Material.FISHING_ROD),
    BUILDING("building", "Building", "🏗", ChatColor.DARK_GRAY, Material.SHELTER_POTTERY_SHERD),
    ALCHEMY("alchemy", "Alchemy", "⚗", ChatColor.LIGHT_PURPLE, Material.POTION),
    WIZARDRY("wizardry", "Wizardry", "௹", ChatColor.DARK_PURPLE, Material.BLAZE_ROD),
    TINKERING("tinkering", "Tinkering", "🔧", ChatColor.GOLD, Material.HEAVY_CORE),
    HUNTING("hunting", "Hunting", "🏹", ChatColor.DARK_GREEN, Material.BOW),;

    final String icon;
    final String displayName;
    final String codeID;
    final ChatColor color;
    final Material icon_item;

    Skill(String codeID, String displayName, String icon, ChatColor color, Material icon_item) {
        this.displayName = displayName;
        this.icon = icon;
        this.codeID = codeID;
        this.color = color;
        this.icon_item = icon_item;
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

    public Material getIconItem() {
        return icon_item;
    }

}
