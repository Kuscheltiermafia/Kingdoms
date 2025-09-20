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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class TomeOfEras extends InventoryGui {

    public TomeOfEras() {
        super("tome_of_eras_main", 6, ChatColor.LIGHT_PURPLE + "Tome of Eras");
    }

    @Override
    public void update(Player p) {
        ItemStack playerStats = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta statmeta = (SkullMeta) playerStats.getItemMeta();

        statmeta.setOwner(p.getName());
        statmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Your Stats: ");
        statmeta.getPersistentDataContainer().set(ItemHandler.ID, PersistentDataType.STRING, "stat_spage_breakdown_menuindicator");
        List<String> statlore = new ArrayList<>();
        for(Stat stat : Stat.values()) {
            statlore.add(stat.getColor() + stat.getIcon() + " " + stat.getDisplayName() + ": " + Kingdoms.playerStatModelIdentifier.get(p).getStat(stat, true));
        }
        statmeta.setLore(statlore);
        playerStats.setItemMeta(statmeta);

        setItem(10, playerStats);

        List<String> skilllore = new ArrayList<>();
        PlayerData playerData = PlayerUtility.getPlayerImage(p);
        for(Skill skill : Skill.values()) {
            skilllore.add(skill.getColor() + skill.getIcon() + " " + skill.getDisplayName() + ": " + (int) playerData.getValueBySkill(skill, false));
            System.out.println(skill.getDisplayName() + ": " + (int) playerData.getValueBySkill(skill, false));
        }
        ItemStack playerSkill = new ItemBuilder().setMaterial(Material.EXPERIENCE_BOTTLE).setID("skill_breakdown_spage_menuindicator").setCustomName(ChatColor.LIGHT_PURPLE + "Your Skill Levels: ").setLore(skilllore).addGlint().setMaxStackSize(1).build();

        setItem(19, playerSkill);

        ItemStack guide = new ItemBuilder().setMaterial(Material.BOOKSHELF).setID("guide_book_spage_menuindicator").setCustomName(ChatColor.AQUA + "Guide").setMaxStackSize(1).build();
        setItem(37, guide);

        if(p.isOp()) {
            ItemStack admin = new ItemBuilder().setMaterial(Material.COMMAND_BLOCK).setID("item_list_spage_menuindicator").setCustomName(ChatColor.RED + "Admin Tools").setMaxStackSize(1).build();
            setItem(53, admin);
        }

        if (AscendEvent.godlyRealm.containsKey(p)) {
            setItem(21, ItemHandler.getItem("descend_item"));
        }else{
            setItem(21, ItemHandler.getItem("ascend_item"));
        }

        for(int i : new int[]{14, 15, 16, 32, 33, 34, 41, 42, 43}) {
            setItem(i, ItemHandler.getItem("placeholder"));
        }

        fillEmptySlots(FillType.ENTIRE);
    }
}
