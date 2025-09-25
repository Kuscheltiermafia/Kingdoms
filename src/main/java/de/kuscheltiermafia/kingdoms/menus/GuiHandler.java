package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuiHandler {
    private static List<Class<? extends InventoryGui>> guis = new ArrayList<>();
    private static HashMap<Player, String> previousGui = new HashMap<>();

    public static HashMap<Player, String> currentGui = new HashMap<>();

    public static void registerGuis() {
        guis.add(TomeOfEras.class);
        guis.add(ItemList.class);
        guis.add(SkillsPage.class);
        guis.add(SpellbookGui.class);
    }

    public static InventoryGui createGui(String id) {
        for(Class<? extends InventoryGui> guiClass : guis) {
            try {
                InventoryGui guiInstance = guiClass.getDeclaredConstructor().newInstance();
                if(guiInstance.id.equals(id)) {
                    return guiInstance;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getPreviousGuiKey(Player p) {
        return previousGui.getOrDefault(p, null);
    }

    public static void setPreviousGui(Player p, String id) {
        previousGui.put(p, id);
    }

    public static boolean isPaginatedGui(InventoryGui gui) {
        return gui instanceof IPaginatedGui;
    }

    public static boolean isGuiButton(ItemStack item) {
        try {
            return item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ID, org.bukkit.persistence.PersistentDataType.STRING).contains("menuindicator");
        }catch (Exception e) {
            return false;
        }
    }

    public static String getGuiLink(ItemStack item) {
        try {
            return item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ID, org.bukkit.persistence.PersistentDataType.STRING).replace("_menuindicator", "");
        }catch (Exception e) {
            return "null";
        }
    }
}
