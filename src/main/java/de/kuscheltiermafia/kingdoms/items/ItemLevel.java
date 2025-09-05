package de.kuscheltiermafia.kingdoms.items;

import de.kuscheltiermafia.kingdoms.stats.ItemStatCalculator;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;


public enum ItemLevel {

    BASIC("Basic", "basic", "Basic ", ChatColor.GRAY),
    FORGED("Forged", "forged", "Forged ", ChatColor.BLUE),
    STRENGTHENED("Strengthened", "strengthened", "Strengthened ", ChatColor.DARK_RED),
    FUSED("Fused", "fused", "Fused ", ChatColor.GOLD),
    EMPOWERED("Empowered", "empowered", "Empowered ", ChatColor.LIGHT_PURPLE),
    ENLIGHTENED("Enlightened", "enlightened", "Enlighted ", ChatColor.AQUA),
    ATOMBOUND("Atombound", "atombound", "Atombound ", ChatColor.DARK_PURPLE);


    final String displayName;
    final String levelID;
    final String Prefix;
    final ChatColor color;

    ItemLevel(String displayName, String levelID, String Prefix, ChatColor color) {
        this.displayName = displayName;
        this.levelID = levelID;
        this.Prefix = Prefix;
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPrefix() {
        return Prefix;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getLevelID() {
        return levelID;
    }

    public static ItemLevel getLevelbyID(String id) {
        for(ItemLevel level : ItemLevel.values()) {
            if(level.getLevelID().equals(id)) {
                return level;
            }
        }
        return null;
    }

    public static void setLevel(ItemStack item, ItemLevel level) {
        if(item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().has(ItemHandler.LEVEL)) {
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(ItemHandler.LEVEL, PersistentDataType.STRING, level.getLevelID());
            meta.getPersistentDataContainer().set(ItemHandler.CURRENT_LEVEL_PROGRESS, PersistentDataType.INTEGER, 0);
            item.setItemMeta(meta);
            ItemHandler.updateItem(item);
        }
    }

    public static void setLevelProgress(ItemStack item, int progress) {
        if(item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().has(ItemHandler.LEVEL)) {
            ItemMeta meta = item.getItemMeta();
            if(progress < 0) progress = 0;
            if(progress > 10) progress = 10;
            meta.getPersistentDataContainer().set(ItemHandler.CURRENT_LEVEL_PROGRESS, PersistentDataType.INTEGER, progress);
            item.setItemMeta(meta);
            ItemHandler.updateItem(item);
        }
    }

    public static void updateItemLevelBoosts(ItemStack item) {
        if(item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().has(ItemHandler.LEVEL)) {
            ItemLevel level = ItemLevel.getLevelbyID(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.LEVEL, PersistentDataType.STRING));
            int currentProgress = item.getItemMeta().getPersistentDataContainer().get(ItemHandler.CURRENT_LEVEL_PROGRESS, PersistentDataType.INTEGER) != null ? item.getItemMeta().getPersistentDataContainer().get(ItemHandler.CURRENT_LEVEL_PROGRESS, PersistentDataType.INTEGER) : 0;
            if(level == null) return;
            cleanseLevelBoosts(item);
            for(int i = 0; i < ItemLevel.values().length; i++) {
                if(i <= level.ordinal() - 1) {
                    for (Stat stat : Stat.values()) {
                        ItemStatCalculator.addMultiplicativeModifier(item, stat, ItemLevel.values()[i].getDisplayName(), 1.5);
                    }
                }
            }
            for (Stat stat : Stat.values()) {
                ItemStatCalculator.addMultiplicativeModifier(item, stat, "NextLevelProgress", 1 + (0.025 * currentProgress));
            }
        }
    }

    private static void cleanseLevelBoosts(ItemStack item) {
        for (Stat stat : Stat.values()) {
            for(ItemLevel level : ItemLevel.values()) {
                ItemStatCalculator.removeMultiplicativeModifier(item, stat, level.getDisplayName());
            }
        }
        for (Stat stat : Stat.values()) {
            ItemStatCalculator.removeMultiplicativeModifier(item, stat, "NextLevelProgress");
        }
    }

    public static boolean canLevel(ItemType type) {
        List<ItemType> levelableTypes = Arrays.asList(
                ItemType.SWORD,
                ItemType.AXE,
                ItemType.DAGGER,
                ItemType.HELMET,
                ItemType.CHESTPLATE,
                ItemType.BOW,
                ItemType.LEGGINGS,
                ItemType.BOOTS,
                ItemType.SPELLCASTER,
                ItemType.PICKAXE,
                ItemType.SHOVEL,
                ItemType.HOE,
                ItemType.GAUNTLET
        );
        if(levelableTypes.contains(type)) {
            return true;
        }
        return false;
    }
}
