package de.kuscheltiermafia.kingdoms.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import de.kuscheltiermafia.kingdoms.menus.GuiHandler;
import org.bukkit.entity.Player;

@CommandAlias("opengui")
@CommandPermission("kingdoms.dev")
public class OpenGUICommand extends BaseCommand {

    @CommandCompletion("<gui>")
    @Default
    public static void openGUI(Player p, String gui) {
        GuiHandler.createGui(gui).open(p, true);
    }

}
