package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.data.PlayerStats;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.skills.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillsPage {
    public static Inventory createSkillsList(Player p) {
        Inventory skillList = org.bukkit.Bukkit.createInventory(null, 9 * 6, ChatColor.LIGHT_PURPLE + "Tome of Eras - Your Skills");

        return skillList;
    }

    public static void updateSkillsList(Inventory skillList, Player p) {
        HashMap<Integer, ItemStack> skillItems = createSkillItems(p);
        for(int i : skillItems.keySet()) {
            skillList.setItem(i, skillItems.get(i));
        }

        MenuUtils.placeReturnButton(skillList);

        MenuUtils.fillWithSpacers(skillList);
    }

    private static HashMap<Integer, ItemStack> createSkillItems(Player p) {
        HashMap<Integer, ItemStack> skillItems = new HashMap<>();
        int[] slots = new int[]{19, 20, 21, 22, 23, 24, 25, 29, 30, 32, 33};
        PlayerStats playerStats = PlayerUtility.getPlayerImage(p);

        for(Skill skill : Skill.values()) {
            int index = skill.ordinal();

            List<String> skillLore = new ArrayList<>();
            skillLore.add(ChatColor.GRAY + "Current Level: " + playerStats.getValueBySkill(skill, false));
            skillLore.add(ChatColor.GRAY + "Current Experience: " + playerStats.getValueBySkill(skill, true) + " / " + playerStats.getLevelXP((int) playerStats.getValueBySkill(skill, false)));

            ItemStack skillItem = new ItemBuilder().setMaterial(skill.getIconItem()).setID("spacer" /*maybe skill_view_item*/).setCustomName(skill.getColor() + skill.getIcon() + " " + skill.getDisplayName()).setLore(skillLore).setMaxStackSize(1).temporary().build();

            skillItems.put(slots[index], skillItem);
        }

        return skillItems;
    }
}
