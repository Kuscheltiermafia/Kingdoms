package de.kuscheltiermafia.kingdoms;

import co.aikar.commands.PaperCommandManager;
import de.kuscheltiermafia.kingdoms.data.Buildings;
import de.kuscheltiermafia.kingdoms.debug.GetCellCommand;
import de.kuscheltiermafia.kingdoms.debug.StructureCommand;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.debug.GetStats;
import de.kuscheltiermafia.kingdoms.debug.ItemList;
import de.kuscheltiermafia.kingdoms.events.*;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.overlays.ActionBarHandler;
import de.kuscheltiermafia.kingdoms.stats.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
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

        ItemHandler.registerItems();

        Bukkit.getPluginManager().registerEvents(new AscendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DescendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryEvents(), this);
        Bukkit.getPluginManager().registerEvents(new TomeOfErasEvents(), this);
        //Bukkit.getPluginManager().registerEvents(new OnDamageEvent(), this);

        getCommand("itemlist").setExecutor(new ItemList());
        getCommand("getstats").setExecutor(new GetStats());

        ActionBarHandler.startActionBarUpdater();

        for(Player p : Bukkit.getOnlinePlayers()) {
            playerStatModelIdentifier.put(p, new PlayerStatModel());
            playerStatModelIdentifier.get(p).resetStats();
            UpdatePlayerStats.updatePlayerStats(p);
        }

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Kingdoms getPlugin() {
        return plugin;
    }

}
