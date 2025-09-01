package de.kuscheltiermafia.kingdoms;

import co.aikar.commands.PaperCommandManager;
import de.kuscheltiermafia.kingdoms.debug.*;
import de.kuscheltiermafia.kingdoms.data.PlayerStats;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import de.kuscheltiermafia.kingdoms.events.*;
import de.kuscheltiermafia.kingdoms.items.Items;
import de.kuscheltiermafia.kingdoms.overlays.HealthBar;
import de.kuscheltiermafia.kingdoms.stats.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.UpdatePlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.HashMap;


public final class Kingdoms extends JavaPlugin {

    private static Kingdoms plugin;
    public static HashMap<Player, PlayerStatModel> playerStatModelIdentifier = new HashMap<>();

    @Override
    public void onEnable() {

        plugin = this;

        Items.initializeItems();

        HealthBar.startHealthBarUpdater();

        Bukkit.getPluginManager().registerEvents(new AscendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DescendEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryEvents(), this);
        Bukkit.getPluginManager().registerEvents(new TomeOfErasEvents(), this);
        Bukkit.getPluginManager().registerEvents(new OnDamageEvent(), this);

        getCommand("itemlist").setExecutor(new ItemList());
        getCommand("getstats").setExecutor(new GetStats());

        for(Player p : Bukkit.getOnlinePlayers()) {
            playerStatModelIdentifier.put(p, new PlayerStatModel(20f, 0f, 10f, 2f, 0.05f, 1f, 4f, 4f, 0.1f, 0f, 1f, 0f, 0f));
            UpdatePlayerStats.updatePlayerStats(p);
        }

        for(Player p : Bukkit.getOnlinePlayers()) {
            PlayerStats image = new PlayerStats();
            File file = new File(PlayerUtility.getFolderPath(p) + "stats.yml");

            if (file.exists()) {
                image.loadStats(file);
            }

            PlayerUtility.setPlayerImage(p, image);
        }

        for(Player p : Bukkit.getOnlinePlayers()) {
            PotionEffect sat = new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 255, false, false, false);
            PotionEffect res = new PotionEffect(PotionEffectType.RESISTANCE, Integer.MAX_VALUE, 255, false, false, false);
            p.addPotionEffect(sat);
            p.addPotionEffect(res);
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
