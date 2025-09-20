package de.kuscheltiermafia.kingdoms.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import de.kuscheltiermafia.kingdoms.wizardry.spells.SpellHandler;
import org.bukkit.entity.Player;

@CommandAlias("spell")
@CommandPermission("kingdoms.dev")
public class ManageSpellsCommand extends BaseCommand {

    @Subcommand("add")
    @CommandCompletion("<spell>")
    public static void onAddSpell(Player sender, String spell) {
        SpellHandler.storeSpell(SpellHandler.getSpellById(spell), sender.getInventory().getItemInMainHand());
    }
}
