package de.kuscheltiermafia.kingdoms.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import de.kuscheltiermafia.kingdoms.building.Grid;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandAlias("getcell")
public class GetCellCommand extends BaseCommand {

    @Default
    public void onGetCellCommand(Player player) {
        Location location = player.getLocation();
        String cellID = Grid.getCellID(location.getBlockX(), location.getBlockZ());
        player.sendMessage("Cell ID: " + cellID);
    }
}
