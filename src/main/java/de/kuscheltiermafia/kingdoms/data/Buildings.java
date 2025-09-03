package de.kuscheltiermafia.kingdoms.data;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.building.Building;
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

    public Building getBuilding(String cell) {
        String structureName = config.getString(cell, "none");
        if (structureName.equals("none")) return null;
        return Building.fromString(structureName);
    }

    public void setBuilding(String cell, Building building) {
        config.set(cell, building.getStructureName());
        try {
            config.save(file);
        } catch (Exception e) {
            Kingdoms.getPlugin().getLogger().warning("Could not save building data for position " + cell);
        }
    }

}
