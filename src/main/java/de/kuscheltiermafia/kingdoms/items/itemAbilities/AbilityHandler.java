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
        abilities.put("tri_shortbow", new TriShotShortbow());
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
                    // RIGHT CLICK WITHOUT SNEAKING
                    switch (ability.abilityType) {
                        case RIGHT_CLICK:
                            if (e.getAction().toString().contains("RIGHT_CLICK")) {
                                if (p.isSneaking()) {
                                    if(isOnCooldown(p, ability)) {
                                        sendCooldownMessage(p, ability);
                                        return;
                                    }
                                    ability.shiftRightClick(p, e);
                                } else {
                                    if(isOnCooldown(p, ability)) {
                                        sendCooldownMessage(p, ability);
                                        return;
                                    }
                                    ability.rightClick(p, e);
                                }
                            }
                            break;
                        case LEFT_CLICK:
                            if (e.getAction().toString().contains("LEFT_CLICK")) {
                                if (p.isSneaking()) {
                                    if(isOnCooldown(p, ability)) {
                                        sendCooldownMessage(p, ability);
                                        return;
                                    }
                                    ability.shiftLeftClick(p, e);
                                } else {
                                    if(isOnCooldown(p, ability)) {
                                        sendCooldownMessage(p, ability);
                                        return;
                                    }
                                    ability.leftClick(p, e);
                                }
                            }
                            break;
                        case GENERAL_CLICK:
                            if(isOnCooldown(p, ability)) {
                                sendCooldownMessage(p, ability);
                                return;
                            }
                            ability.onGeneralClick(p, e);
                            break;
                        default:
                            break;
                    }
                }
            }
        }catch (Exception ignored) {}
    }

    private boolean isOnCooldown(Player p, ItemAbility ability) {
        return ability.getCooldown(p) != 0;
    }

    private void sendCooldownMessage(Player p, ItemAbility ability) {
        int cooldown = ability.getCooldown(p);
        p.sendMessage(ChatColor.RED + "This ability is on cooldown for " + cooldown + " more seconds.");
    }
}
