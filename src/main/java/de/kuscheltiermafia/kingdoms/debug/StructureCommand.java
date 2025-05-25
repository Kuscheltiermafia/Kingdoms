package de.kuscheltiermafia.kingdoms.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import de.kuscheltiermafia.kingdoms.building.StructureHandler;
import org.bukkit.entity.Player;

@CommandAlias("structure")
@CommandPermission("kingdoms.dev")
public class StructureCommand extends BaseCommand {

    @Default
    @Syntax("/structure <structure_name> <rotation>")
    @CommandCompletion("<structure_name> 0|1|2|3")
    public void onStructureCommand(Player player, String structureName, int rotation) {
        StructureHandler.placeStructure(
                player.getLocation(),
                structureName,
                rotation
        );
    }

}
