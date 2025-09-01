package de.kuscheltiermafia.kingdoms.building;

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
}
