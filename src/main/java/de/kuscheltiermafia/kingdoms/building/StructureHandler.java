package de.kuscheltiermafia.kingdoms.building;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.structure.StructureManager;

public class StructureHandler {

    StructureManager structureManager = Bukkit.getStructureManager();

    public void registerNew(String key) {
        structureManager.registerStructure(new NamespacedKey(Kingdoms.getPlugin(), key), );
    }

}
