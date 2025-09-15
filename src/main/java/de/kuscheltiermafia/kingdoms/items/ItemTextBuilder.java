package de.kuscheltiermafia.kingdoms.items;

import com.google.gson.reflect.TypeToken;
import de.kuscheltiermafia.kingdoms.items.itemAbilities.AbilityHandler;
import de.kuscheltiermafia.kingdoms.items.itemAbilities.ItemAbility;
import de.kuscheltiermafia.kingdoms.items.itemEnchants.EnchantmentHandler;
import de.kuscheltiermafia.kingdoms.items.itemEnchants.ItemEnchant;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

import static de.kuscheltiermafia.kingdoms.items.ItemHandler.ID;
import static de.kuscheltiermafia.kingdoms.utils.TextUtils.toRoman;

public class ItemTextBuilder {

    public static ItemMeta updateTextMeta(ItemMeta meta) {
        ArrayList<String> actualLore = meta.getLore() != null ? new ArrayList<>(meta.getLore()) : new ArrayList<>();

        ArrayList<String> finalLore = new ArrayList<>();

        meta.setDisplayName(getDisplayName(meta));

        HashMap<Stat, Double> statMap = GsonHandler.fromJson(meta.getPersistentDataContainer().get(ItemHandler.FINAL_STATS, PersistentDataType.STRING), GsonHandler.STAT_MAP_TYPE);

        if(meta.getPersistentDataContainer().has(ItemHandler.FINAL_STATS)) {
            for (Stat stat : Stat.values()) {
                if (statMap.containsKey(stat) && statMap.get(stat) != 0.0) {
                    finalLore.add(stat.getColor() + stat.getIcon() + " " + stat.getDisplayName() + ": " + (statMap.get(stat) >= 0 ? "+" : "") + Math.round(statMap.get(stat)));
                }
            }
        }

        if(meta.getPersistentDataContainer().has(ItemHandler.GEMSTONES)) {
            finalLore.add(" ");
            HashMap<Gemstone, Integer> gemMap = Gemstone.returnGemGrade(meta);
            StringBuilder gemLine = new StringBuilder(ChatColor.DARK_PURPLE.toString());
            List<String> gemLines = new ArrayList<>();

            int gemCount = 0;
            for (Gemstone gem : Gemstone.values()) {
                if (gemMap.containsKey(gem)) {
                    gemLine.append(Gemstone.returnGemGradeColor(gemMap.get(gem))).append("[").append(gem.getColor()).append(gem.getIcon()).append(Gemstone.returnGemGradeColor(gemMap.get(gem))).append("]");
                } else {
                    gemLine.append(ChatColor.DARK_GRAY).append("[").append(gem.getIcon()).append("]");
                }
                gemCount++;
                if (gemCount % 7 != 0) {
                    gemLine.append(" ");
                } else {
                    gemLines.add(gemLine.toString());
                    gemLine = new StringBuilder(ChatColor.DARK_PURPLE.toString());
                }
            }

            if (gemLine.length() > 0 && gemCount % 7 != 0) {
                gemLines.add(gemLine.toString());
            }
            finalLore.addAll(gemLines);
        }

        if(meta.getPersistentDataContainer().has(ItemHandler.ABILITIES)) {
            ArrayList<String> abilityIDs = GsonHandler.fromJson(meta.getPersistentDataContainer().get(ItemHandler.ABILITIES, PersistentDataType.STRING), new TypeToken<ArrayList<String>>() {}.getType());
            for(String abilityID : abilityIDs) {
                if(AbilityHandler.abilities.keySet().contains(abilityID)) {
                    ItemAbility ability = AbilityHandler.abilities.get(abilityID);
                    if(ability.getDisplayType().equals(ItemAbility.AbilityDisplayType.HIDDEN)) continue;
                    if(ability.getDisplayType().equals(ItemAbility.AbilityDisplayType.VISIBLE)) {
                        finalLore.add(" ");
                        finalLore.add(ChatColor.YELLOW + ability.abilityName + ": " + ChatColor.WHITE + ChatColor.BOLD + ability.abilityType.getName());
                        for (String line : ability.description) {
                            finalLore.add("§7" + line);
                        }
                        if (ability.description.length > 0) {
                            finalLore.add("");
                        }
                        if (ability.manaCost != 0) {
                            finalLore.add("§bMana Cost: §f" + ability.manaCost);
                        }
                        if (ability.cooldown != 0) {
                            finalLore.add("§aCooldown: §f" + ability.cooldown + "s");
                        }
                    }
                    if(ability.getDisplayType().equals(ItemAbility.AbilityDisplayType.ITEM_TYPE)) {
                        finalLore.add(" ");
                        finalLore.add(ChatColor.YELLOW + ability.abilityName + ": " + ChatColor.WHITE + ChatColor.BOLD + Arrays.toString(ability.description));
                    }
                }
            }
        }

        if(meta.getPersistentDataContainer().has(ItemHandler.ENCHANTMENTS)) {
            finalLore.add(" ");
            HashMap<String, Integer> enchantMap = GsonHandler.fromJson(meta.getPersistentDataContainer().get(ItemHandler.ENCHANTMENTS, PersistentDataType.STRING), GsonHandler.ENCHANT_MAP_TYPE);

            HashMap<String, Integer> ultimateEnchantMap = new HashMap<>();
            HashMap<String, Integer> specialEnchantMap = new HashMap<>();
            HashMap<String, Integer> normalEnchantMap = new HashMap<>();

            for(Map.Entry<String, Integer> entry : enchantMap.entrySet()) {
                if(EnchantmentHandler.enchants.containsKey(entry.getKey())) {
                    ItemEnchant enchant = EnchantmentHandler.enchants.get(entry.getKey());
                    if(enchant.enchantType == ItemEnchant.EnchantType.ULTIMATE) {
                        ultimateEnchantMap.put(entry.getKey(), entry.getValue());
                    } else if(enchant.enchantType == ItemEnchant.EnchantType.SPECIAL) {
                        specialEnchantMap.put(entry.getKey(), entry.getValue());
                    } else {
                        normalEnchantMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }

            enchantMap.clear();

            enchantMap.putAll(ultimateEnchantMap);
            enchantMap.putAll(specialEnchantMap);
            enchantMap.putAll(normalEnchantMap);

            List<String> loreLines = new ArrayList<>();
            StringBuilder lineBuilder = new StringBuilder(ChatColor.BLUE.toString());

            int count = 0;
            for (Map.Entry<String, Integer> entry : enchantMap.entrySet()) {
                String name = EnchantmentHandler.enchants.containsKey(entry.getKey()) ? EnchantmentHandler.enchants.get(entry.getKey()).enchantmentName : entry.getKey();
                String roman = toRoman(entry.getValue());

                if (count > 0) {
                    lineBuilder.append(", ");
                }

                lineBuilder.append(EnchantmentHandler.enchants.get(entry.getKey()).enchantType.getColor()).append(name).append(" ").append(roman);
                count++;

                if (count % 4 == 0) {
                    loreLines.add(lineBuilder.toString());
                    count = 0;
                }
            }

            if (count > 0) {
                loreLines.add(lineBuilder.toString());

            }

            finalLore.addAll(loreLines);
        }

        if(!finalLore.isEmpty()) {
            finalLore.add(" ");
            finalLore.addAll(actualLore);
        }

        finalLore.add(" ");
        finalLore.add(ItemRarity.getById(meta.getPersistentDataContainer().get(ItemHandler.RARITY, PersistentDataType.STRING)).getColor()
                + ItemRarity.getById(meta.getPersistentDataContainer().get(ItemHandler.RARITY, PersistentDataType.STRING)).getDisplayName()
                + " " + ItemType.getById(meta.getPersistentDataContainer().get(ItemHandler.TYPE, PersistentDataType.STRING)).getDisplayName());

        meta.setLore(finalLore);
        return meta;
    }

    private static String getDisplayName(ItemMeta meta) {
        String name = meta.getDisplayName();

        HashMap<String, ItemBuilder> storedItems = GsonHandler.returnStoredItems();
        for(String key : storedItems.keySet()) {
            ItemBuilder builder = storedItems.get(key);
            if(meta.getPersistentDataContainer().get(ID, PersistentDataType.STRING).equals(key)) {
                name = builder.customName;
                break;
            }
        }

        if(meta.getPersistentDataContainer().has(ItemHandler.LEVEL, PersistentDataType.STRING)) {
            ItemLevel level = ItemLevel.getLevelbyID(meta.getPersistentDataContainer().get(ItemHandler.LEVEL, PersistentDataType.STRING));
            StringBuilder currentProgress = new StringBuilder();
            int currentLevelProgress = meta.getPersistentDataContainer().get(ItemHandler.CURRENT_LEVEL_PROGRESS, PersistentDataType.INTEGER) != null ? meta.getPersistentDataContainer().get(ItemHandler.CURRENT_LEVEL_PROGRESS, PersistentDataType.INTEGER) : 0;
            if(currentLevelProgress != 0) {
                currentProgress.append(" ");
                if(currentLevelProgress > 5) {
                    int dual = currentLevelProgress - 5;
                    for (int i = 0; i < dual; i++) {
                        currentProgress.append(ChatColor.AQUA + "⛥");
                    }
                    for(int i = 0; i < 5-dual; i++) {
                        currentProgress.append(ChatColor.GOLD + "⛥");
                    }
                }else{
                    for(int i = 0; i < currentLevelProgress; i++) {
                        currentProgress.append(ChatColor.GOLD + "⛥");
                    }
                }
            }
            return level.getColor() + level.getPrefix() + name.replaceAll("§[0-9a-fk-or]", "") + currentProgress;
        }
        return name;
    }
}
