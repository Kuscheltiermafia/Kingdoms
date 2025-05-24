package de.kuscheltiermafia.kingdoms.debug;

import de.kuscheltiermafia.kingdoms.Main;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetStats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;

            UpdatePlayerStats.updatePlayerStats(p);

            p.sendMessage("Health: " + Main.playerStatModelIdentifier.get(p).getHealth());
            p.sendMessage("Defense: " + Main.playerStatModelIdentifier.get(p).getDefense());
            p.sendMessage("Intelligence: " + Main.playerStatModelIdentifier.get(p).getIntelligence());
            p.sendMessage("Mana Regeneration: " + Main.playerStatModelIdentifier.get(p).getManaRegeneration());
            p.sendMessage("Crit Chance: " + Main.playerStatModelIdentifier.get(p).getCritChance());
            p.sendMessage("Crit Damage: " + Main.playerStatModelIdentifier.get(p).getCritDamage());
            p.sendMessage("Damage: " + Main.playerStatModelIdentifier.get(p).getDamage());
            p.sendMessage("Strength: " + Main.playerStatModelIdentifier.get(p).getStrength());
            p.sendMessage("Speed: " + Main.playerStatModelIdentifier.get(p).getSpeed());
            p.sendMessage("Luck: " + Main.playerStatModelIdentifier.get(p).getLuck());
            p.sendMessage("Breaking Speed: " + Main.playerStatModelIdentifier.get(p).getBreakingSpeed());
            p.sendMessage("Fortune: " + Main.playerStatModelIdentifier.get(p).getFortune());
            p.sendMessage("Spellbound: " + Main.playerStatModelIdentifier.get(p).getSpellbound());

        }
        return false;
    }
}
