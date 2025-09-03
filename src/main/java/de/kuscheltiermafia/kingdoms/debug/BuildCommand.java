package de.kuscheltiermafia.kingdoms.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import de.kuscheltiermafia.kingdoms.building.Building;
import de.kuscheltiermafia.kingdoms.building.Grid;
import org.bukkit.entity.Player;

@CommandAlias("build")
@CommandPermission("kingdoms.dev")
public class BuildCommand extends BaseCommand {

    @Default
    @Syntax("/structure <structure_name> <level>")
    @CommandCompletion("<structure_name> <level>")
    public void onStructureCommand(Player player, String structureName, int level) {
        Building building = Building.fromString(structureName);
        try {
            Building.placeBuilding(
                    Grid.getCellID(player.getLocation().getBlockX(), player.getLocation().getBlockZ()),
                    building,
                    level
            );
        }catch (Exception onPath){
            player.sendMessage("Du kannst hier nicht bauen!");
        }
    }

}
