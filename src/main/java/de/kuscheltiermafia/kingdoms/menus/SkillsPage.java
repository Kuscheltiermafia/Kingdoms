package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.data.PlayerData;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
import de.kuscheltiermafia.kingdoms.skills.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillsPage extends InventoryGui{
    public SkillsPage() {
        super("skill_breakdown", 6, ChatColor.DARK_PURPLE + "Your Skills");
    }

    @Override
    public void update(Player p) {
        HashMap<Integer, ItemStack> skillItems = createSkillItems(p);
        for(int i : skillItems.keySet()) {
            setItem(i, skillItems.get(i));
        }

        addReturnButton();
        fillEmptySlots(FillType.ENTIRE);
    }

    private static HashMap<Integer, ItemStack> createSkillItems(Player p) {
        HashMap<Integer, ItemStack> skillItems = new HashMap<>();
        int[] slots = new int[]{19, 20, 21, 22, 23, 24, 25, 29, 30, 32, 33};
        PlayerData playerSkills = PlayerUtility.getPlayerImage(p);

        for(Skill skill : Skill.values()) {
            int index = skill.ordinal();

            List<String> skillLore = new ArrayList<>();
            skillLore.add(ChatColor.GRAY + "Current Level: " + playerSkills.getValueBySkill(skill, false));
            skillLore.add(ChatColor.GRAY + "Current Experience: " + playerSkills.getValueBySkill(skill, true) + " / " + playerSkills.getLevelXP((int) playerSkills.getValueBySkill(skill, false)));

            ItemStack skillItem = new ItemBuilder().setMaterial(skill.getIconItem()).setID("spacer" /*maybe skill_view_item*/).setCustomName(skill.getColor() + skill.getIcon() + " " + skill.getDisplayName()).setLore(skillLore).setMaxStackSize(1).temporary(true).build();

            skillItems.put(slots[index], skillItem);
        }

        return skillItems;
    }
}
