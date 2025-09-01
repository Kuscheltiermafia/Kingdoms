package de.kuscheltiermafia.kingdoms;

import co.aikar.commands.PaperCommandManager;
import de.kuscheltiermafia.kingdoms.building.StructureHandler;
import de.kuscheltiermafia.kingdoms.data.Buildings;
import de.kuscheltiermafia.kingdoms.debug.GetCellCommand;
import de.kuscheltiermafia.kingdoms.debug.StructureCommand;
import de.kuscheltiermafia.kingdoms.data.PlayerStats;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.debug.GetStats;
import de.kuscheltiermafia.kingdoms.debug.ItemList;
import de.kuscheltiermafia.kingdoms.events.*;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.stats.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.structure.Structure;

import java.io.File;
import java.io.IOException;

import java.io.File;
import java.util.HashMap;

public final class Kingdoms extends JavaPlugin {

    private static Kingdoms plugin;
    public static HashMap<Player, PlayerStatModel> playerStatModelIdentifier = new HashMap<>();

    @Override
    public void onEnable() {

        plugin = this;

        ItemHandler.innitItems();

        Bukkit.getPluginManager().registerEvents(new AscendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DescendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryEvents(), this);
        Bukkit.getPluginManager().registerEvents(new TomeOfErasEvents(), this);

        getCommand("itemlist").setExecutor(new ItemList());
        getCommand("getstats").setExecutor(new GetStats());

        for(Player p : Bukkit.getOnlinePlayers()) {
            playerStatModelIdentifier.put(p, new PlayerStatModel(20f, 0f, 10f, 2f, 0.05f, 1f, 4f, 4f, 0.1f, 0f, 1f, 0f, 0f));
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
