package de.kuscheltiermafia.kingdoms.items;

import org.bukkit.ChatColor;
import org.bukkit.inventory.EquipmentSlot;

public enum ItemType {
    SWORD("Sword", "sword", ChatColor.RED, "⚔", EquipmentSlot.HAND),
    BOW("Bow", "bow", ChatColor.GREEN, "🏹", EquipmentSlot.HAND),
    SPELLCASTER("Spellcaster", "spellcaster", ChatColor.LIGHT_PURPLE, "🔮", EquipmentSlot.HAND),
    DAGGER("Dagger", "dagger", ChatColor.DARK_AQUA, "🗡", EquipmentSlot.HAND),
    AXE("Axe", "axe", ChatColor.GOLD, "🪓", EquipmentSlot.HAND),
    HELMET("Helmet", "helmet", ChatColor.BLUE, "🪖", EquipmentSlot.HEAD),
    CHESTPLATE("Chestplate", "chestplate", ChatColor.BLUE, "🛡", EquipmentSlot.CHEST),
    LEGGINGS("Leggings", "leggings", ChatColor.BLUE, "👖", EquipmentSlot.LEGS),
    BOOTS("Boots", "boots", ChatColor.BLUE, "👢", EquipmentSlot.FEET),
    PICKAXE("Pickaxe", "pickaxe", ChatColor.YELLOW, "⛏", EquipmentSlot.HAND),
    SHOVEL("Shovel", "shovel", ChatColor.YELLOW, "🪤", EquipmentSlot.HAND),
    HOE("Hoe", "hoe", ChatColor.YELLOW, "🌾", EquipmentSlot.HAND),
    GAUNTLET("Gauntlet", "gauntlet", ChatColor.DARK_RED, "🥊", EquipmentSlot.HAND, EquipmentSlot.OFF_HAND),
    INGREDIENT("Crafting Ingredient", "ingredient", ChatColor.GRAY, "🪵"),
    MISC("Miscellaneous Item", "misc", ChatColor.WHITE, "📦");

    final String displayName;
    final ChatColor color;
    final String icon;
    final String codeId;
    final EquipmentSlot[] validSlots;

    ItemType(String displayName, String id, ChatColor color, String icon, EquipmentSlot... validSlots) {
        this.displayName = displayName;
        this.color = color;
        this.icon = icon;
        this.codeId = id;
        this.validSlots = validSlots;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return codeId;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    public EquipmentSlot[] getValidSlots() {
        return validSlots;
    }

    public static ItemType getById(String id) {
        for(ItemType type : ItemType.values()) {
            if(type.getId().equalsIgnoreCase(id)) {
                return type;
            }
        }
        return MISC;
    }

    public boolean isValidSlot(EquipmentSlot slot) {
        for(EquipmentSlot validSlot : validSlots) {
            if(validSlot == slot) {
                return true;
            }
        }
        return false;
    }
}
