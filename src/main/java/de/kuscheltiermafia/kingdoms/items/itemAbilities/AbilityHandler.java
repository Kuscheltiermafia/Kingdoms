package de.kuscheltiermafia.kingdoms.items.itemAbilities;

import com.google.common.reflect.TypeToken;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Set;

public class AbilityHandler implements Listener {

    public static HashMap<String, ItemAbility> abilities = new HashMap<>();

    public static void registerAbility() {
        abilities.put("test_ability", new TestAbility());
    }

    public static void updatePlayerCooldowns(Player p) {
        abilities.values().forEach(ability -> ability.updateCooldown(p));
    }

    @EventHandler
    public void onItemInteraction(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            if(e.getItem() == null) return;
            ItemStack item = e.getItem();
            if(!item.hasItemMeta()) return;
            if(item.getItemMeta().getPersistentDataContainer().has(ItemHandler.ABILITIES)) {
                Set<String> abilityIDs = GsonHandler.fromJson(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ABILITIES, PersistentDataType.STRING), new TypeToken<Set<String>>() {}.getType());
                assert abilityIDs != null;
                for(String abilityID : abilityIDs) {
                    ItemAbility ability = abilities.get(abilityID);
                    if(ability == null) continue;
                    if(ability.getCooldown(p) == 0) {
                        // RIGHT CLICK WITHOUT SNEAKING
                        if (ability.abilityType == ItemAbility.AbilityType.RIGHT_CLICK && (e.getAction().toString().contains("RIGHT_CLICK")) && !p.isSneaking()) {
                            ability.rightClick(p, e);
                        }
                        // LEFT CLICK WITHOUT SNEAKING
                        if (ability.abilityType == ItemAbility.AbilityType.LEFT_CLICK && (e.getAction().toString().contains("LEFT_CLICK")) && !p.isSneaking()) {
                            ability.leftClick(p, e);
                        }
                        // RIGHT CLICK WITH SNEAKING
                        if (ability.abilityType == ItemAbility.AbilityType.RIGHT_CLICK && (e.getAction().toString().contains("RIGHT_CLICK")) && p.isSneaking()) {
                            ability.shiftRightClick(p, e);
                        }
                        // LEFT CLICK WITH SNEAKING
                        if (ability.abilityType == ItemAbility.AbilityType.LEFT_CLICK && (e.getAction().toString().contains("LEFT_CLICK")) && p.isSneaking()) {
                            ability.shiftLeftClick(p, e);
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "Ability " + ability.abilityName + " is on cooldown for " + ChatColor.YELLOW + ability.getCooldown(p) + ChatColor.RED + " more seconds.");
                    }
                }
            }
        }catch (Exception ignored) {}
    }
}
