package de.kuscheltiermafia.kingdoms.items;

import com.google.common.reflect.TypeToken;
import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

import static de.kuscheltiermafia.kingdoms.items.ItemTextBuilder.updateTextMeta;
import static de.kuscheltiermafia.kingdoms.stats.ItemStatCalculator.returnFinalStatMap;

public class ItemHandler {

    public static NamespacedKey ID = new NamespacedKey(Kingdoms.getPlugin(), "kingdoms");
    public static NamespacedKey RARITY = new NamespacedKey(Kingdoms.getPlugin(), "rarity");
    public static NamespacedKey TYPE = new NamespacedKey(Kingdoms.getPlugin(), "type");
    public static NamespacedKey LEVEL = new NamespacedKey(Kingdoms.getPlugin(), "level");
    public static NamespacedKey CURRENT_LEVEL_PROGRESS = new NamespacedKey(Kingdoms.getPlugin(), "current_level_progress");

    public static NamespacedKey STATS = new NamespacedKey(Kingdoms.getPlugin(), "stats");
    public static NamespacedKey FINAL_STATS = new NamespacedKey(Kingdoms.getPlugin(), "final_stats");
    public static NamespacedKey STAT_MULTIPLICATIVE = new NamespacedKey(Kingdoms.getPlugin(), "stat_multiplier");
    public static NamespacedKey STAT_ADDITITVE = new NamespacedKey(Kingdoms.getPlugin(), "stat_addition");

    public static NamespacedKey ABILITIES = new NamespacedKey(Kingdoms.getPlugin(), "abilities");
    public static NamespacedKey SPELLS = new NamespacedKey(Kingdoms.getPlugin(), "spells");
    public static NamespacedKey ENCHANTMENTS = new NamespacedKey(Kingdoms.getPlugin(), "enchantments");
    public static NamespacedKey GEMSTONES = new NamespacedKey(Kingdoms.getPlugin(), "gemstones");
    public static NamespacedKey STORAGE = new NamespacedKey(Kingdoms.getPlugin(), "data_storage");

    public static ArrayList<ItemStack> itemList = new ArrayList<>();
    public static HashMap<String, ItemStack> itemMap = new HashMap<>();

    public static void registerItems() {
        HashMap<String, ItemBuilder> storedItems = GsonHandler.readJsonsFromFileAsMap("items", ItemBuilder.class);
        for(String key : storedItems.keySet()) {
            ItemBuilder builder = storedItems.get(key);
            ItemStack item = builder.setID(key).build();
        }
        registerDebugItems();
    }

    private static void registerDebugItems() {
        new ItemBuilder().setMaterial(Material.BARRIER).setID("no_page_up").setCustomName("§4No next Page available").setMaxStackSize(1).build();
        new ItemBuilder().setMaterial(Material.BARRIER).setID("no_page_down").setCustomName("§4No previous Page available").setMaxStackSize(1).build();
        new ItemBuilder().setMaterial(Material.ARROW).setID("page_up_menuindicator").setCustomName("§aNext Page").setMaxStackSize(1).build();
        new ItemBuilder().setMaterial(Material.ARROW).setID("page_down_menuindicator").setCustomName("§aPrevious Page").setMaxStackSize(1).build();
        new ItemBuilder().setMaterial(Material.ARROW).setID("back_button_menuindicator").setCustomName("§cGo Back").setMaxStackSize(1).build();
        new ItemBuilder().setMaterial(Material.STRUCTURE_VOID).setID("placeholder").setCustomName("§5§l§kA§r§7 PLACEHOLDER §r§5§l§kA").setMaxStackSize(64).build();
        new ItemBuilder().setMaterial(Material.GRAY_STAINED_GLASS_PANE).setID("spacer").hideTooltip().setMaxStackSize(1).build();
    }

    public static ItemStack getItem(String id) {
        if(!itemMap.containsKey(id)) {
            System.out.println("Item with id " + id + " not found!");
            return new ItemStack(Material.BARRIER);
        }
        return itemMap.get(id).clone();
    }

    public static void clearItem(Player p, String id, int amount) {
        ItemStack item = getItem(id);
        item.setAmount(item.getAmount() - amount);
        p.getInventory().removeItem(item);
    }

    public static void updateItem(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        ItemLevel.updateItemLevelBoosts(item);
        Gemstone.updateGemGradeStatBoosts(item);

        HashMap<Stat, Double> statMap = returnFinalStatMap(item);
        meta.getPersistentDataContainer().set(FINAL_STATS, PersistentDataType.STRING, GsonHandler.toJson(statMap));

        HashMap<String, Integer> enchantments = GsonHandler.fromJson(meta.getPersistentDataContainer().get(ENCHANTMENTS, PersistentDataType.STRING), GsonHandler.ENCHANT_MAP_TYPE);
        if(enchantments == null || enchantments.isEmpty()) {
            meta.getPersistentDataContainer().remove(ENCHANTMENTS);
        }

        ArrayList<String> newLore = new ArrayList<>();

        HashMap<String, ItemBuilder> storedItems = GsonHandler.readJsonsFromFileAsMap("items", ItemBuilder.class);
        for(String key : storedItems.keySet()) {
            ItemBuilder builder = storedItems.get(key);
            if(meta.getPersistentDataContainer().get(ID, PersistentDataType.STRING).equals(key)) {
                newLore.addAll(builder.lore);
                break;
            }
        }
        meta.setLore(newLore);
        updateTextMeta(meta);

        item.setItemMeta(meta);
    }

    //Utils
    public static ItemStack convertToDisplayItem(ItemStack toSpacer, int amount) {
        ItemStack spacerItem = new ItemStack(toSpacer);
        ItemMeta meta = spacerItem.getItemMeta();
        assert meta != null;
        meta.getPersistentDataContainer().set(ID, PersistentDataType.STRING, "spacer");
        spacerItem.setItemMeta(meta);
        spacerItem.setAmount(amount);
        return spacerItem;
    }

    public static boolean checkItemID(ItemStack item, String id) {
        try {
            if (item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ID, PersistentDataType.STRING).equals(id)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            return false;
        }
    }

    public static void modifyStorage(ItemStack item, String key, String value) {
        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(STORAGE, PersistentDataType.STRING)) meta.getPersistentDataContainer().set(STORAGE, PersistentDataType.STRING, "{}");
        HashMap<String, String> storageMap = GsonHandler.fromJson(meta.getPersistentDataContainer().get(STORAGE, PersistentDataType.STRING), new TypeToken<HashMap<String, String>>() {}.getType());
        storageMap.put(key, value);
        meta.getPersistentDataContainer().set(STORAGE, PersistentDataType.STRING, GsonHandler.toJson(storageMap));
        item.setItemMeta(meta);
    }

    public static String getStorage(ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(STORAGE, PersistentDataType.STRING)) return null;
        HashMap<String, String> storageMap = GsonHandler.fromJson(meta.getPersistentDataContainer().get(STORAGE, PersistentDataType.STRING), new TypeToken<HashMap<String, String>>() {}.getType());
        return storageMap.getOrDefault(key, null);
    }
}
