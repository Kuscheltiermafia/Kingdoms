package de.kuscheltiermafia.kingdoms.items.itemAbilities;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.HashMap;

public abstract class ItemAbility {

    public int manaCost;
    public int cooldown;
    public String abilityName;
    public String abilityID;
    public HashMap<Player, Integer> cooldowns;
    public AbilityType abilityType;
    public AbilityDisplayType displayType;
    public String[] description;

    public ItemAbility(String abilityName, String abilityID, String[] description, int manaCost, int cooldown, AbilityType abilityType, AbilityDisplayType displayType) {
        this.abilityName = abilityName;
        this.abilityID = abilityID;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.abilityType = abilityType;
        this.cooldowns = new HashMap<>();
        this.description = description;
        this.displayType = displayType;
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

    public void onGeneralClick(Player p, Event e) {
        // Ability logic to be implemented in subclasses
    }

    public void passiveWhileUse(Player p) {
        // Ability logic to be implemented in subclasses
    }

    public void passiveOnApply(Player p) {
        // Ability logic to be implemented in subclasses
    }

    public void passiveOnRemove(Player p) {
        // Ability logic to be implemented in subclasses
    }

    public void onHitEntity(Player p, Event e) {
        // Ability logic to be implemented in subclasses
    }

    public AbilityDisplayType getDisplayType() {
        return this.displayType;
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

    public static enum AbilityType {
        RIGHT_CLICK("RIGHT CLICK"),
        LEFT_CLICK("LEFT CLICK"),
        SHIFT_RIGHT_CLICK("SHIFT RIGHT CLICK"),
        SHIFT_LEFT_CLICK("SHIFT LEFT CLICK"),
        GENERAL_CLICK("ON USE"),
        PASSIVE("PASSIVE");

        final String name;
        private AbilityType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static enum AbilityDisplayType {
        VISIBLE,
        ITEM_TYPE,
        HIDDEN;

        private AbilityDisplayType() {
        }
    }
}
