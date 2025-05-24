package de.kuscheltiermafia.kingdoms.items;

import de.kuscheltiermafia.kingdoms.stats.Stat;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemStats {

    public static HashMap<String, HashMap<Stat, Float>> itemStats = new HashMap<>();

    public static ItemStack addItemStats(ItemStack item, float health, float defense, float intelligence, float manaRegeneration, float critChance, float critDamage, float damage, float strength, float speed, float luck, float breakingSpeed, float fortune, float spellbound) {

        HashMap<Stat, Float> stats = new HashMap<>();

        stats.put(Stat.HEALTH, health);
        stats.put(Stat.DEFENSE, defense);
        stats.put(Stat.INTELLIGENCE, intelligence);
        stats.put(Stat.MANA_REGENERATION, manaRegeneration);
        stats.put(Stat.CRIT_CHANCE, critChance);
        stats.put(Stat.CRIT_DAMAGE, critDamage);
        stats.put(Stat.DAMAGE, damage);
        stats.put(Stat.STRENGTH, strength);
        stats.put(Stat.SPEED, speed);
        stats.put(Stat.LUCK, luck);
        stats.put(Stat.BREAKING_SPEED, breakingSpeed);
        stats.put(Stat.FORTUNE, fortune);
        stats.put(Stat.SPELLBOUND, spellbound);

        itemStats.put(ItemHandler.getItemID(item), stats);

        List<String> lore = new ArrayList<>();
        for(Stat stat : stats.keySet()) {
            if(stats.get(stat) != 0f) {
                lore.add(stat.getColor() + stat.getIcon() + " " + stat.getDisplayName() + ": " + stats.get(stat));
            }
        }
        if(item.getItemMeta().hasLore()) {
             List<String> ActualLore = item.getItemMeta().getLore();
             lore.add(" ");
             lore.addAll(ActualLore);
        }

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static float getItemStat(ItemStack item, Stat stat) {
        try {
            return itemStats.get(ItemHandler.getItemID(item)).get(stat);
        } catch (Exception e) {
            return 0f;
        }
    }

}
