package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.menus.GuiHandler;
import de.kuscheltiermafia.kingdoms.menus.IPaginatedGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEvents implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            if (ItemHandler.checkItemID(e.getCurrentItem(), "spacer") || ItemHandler.checkItemID(e.getCurrentItem(), "placeholder") || ItemHandler.checkItemID(e.getCurrentItem(), "no_page_up") || ItemHandler.checkItemID(e.getCurrentItem(), "no_page_down")) {
                e.setCancelled(true);
            }else if(GuiHandler.isGuiButton(e.getCurrentItem())) {
                e.setCancelled(true);

                System.out.println("Gui button clicked: " + GuiHandler.getGuiLink(e.getCurrentItem()));

                if(GuiHandler.getGuiLink(e.getCurrentItem()).contains("_spage")) {
                    System.out.println("Special page button clicked: " + GuiHandler.getGuiLink(e.getCurrentItem()).replace("_spage", ""));
                    GuiHandler.getGui(GuiHandler.getGuiLink(e.getCurrentItem()).replace("_spage", "")).changeGui(p, GuiHandler.currentGui.get(p));
                }
                if(GuiHandler.getGuiLink(e.getCurrentItem()).equals("back_button")) {
                    System.out.println("Back button clicked + " + GuiHandler.getPreviousGui(p).getId());
                    GuiHandler.getPreviousGui(p).changeGui(p, GuiHandler.currentGui.get(p));
                }

                if(!GuiHandler.isPaginatedGui(GuiHandler.getGui(GuiHandler.currentGui.get(p)))) return;
                if(GuiHandler.getGuiLink(e.getCurrentItem()).equals("page_up")) {
                    IPaginatedGui paginatedGuiUp = (IPaginatedGui) GuiHandler.getGui(GuiHandler.currentGui.get(p));
                    paginatedGuiUp.nextPage(p);
                }
                if(GuiHandler.getGuiLink(e.getCurrentItem()).equals("page_down")) {
                    IPaginatedGui paginatedGuiDown = (IPaginatedGui) GuiHandler.getGui(GuiHandler.currentGui.get(p));
                    paginatedGuiDown.previousPage(p);
                }
            }
        }catch (Exception ignored){}
    }
}
