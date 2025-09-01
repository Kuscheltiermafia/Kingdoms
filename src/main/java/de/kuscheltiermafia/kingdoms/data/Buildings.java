package de.kuscheltiermafia.kingdoms.data;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Buildings {

    static File file = new File(Kingdoms.getPlugin().getDataFolder().getAbsolutePath() + "/buildings.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static boolean setupConfig() throws IOException {
        if (file.exists()) {
            return true;
        }else if (file.createNewFile()) {
            config.save(file);
            return true;
        }
        return false;
    }

    public String getBuilding(String position) {
        return config.getString(position, "none");
    }

    public void setBuilding(String position, String building) {
        config.set(position, building);
        try {
            config.save(file);
        } catch (Exception e) {
            Kingdoms.getPlugin().getLogger().warning("Could not save building data for position " + position);
        }
    }

}
