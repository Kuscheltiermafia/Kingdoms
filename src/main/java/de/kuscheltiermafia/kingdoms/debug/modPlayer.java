package de.kuscheltiermafia.kingdoms.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.items.ItemLevel;
import de.kuscheltiermafia.kingdoms.skills.Skill;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("modifyplayer")
@CommandPermission("kingdoms.dev")
public class modPlayer extends BaseCommand {

    @Subcommand("setskill")
    @CommandCompletion("<skill> <level>")
    public static void onSetLevelCommand(Player sender, String skill, int level) {
        PlayerUtility.getPlayerImage(sender).setValueBySkill(Skill.getSkillByID(skill), level, false);
    }

    @Subcommand("setskillxp")
    @CommandCompletion("<skill> <xp>")
    public static void onSetXPCommand(Player sender, String skill, int xp) {
        PlayerUtility.getPlayerImage(sender).setValueBySkill(Skill.getSkillByID(skill), xp, true);
    }

    @Subcommand("getskill")
    @CommandCompletion("<skill>")
    public static void onGetSkillCommand(Player sender, String skill) {
        sender.sendMessage("Level: " + PlayerUtility.getPlayerImage(sender).getValueBySkill(Skill.getSkillByID(skill), false));
        sender.sendMessage("XP: " + PlayerUtility.getPlayerImage(sender).getValueBySkill(Skill.getSkillByID(skill), true));
    }

    @Subcommand("getstat")
    @CommandCompletion("<stat>")
    public static void onGetStatCommand(Player sender, String stat) {
        sender.sendMessage("Stat " + stat + ": " + PlayerUtility.getPlayerImage(sender).getCorrespondingStatModel().getStat(Stat.getStatByID(stat), true));
    }

}
