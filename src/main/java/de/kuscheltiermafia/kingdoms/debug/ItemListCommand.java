package de.kuscheltiermafia.kingdoms.debug;

import de.kuscheltiermafia.kingdoms.menus.GuiHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            GuiHandler.getGui("item_list").open(p, true);
        }
        return true;
    }
}
