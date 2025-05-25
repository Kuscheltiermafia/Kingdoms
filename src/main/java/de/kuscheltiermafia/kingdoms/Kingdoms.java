package de.kuscheltiermafia.kingdoms;

import co.aikar.commands.PaperCommandManager;
import de.kuscheltiermafia.kingdoms.building.StructureHandler;
import de.kuscheltiermafia.kingdoms.debug.GetCellCommand;
import de.kuscheltiermafia.kingdoms.debug.ItemList;
import de.kuscheltiermafia.kingdoms.debug.StructureCommand;
import de.kuscheltiermafia.kingdoms.events.AscendEvent;
import de.kuscheltiermafia.kingdoms.events.DescendEvent;
import de.kuscheltiermafia.kingdoms.events.InventoryEvents;
import de.kuscheltiermafia.kingdoms.events.JoinEvent;
import de.kuscheltiermafia.kingdoms.events.QuitEvent;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.structure.Structure;

import java.io.File;
import java.io.IOException;

public final class Kingdoms extends JavaPlugin {

    private static Kingdoms plugin;

    @Override
    public void onEnable() {

        plugin = this;

        ItemHandler.innitItems();

        Bukkit.getPluginManager().registerEvents(new AscendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DescendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryEvents(), this);

        getCommand("itemlist").setExecutor(new ItemList());

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
