package de.kuscheltiermafia.kingdoms;

import de.kuscheltiermafia.kingdoms.events.AscendEvent;
import de.kuscheltiermafia.kingdoms.events.DescendEvent;
import de.kuscheltiermafia.kingdoms.events.JoinEvent;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {

        plugin = this;


        Bukkit.getPluginManager().registerEvents(new AscendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DescendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }

}
