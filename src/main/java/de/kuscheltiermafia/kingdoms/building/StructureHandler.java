package de.kuscheltiermafia.kingdoms.building;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.io.File;
import java.util.Random;

public class StructureHandler {

    static StructureManager structureManager = Bukkit.getStructureManager();

    public static void placeStructure(Location location, String structureName, int rotation) {

        NamespacedKey key = new NamespacedKey("kingdoms", structureName);
        File file = Bukkit.getStructureManager().getStructureFile(key);
        Structure structure = Bukkit.getStructureManager().loadStructure(key);

        StructureRotation structureRotation = StructureRotation.values()[rotation % StructureRotation.values().length];

        if (structure == null) {
            Bukkit.getLogger().warning("Structure " + structureName + " not found.");
            return;
        }
        structure.place(location, true, structureRotation, Mirror.NONE, 0, 1.0f, new Random());

    }

}
