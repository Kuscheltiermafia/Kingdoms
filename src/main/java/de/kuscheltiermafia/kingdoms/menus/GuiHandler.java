package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GuiHandler {
    private static HashMap<String, InventoryGui> guis = new HashMap<>();
    private static HashMap<Player, String> previousGui = new HashMap<>();

    public static HashMap<Player, String> currentGui = new HashMap<>();

    public static void registerGuis() {
        guis.put("tome_of_eras_main", new TomeOfEras());
        guis.put("item_list", new ItemList());
    }

    public static InventoryGui getGui(String id) {
        return guis.get(id);
    }

    public static InventoryGui getPreviousGui(Player p) {
        if(previousGui.containsKey(p)) {
            return guis.get(previousGui.get(p));
        }else{
            return null;
        }
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
