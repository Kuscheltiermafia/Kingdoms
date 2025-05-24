package de.kuscheltiermafia.kingdoms;

import de.kuscheltiermafia.kingdoms.debug.GetStats;
import de.kuscheltiermafia.kingdoms.debug.ItemList;
import de.kuscheltiermafia.kingdoms.events.*;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.stats.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Kingdoms getPlugin() {
        return plugin;
    }

}
