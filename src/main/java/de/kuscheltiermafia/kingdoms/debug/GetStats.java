package de.kuscheltiermafia.kingdoms.debug;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.stats.DamageCalculator;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetStats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;

            UpdatePlayerStats.updatePlayerStats(p);

            switch (args[0]) {
                case "finaldmg":
                    p.sendMessage("Final Damage: " + DamageCalculator.calculateFinalDamage(p));
                    break;
                case "all":
                    for(Stat stat : Stat.values()) {
                        p.sendMessage(stat.name() + ": " + Kingdoms.playerStatModelIdentifier.get(p).getStat(stat));
                    }
                default:
                    break;
            }

        }
        return false;
    }
}
