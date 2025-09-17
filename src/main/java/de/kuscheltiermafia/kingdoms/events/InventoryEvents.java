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

                //Doesn't work for some mfing reason \/
                if(e.getCurrentItem().getItemMeta().getItemName().contains("Go Back")) {
                    System.out.println("Back button clicked + " + GuiHandler.getPreviousGui(p).getId());
                    GuiHandler.getPreviousGui(p).changeGui(p, GuiHandler.getGuiLink(e.getCurrentItem()));
                }
                //Doesn't work for some mfing reason /\

                if(GuiHandler.getGuiLink(e.getCurrentItem()).contains("_spage")) {
                    GuiHandler.getGui(GuiHandler.getGuiLink(e.getCurrentItem()).replace("_spage", "")).changeGui(p, GuiHandler.getGuiLink(e.getCurrentItem()));
                }
                if(GuiHandler.getGuiLink(e.getCurrentItem()).contains("page_up") && GuiHandler.isPaginatedGui(GuiHandler.getGui(GuiHandler.getPreviousGui(p).getId()))) {
                    IPaginatedGui paginatedGui = (IPaginatedGui) GuiHandler.getGui(GuiHandler.getPreviousGui(p).getId());
                    paginatedGui.nextPage(p);
                }
                if(GuiHandler.getGuiLink(e.getCurrentItem()).contains("page_down") && GuiHandler.isPaginatedGui(GuiHandler.getGui(GuiHandler.getPreviousGui(p).getId()))) {
                    IPaginatedGui paginatedGui = (IPaginatedGui) GuiHandler.getGui(GuiHandler.getPreviousGui(p).getId());
                    paginatedGui.previousPage(p);
                }
            }
        }catch (Exception ignored){}
    }
}
