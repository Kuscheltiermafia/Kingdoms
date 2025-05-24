package de.kuscheltiermafia.kingdoms.stats;

import de.kuscheltiermafia.kingdoms.Main;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.items.ItemStats;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdatePlayerStats {

    public static ArrayList<EquipmentSlot> playerStatSlots = new ArrayList<>();

    public static void initPlayerStatSlots(Player p) {
        playerStatSlots.clear();
        playerStatSlots.add(EquipmentSlot.HAND);
        playerStatSlots.add(EquipmentSlot.OFF_HAND);
        playerStatSlots.add(EquipmentSlot.HEAD);
        playerStatSlots.add(EquipmentSlot.CHEST);
        playerStatSlots.add(EquipmentSlot.LEGS);
        playerStatSlots.add(EquipmentSlot.FEET);
    }

    public static void updatePlayerStats(Player p) {
        PlayerStatModel playerStatModel = Main.playerStatModelIdentifier.get(p);
        initPlayerStatSlots(p);
        playerStatModel.resetStats();

        for (EquipmentSlot slot : playerStatSlots) {
            if(p.getInventory().getItem(slot) != null && p.getInventory().getItem(slot).hasItemMeta() && p.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().has(ItemHandler.ID)) {
                ItemStack currentItem = p.getInventory().getItem(slot);
                playerStatModel.setHealth(playerStatModel.getHealth() + ItemStats.getItemStat(currentItem, Stat.HEALTH));
                playerStatModel.setDefense(playerStatModel.getDefense() + ItemStats.getItemStat(currentItem, Stat.DEFENSE));
                playerStatModel.setIntelligence(playerStatModel.getIntelligence() + ItemStats.getItemStat(currentItem, Stat.INTELLIGENCE));
                playerStatModel.setManaRegeneration(playerStatModel.getManaRegeneration() + ItemStats.getItemStat(currentItem, Stat.MANA_REGENERATION));
                playerStatModel.setCritChance(playerStatModel.getCritChance() + ItemStats.getItemStat(currentItem, Stat.CRIT_CHANCE));
                playerStatModel.setCritDamage(playerStatModel.getCritDamage() + ItemStats.getItemStat(currentItem, Stat.CRIT_DAMAGE));
                playerStatModel.setDamage(playerStatModel.getDamage() + ItemStats.getItemStat(currentItem, Stat.DAMAGE));
                playerStatModel.setStrength(playerStatModel.getStrength() + ItemStats.getItemStat(currentItem, Stat.STRENGTH));
                playerStatModel.setSpeed(playerStatModel.getSpeed() + ItemStats.getItemStat(currentItem, Stat.SPEED));
                playerStatModel.setLuck(playerStatModel.getLuck() + ItemStats.getItemStat(currentItem, Stat.LUCK));
                playerStatModel.setBreakingSpeed(playerStatModel.getBreakingSpeed() + ItemStats.getItemStat(currentItem, Stat.BREAKING_SPEED));
                playerStatModel.setFortune(playerStatModel.getFortune() + ItemStats.getItemStat(currentItem, Stat.FORTUNE));
                playerStatModel.setSpellbound(playerStatModel.getSpellbound() + ItemStats.getItemStat(currentItem, Stat.SPELLBOUND));
            }
        }
    }

}
