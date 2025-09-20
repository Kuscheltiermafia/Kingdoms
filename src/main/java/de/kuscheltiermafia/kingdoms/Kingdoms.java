package de.kuscheltiermafia.kingdoms;

import co.aikar.commands.PaperCommandManager;
import de.kuscheltiermafia.kingdoms.data.Buildings;
import de.kuscheltiermafia.kingdoms.debug.*;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.events.*;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.items.crafting.CraftingEvents;
import de.kuscheltiermafia.kingdoms.items.crafting.CraftingHandler;
import de.kuscheltiermafia.kingdoms.items.itemAbilities.AbilityHandler;
import de.kuscheltiermafia.kingdoms.items.itemEnchants.EnchantmentHandler;
import de.kuscheltiermafia.kingdoms.menus.GuiHandler;
import de.kuscheltiermafia.kingdoms.stats.models.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import de.kuscheltiermafia.kingdoms.wizardry.spells.SpellHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import java.util.HashMap;

public final class Kingdoms extends JavaPlugin {

    private static Kingdoms plugin;
    public static HashMap<Player, PlayerStatModel> playerStatModelIdentifier = new HashMap<>();

    @Override
    public void onEnable() {

        plugin = this;

        AbilityHandler.registerAbility();
        EnchantmentHandler.registerEnchantments();
        ItemHandler.registerItems();
        CraftingHandler.initializeRecipes();

        GuiHandler.registerGuis();
        SpellHandler.registerSpells();

        RepeatingTasks.updateEachTick();
        RepeatingTasks.updateEachSecond();

        Bukkit.getPluginManager().registerEvents(new AscendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DescendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryEvents(), this);
        Bukkit.getPluginManager().registerEvents(new TomeOfErasEvents(), this);
        Bukkit.getPluginManager().registerEvents(new AbilityHandler(), this);
        Bukkit.getPluginManager().registerEvents(new CraftingEvents(), this);
        Bukkit.getPluginManager().registerEvents(new OnDamageEvent(), this);
        Bukkit.getPluginManager().registerEvents(new UpdatePlayerStats(), this);

        getCommand("itemlist").setExecutor(new ItemListCommand());

        saveDefaultConfig();

        for(Player player : Bukkit.getOnlinePlayers()) {
            if (!PlayerUtility.initPlayer(player)) return;
        }

        try {
            if (Buildings.setupConfig()) {
                getLogger().info("Created buildings.yml");
            }else {
                getLogger().severe("Could not create buildings.yml. Disabling plugin due to unplayability.");
                this.setEnabled(false);
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new StructureCommand());
        manager.registerCommand(new GetCellCommand());
        manager.registerCommand(new BuildCommand());
        manager.registerCommand(new ItemupgradesCommand());
        manager.registerCommand(new EntityCommand());
        manager.registerCommand(new modPlayer());
        manager.registerCommand(new ManageSpellsCommand());
        manager.registerCommand(new OpenGUICommand());
    }

    @Override
    public void onDisable() {

        for(Player player : Bukkit.getOnlinePlayers()) {
            PlayerUtility.getPlayerImage(player).storeData(player);
        }
    }

    public static Kingdoms getPlugin() {
        return plugin;
    }

}
