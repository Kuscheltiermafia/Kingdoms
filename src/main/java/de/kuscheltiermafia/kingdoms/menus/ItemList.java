package de.kuscheltiermafia.kingdoms.menus;

import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemList extends InventoryGui implements IPaginatedGui{
    HashMap<Player, Integer> itemListPage = new HashMap<>();

    public ItemList() {
        super("item_list", 6, "§4Itemlist");
    }

    @Override
    public void update(Player p) {
        int pageModi = 28 * (getPage(p));

        for(int i = 0; i < inventory.getSize(); i++) {
            setItem(i, new ItemStack(Material.AIR));
        }

        if(getPage(p) == 0) {
            setItem(0, ItemHandler.getItem("no_page_down"));
        }else{setItem(0, ItemHandler.getItem("page_down_menuindicator"));}

        if((getPage(p) + 1) * 28 <= ItemHandler.itemList.size()) {
            setItem(2, ItemHandler.getItem("page_up_menuindicator"));
        }else{setItem(2, ItemHandler.getItem("no_page_up"));}

        addReturnButton();

        setItem(1, new ItemBuilder().setCustomName(ChatColor.DARK_RED + "Current Page: " + (getPage(p) + 1)).setID("spacer").setMaterial(Material.BOOK).setMaxStackSize(1).temporary().build());

        fillEmptySlots(FillType.BORDER);

        for (int i = 0; i < 28; i++) {
            if(i + pageModi >= ItemHandler.itemList.size()) {
                break;
            }else {
                inventory.addItem(ItemHandler.itemList.get(i + pageModi));
            }
        }
    }

    @Override
    public void setPage(Player p, int page) {
        itemListPage.put(p, page);
    }

    @Override
    public int getPage(Player p) {
        return itemListPage.getOrDefault(p, 0);
    }

    @Override
    public void nextPage(Player p) {
        if(!itemListPage.containsKey(p)) itemListPage.put(p, 0);
        itemListPage.compute(p, (k, currentPage) -> currentPage + 1);
        update(p);
    }

    @Override
    public void previousPage(Player p) {
        int currentPage = itemListPage.get(p);
        if(currentPage > 0) {
            itemListPage.put(p, currentPage - 1);
            update(p);
        }
    }
}
