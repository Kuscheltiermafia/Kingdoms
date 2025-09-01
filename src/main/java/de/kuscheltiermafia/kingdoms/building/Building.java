package de.kuscheltiermafia.kingdoms.building;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.structure.Structure;

import java.util.Map;
import java.util.Random;

public enum Building {

    ARCH_TOWER("arch_tower", 1 , -1),
    BAKERY("bakery", 1 , -1),
    CAMP("camp", 1 , 0),
    FARM("farm", 1 , 0),
    LUMBERJACK("lumberjack", 1, 0),
    MINE("mine", 1, -5),
    QUARRY("quarry", 1, 0),
    TOWN_HALL("town_hall", 2, 0);

    final String structureName;
    final int maxLevel;
    final int offsetY;

    Building(String structureName, int maxLevel, int offsetY) {
        this.structureName = structureName;
        this.maxLevel = maxLevel;
        this.offsetY = offsetY;
    }

    public static Building fromString(String name) {
        for (Building building : Building.values()) {
            if (building.structureName.equalsIgnoreCase(name)) {
                return building;
            }
        }
        return null;
    }

    public String getStructureName() {
        return structureName;
    }

    public static void placeBuilding(String cell, Building building, int level) {

        String cellOrigin = Grid.getCellOrigin(cell);
        String[] coords = cellOrigin.split(";");
        Location location = new Location(Bukkit.getWorlds().get(0), Double.parseDouble(coords[0]), Integer.parseInt(coords[1]), Double.parseDouble(coords[2]));

        String rotationKey = "";
        if (coords[0].contains("-")) rotationKey += "-"; else rotationKey += "+";
        if (coords[2].contains("-")) rotationKey += "-"; else rotationKey += "+";

        Map<String, Integer> rotations = Map.of(
                "++", 0,
                "-+", 1,
                "--", 2,
                "+-", 3
        );

        int rotation = rotations.get(rotationKey);

        generateStructure(location, building, level, rotation);

    }

    public static void generateStructure(Location location, Building building, int level, int rotation) {

        NamespacedKey key = new NamespacedKey("kingdoms", building.structureName + "/level" + level);
        Structure structure = Bukkit.getStructureManager().loadStructure(key);
        if (structure == null) {
            Bukkit.getLogger().warning("Structure " + building.name() + " not found.");
            return;
        }

        StructureRotation structureRotation = StructureRotation.values()[rotation % StructureRotation.values().length];

        structure.place(location.add(0, building.offsetY - 1, 0), true, structureRotation, Mirror.NONE, 0, 1.0f, new Random());
    }
}
