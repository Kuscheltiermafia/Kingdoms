package de.kuscheltiermafia.kingdoms.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import de.kuscheltiermafia.kingdoms.mobs.MobHandler;
import org.bukkit.entity.Player;

@CommandAlias("entity")
@CommandPermission("kingdoms.dev")
public class EntityCommand extends BaseCommand {

    @Subcommand("spawnentity")
    @CommandCompletion("<entitytype>")
    public void onSpawnEntity(Player sender) {
        MobHandler.spawnTestEntity(sender.getLocation());
    }
}
