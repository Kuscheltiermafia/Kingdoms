package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.data.PlayerStats;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.events.AscendEvent;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.skills.Skill;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TomeOfEras {

    public static Inventory createTOE() {
        Inventory tomeOfEras = org.bukkit.Bukkit.createInventory(null, 9 * 6, ChatColor.LIGHT_PURPLE + "Tome of Eras");

        return tomeOfEras;
    }

    public static void updateTOE(Inventory toe, Player p) {

        ItemStack playerStats = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta statmeta = (SkullMeta) playerStats.getItemMeta();

        statmeta.setOwner(p.getName());
        statmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Your Stats: ");
        statmeta.getPersistentDataContainer().set(ItemHandler.ID, PersistentDataType.STRING, "spacer");
        List<String> statlore = new ArrayList<>();
        for(Stat stat : Stat.values()) {
            statlore.add(stat.getColor() + stat.getIcon() + " " + stat.getDisplayName() + ": " + Kingdoms.playerStatModelIdentifier.get(p).getStat(stat));
        }
        statmeta.setLore(statlore);
        playerStats.setItemMeta(statmeta);
        toe.setItem(10, playerStats);

        List<String> skilllore = new ArrayList<>();
        PlayerStats playerSkills = PlayerUtility.getPlayerImage(p);
        for(Skill skill : Skill.values()) {
            Logger.getLogger("Kingdoms").info("Skill: " + skill.getDisplayName() + " - Level: " + playerSkills.getValueBySkill(skill, false));
            skilllore.add(skill.getColor() + skill.getIcon() + " " + skill.getDisplayName() + ": " + (int) playerSkills.getValueBySkill(skill, false));
        }
        ItemStack playerSkill = ItemHandler.createItem(Material.EXPERIENCE_BOTTLE, ChatColor.LIGHT_PURPLE + "Your Skill Levels: ", "view_skills_button", 1, skilllore, true, false, false, false);
        toe.setItem(19, playerSkill);

        List<String> currentEraReqLore = new ArrayList<>();
        ItemStack currentEraReq = ItemHandler.createItem(Material.WRITABLE_BOOK, ChatColor.AQUA + "Current Era Requirement", "view_era_button", 1, currentEraReqLore, true, false, false, false);
        toe.setItem(28, currentEraReq);

        List<String> guideLore = new ArrayList<>();
        ItemStack guide = ItemHandler.createItem(Material.BOOKSHELF, ChatColor.AQUA + "Guide", "guide_button", 1, guideLore, true, false, false, false);
        toe.setItem(37, guide);

        if (AscendEvent.godlyRealm.containsKey(p) && AscendEvent.godlyRealm.get(p)) {
            toe.setItem(21, new ItemStack(ItemHandler.descend_item));
        }else{
            toe.setItem(21, new ItemStack(ItemHandler.ascend_item));
        }

        ItemStack enderChest = ItemHandler.createItem(Material.ENDER_CHEST, ChatColor.DARK_PURPLE + "Ender Chest", "ender_chest_button", 1, null, true, false, false, false);
        toe.setItem(39, enderChest);

        toe.setItem(14, new ItemStack(ItemHandler.placeholder));
        toe.setItem(15, new ItemStack(ItemHandler.placeholder));

        ItemStack unlockedSpells = ItemHandler.createItem(Material.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, ChatColor.LIGHT_PURPLE + "Unlocked Spells", "unlocked_spells_button", 1, null, true, false, true, false);
        toe.setItem(16, unlockedSpells);

        ItemStack awaitingNewTasks = ItemHandler.createItem(Material.FILLED_MAP, ChatColor.LIGHT_PURPLE + "Awaiting New Quests...", "spacer", 1, null, false, false, true, false);
        for(int i : new int[]{32, 33, 34, 41, 42, 43}) {
            toe.setItem(i, awaitingNewTasks);
        }

        if(p.isOp()) {
            ItemStack itemList = ItemHandler.createItem(Material.COMMAND_BLOCK, ChatColor.DARK_RED + "Open Item List", "open_item_list", 1, null, true, false, false, false);
            toe.setItem(53, itemList);
        }

        MenuUtils.fillWithSpacers(toe);
    }

}
