package de.kuscheltiermafia.kingdoms.building;

import org.bukkit.Bukkit;

public class Grid {

    static final double DIMENSIONS_TOWN_HALL = 19;
    static final double DIMENSIONS_BUILDINGS = 8;
    static final double WIDTH_PATHS = 3;

    static final double X_TOWN_HALL = 0;
    static final double Z_TOWN_HALL = 0;

    public static String getCellID(int x, int z) {
        double deltaX = x - X_TOWN_HALL;
        double deltaZ = z - Z_TOWN_HALL;

        double adjustedDeltaX = deltaX - deltaX / Math.abs(deltaX) * (Math.round(DIMENSIONS_TOWN_HALL / 2) - DIMENSIONS_BUILDINGS);
        double adjustedDeltaZ = deltaZ - deltaZ / Math.abs(deltaZ) * (Math.round(DIMENSIONS_TOWN_HALL / 2) - DIMENSIONS_BUILDINGS);

        double cellX = adjustedDeltaX / (DIMENSIONS_BUILDINGS + WIDTH_PATHS) + 1 * adjustedDeltaX / Math.abs(adjustedDeltaX);
        double cellZ = adjustedDeltaZ / (DIMENSIONS_BUILDINGS + WIDTH_PATHS) + 1 * adjustedDeltaZ / Math.abs(adjustedDeltaZ);

        if (adjustedDeltaX % (DIMENSIONS_BUILDINGS + WIDTH_PATHS) > DIMENSIONS_BUILDINGS || adjustedDeltaZ % (DIMENSIONS_BUILDINGS + WIDTH_PATHS) > DIMENSIONS_BUILDINGS) {
            return "PATH";
        }

        return String.format("X%dZ%d", (int) cellX, (int) cellZ);
    }

}
