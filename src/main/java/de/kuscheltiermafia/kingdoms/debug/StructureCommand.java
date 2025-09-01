package de.kuscheltiermafia.kingdoms.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import de.kuscheltiermafia.kingdoms.building.Building;
import org.bukkit.entity.Player;

@CommandAlias("structure")
@CommandPermission("kingdoms.dev")
public class StructureCommand extends BaseCommand {

    @Default
    @Syntax("/structure <structure_name> <level> <rotation>")
    @CommandCompletion("<structure_name> <level> 0|1|2|3")
    public void onStructureCommand(Player player, String structureName, int level, int rotation) {
        Building building = Building.fromString(structureName);
        Building.generateStructure(
                player.getLocation(),
                building,
                level,
                rotation
        );
    }

}
