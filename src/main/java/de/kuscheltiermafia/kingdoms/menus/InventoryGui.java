package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryGui {

    Inventory inventory;
    String id;
    int rows;
    String title;

    public InventoryGui(String id, int rows, String title) {
        this.id = id;
        this.rows = rows;
        this.title = title;

        inventory = org.bukkit.Bukkit.createInventory(null, rows * 9, title);
    }

    public void open(Player player, boolean freshOpened) {
        update(player);
        player.openInventory(inventory);
        GuiHandler.currentGui.put(player, id);
        if(freshOpened) GuiHandler.setPreviousGui(player, id);
    }

    public void changeGui(Player player, String oldGuiID) {
        GuiHandler.setPreviousGui(player, oldGuiID);
        open(player, false);
    }

    public void update(Player p) {
        // Override in subclasses
    }

    protected void addReturnButton() {
        setItem(45, ItemHandler.getItem("back_button_menuindicator"));
    }

    protected void fillEmptySlots(FillType type) {
        if (type == FillType.BORDER) {
            for (int i = 0; i < inventory.getSize(); i++) {
                if (i < 9 || i >= inventory.getSize() - 9 || i % 9 == 0 || i % 9 == 8) {
                    if (inventory.getItem(i) == null) {
                        inventory.setItem(i, ItemHandler.getItem("spacer"));
                    }
                }
            }
        }else if(type.equals(FillType.ENTIRE)) {
            for (int i = 0; i < inventory.getSize(); i++) {
                if (inventory.getItem(i) == null) {
                    inventory.setItem(i, ItemHandler.getItem("spacer"));
                }
            }
        }
    }

    protected void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }
    protected void setItem(int slot, String itemID) {
        inventory.setItem(slot, ItemHandler.getItem(itemID));
    }

    public String getId() {
        return id;
    }

    protected enum FillType {
        ENTIRE,
        BORDER
    }
}
