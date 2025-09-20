package de.kuscheltiermafia.kingdoms.wizardry.spells;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class BaseSpell {

    String id;
    String displayName;
    ChatColor color;
    Material displayItem = Material.BOOK; // default item
    List<String> description;

    int manaCost;
    int cooldown; // in seconds

    HashMap<Player, Integer> cooldowns = new HashMap<>();

    BaseSpell(String id, String displayName, int manaCost, int cooldown, ChatColor color, Material displayItem, List<String> description) {
        this.id = id;
        this.displayName = displayName;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.color = color;
        this.description = description;
        this.displayItem = displayItem;
    }

    public int getCooldown(Player p) {
        return cooldowns.getOrDefault(p, 0);
    }

    public void setCooldown(Player p, int time) {
        cooldowns.put(p, time);
    }

    public void updateCooldown(Player p) {
        if(cooldowns.containsKey(p)) {
            int time = cooldowns.get(p);
            if(time > 0) {
                cooldowns.put(p, time - 1);
            } else {
                cooldowns.put(p, 0);
            }
        }
    }

    public void onUse(Player caster) {
        // to be overridden
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public Material getDisplayItem() {
        return displayItem;
    }

    public ChatColor getColor() {
        return color;
    }
}
