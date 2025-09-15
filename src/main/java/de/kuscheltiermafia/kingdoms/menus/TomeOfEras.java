package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.data.PlayerData;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.events.AscendEvent;
import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
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
            statlore.add(stat.getColor() + stat.getIcon() + " " + stat.getDisplayName() + ": " + Kingdoms.playerStatModelIdentifier.get(p).getStat(stat, true));
        }
        statmeta.setLore(statlore);
        playerStats.setItemMeta(statmeta);
        toe.setItem(10, playerStats);

        List<String> skilllore = new ArrayList<>();
        PlayerData playerData = PlayerUtility.getPlayerImage(p);
        for(Skill skill : Skill.values()) {
            skilllore.add(skill.getColor() + skill.getIcon() + " " + skill.getDisplayName() + ": " + (int) playerData.getValueBySkill(skill, false));
        }
        ItemStack playerSkill = new ItemBuilder().setMaterial(Material.EXPERIENCE_BOTTLE).setID("view_skills_button").setCustomName(ChatColor.LIGHT_PURPLE + "Your Skill Levels: ").setLore(skilllore).addGlint().setMaxStackSize(1).build();
        toe.setItem(19, playerSkill);

        List<String> guideLore = new ArrayList<>();
        ItemStack guide = new ItemBuilder().setMaterial(Material.BOOKSHELF).setID("guide_button").setCustomName(ChatColor.AQUA + "Guide").setMaxStackSize(1).build();
        toe.setItem(37, guide);

        if (AscendEvent.godlyRealm.containsKey(p) && AscendEvent.godlyRealm.get(p)) {
            toe.setItem(21, ItemHandler.getItem("descend_item"));
        }else{
            toe.setItem(21, ItemHandler.getItem("ascend_item"));
        }

        toe.setItem(14, ItemHandler.getItem("placeholder"));
        toe.setItem(15, ItemHandler.getItem("placeholder"));

        toe.setItem(16, ItemHandler.getItem("placeholder"));

        for(int i : new int[]{32, 33, 34, 41, 42, 43}) {
            toe.setItem(i, ItemHandler.getItem("placeholder"));
        }

        if(p.isOp()) {
            ItemStack itemList = new ItemBuilder().setMaterial(Material.COMMAND_BLOCK).setID("open_item_list").setCustomName(ChatColor.DARK_RED + "Open Item List").setMaxStackSize(1).build();
            toe.setItem(53, itemList);
        }

        MenuUtils.fillWithSpacers(toe);
    }

}
