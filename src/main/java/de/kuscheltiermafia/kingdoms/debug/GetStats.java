package de.kuscheltiermafia.kingdoms.debug;

import de.kuscheltiermafia.kingdoms.Kingdoms;
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

            p.sendMessage("Health: " + Kingdoms.playerStatModelIdentifier.get(p).getHealth());
            p.sendMessage("Defense: " + Kingdoms.playerStatModelIdentifier.get(p).getDefense());
            p.sendMessage("Intelligence: " + Kingdoms.playerStatModelIdentifier.get(p).getIntelligence());
            p.sendMessage("Mana Regeneration: " + Kingdoms.playerStatModelIdentifier.get(p).getManaRegeneration());
            p.sendMessage("Crit Chance: " + Kingdoms.playerStatModelIdentifier.get(p).getCritChance());
            p.sendMessage("Crit Damage: " + Kingdoms.playerStatModelIdentifier.get(p).getCritDamage());
            p.sendMessage("Damage: " + Kingdoms.playerStatModelIdentifier.get(p).getDamage());
            p.sendMessage("Strength: " + Kingdoms.playerStatModelIdentifier.get(p).getStrength());
            p.sendMessage("Speed: " + Kingdoms.playerStatModelIdentifier.get(p).getSpeed());
            p.sendMessage("Luck: " + Kingdoms.playerStatModelIdentifier.get(p).getLuck());
            p.sendMessage("Breaking Speed: " + Kingdoms.playerStatModelIdentifier.get(p).getBreakingSpeed());
            p.sendMessage("Fortune: " + Kingdoms.playerStatModelIdentifier.get(p).getFortune());
            p.sendMessage("Spellbound: " + Kingdoms.playerStatModelIdentifier.get(p).getSpellbound());

        }
        return false;
    }
}
