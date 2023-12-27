package de.kuscheltiermafia.kingdoms;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemHandler.createItems();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
