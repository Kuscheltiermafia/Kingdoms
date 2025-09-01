package de.kuscheltiermafia.kingdoms.items;

import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class LoreBuilder {
    public static ItemMeta updateLore(ItemMeta meta) {
        ArrayList<String> actualLore = meta.getLore() != null ? new ArrayList<>(meta.getLore()) : new ArrayList<>();

        ArrayList<String> finalLore = new ArrayList<>();

        HashMap<Stat, Double> statMap = GsonHandler.returnItemStatMap(meta.getPersistentDataContainer().get(ItemHandler.STATS, PersistentDataType.STRING));

        if(meta.getPersistentDataContainer().has(ItemHandler.STATS)) {
            for (Stat stat : Stat.values()) {
                if (statMap.containsKey(stat) && statMap.get(stat) != 0) {
                    finalLore.add(stat.getColor() + stat.getIcon() + " " + stat.getDisplayName() + ": " + (statMap.get(stat) >= 0 ? "+" : "") + statMap.get(stat));
                }
            }
        }

        if(!finalLore.isEmpty()) {
            finalLore.add(" ");
        }

        //Insert logic Gems + Enchants here

        finalLore.addAll(actualLore);

        meta.setLore(finalLore);
        return meta;
    }
}
