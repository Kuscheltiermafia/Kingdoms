package de.kuscheltiermafia.kingdoms.items.itemAbilities;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.HashMap;

public class ItemAbility {

    public int manaCost;
    public int cooldown;
    public String abilityName;
    public String abilityID;
    public HashMap<Player, Integer> cooldowns;
    public AbilityType abilityType;
    public String[] description;

    public ItemAbility(String abilityName, String abilityID, String[] description, int manaCost, int cooldown, AbilityType abilityType) {
        this.abilityName = abilityName;
        this.abilityID = abilityID;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.abilityType = abilityType;
        this.cooldowns = new HashMap<>();
        this.description = description;
    }

    public void leftClick(Player p, Event e) {
        // Ability logic to be implemented in subclasses
    }

    public void rightClick(Player p, Event e) {
        // Ability logic to be implemented in subclasses
    }

    public void shiftRightClick(Player p, Event e) {
        // Ability logic to be implemented in subclasses
    }

    public void shiftLeftClick(Player p, Event e) {
        // Ability logic to be implemented in subclasses
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
                cooldowns.remove(p);
            }
        }
    }

    public static enum AbilityType {
        RIGHT_CLICK,
        LEFT_CLICK,
        SHIFT_RIGHT_CLICK,
        SHIFT_LEFT_CLICK,
        PASSIVE;
    }
}
