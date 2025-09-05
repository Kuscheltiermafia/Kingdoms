package de.kuscheltiermafia.kingdoms.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import de.kuscheltiermafia.kingdoms.items.Gemstone;
import de.kuscheltiermafia.kingdoms.items.ItemLevel;
import de.kuscheltiermafia.kingdoms.items.itemEnchants.EnchantmentHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("itemupgrades")
@CommandPermission("kingdoms.dev")
public class ItemupgradesCommand extends BaseCommand {

    @Subcommand("setlevel")
    @CommandCompletion("<level>")
    public static void onSetLevelCommand(Player sender, String level) {
        ItemLevel.setLevel(sender.getInventory().getItemInMainHand(), ItemLevel.getLevelbyID(level));
    }

    @Subcommand("setprogress")
    @CommandCompletion("<progress>")
    public static void onSetProgressCommand(Player sender, int progress) {
        ItemLevel.setLevelProgress(sender.getInventory().getItemInMainHand(), progress);
    }

    @Subcommand("addenchant")
    @CommandCompletion("<enchant> [level]")
    public static void onAddEnchantCommand(CommandSender sender, String enchantment, int level) {
        Player p = (Player) sender;
        if(p.getInventory().getItemInMainHand() == null) {
            p.sendMessage("You must hold an item in your main hand!");
            return;
        }
        for(String s : EnchantmentHandler.enchants.keySet()) {
            if(s.equalsIgnoreCase(enchantment)) {
                p.sendMessage("Adding enchantment " + s + " with level " + level);
                EnchantmentHandler.applyEnchantment(p.getInventory().getItemInMainHand(), EnchantmentHandler.enchants.get(s), level);
                return;
            }
        }
    }

    @Subcommand("removeenchant")
    @CommandCompletion("<enchant>")
    public static void onRemoveEnchantCommand(CommandSender sender, String enchantment) {
        Player p = (Player) sender;
        if(p.getInventory().getItemInMainHand() == null) {
            p.sendMessage("You must hold an item in your main hand!");
            return;
        }
        for(String s : EnchantmentHandler.enchants.keySet()) {
            if(s.equalsIgnoreCase(enchantment)) {
                p.sendMessage("Removing enchantment " + s);
                EnchantmentHandler.removeEnchantment(p.getInventory().getItemInMainHand(), EnchantmentHandler.enchants.get(s));
                return;
            }
        }
    }

    @Subcommand("addgemstone")
    @CommandCompletion("<gemstone> [grade]")
    public static void onAddGemstoneCommand(CommandSender sender, String gemstone, String grade) {
        Player p = (Player) sender;
        if(p.getInventory().getItemInMainHand() == null) {
            p.sendMessage("You must hold an item in your main hand!");
            return;
        }
        Gemstone s = Gemstone.getByCodeID(gemstone);
        p.sendMessage("Adding gemstone " + s + " with grade " + grade);
        Gemstone.modifyGemGrade(p.getInventory().getItemInMainHand(), s, Integer.parseInt(grade), false);
    }

    @Subcommand("removegemstone")
    @CommandCompletion("<gemstone>")
    public static void onRemoveGemstoneCommand(CommandSender sender, String gemstone) {
        Player p = (Player) sender;
        if(p.getInventory().getItemInMainHand() == null) {
            p.sendMessage("You must hold an item in your main hand!");
            return;
        }
        Gemstone s = Gemstone.getByCodeID(gemstone);
        p.sendMessage("Removing gemstone " + s);
        Gemstone.modifyGemGrade(p.getInventory().getItemInMainHand(), s, 0, true);
    }
}
