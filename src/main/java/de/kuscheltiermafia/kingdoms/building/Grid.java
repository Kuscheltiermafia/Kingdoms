package de.kuscheltiermafia.kingdoms.building;

import org.bukkit.Bukkit;

public class Grid {

    static final int Y_LEVEL = 45;

    static final double DIMENSIONS_BUILDINGS = 8;
    static final double WIDTH_PATHS = 3;

    static final double X_TOWN_HALL = 0;
    static final double Z_TOWN_HALL = 0;

    static final double DIMENSIONS_TOWN_HALL = 2 * DIMENSIONS_BUILDINGS + WIDTH_PATHS;

    public static String getCellID(int x, int z) {
        double deltaX = x - X_TOWN_HALL;
        double deltaZ = z - Z_TOWN_HALL;

        if ((Math.abs(deltaX) < WIDTH_PATHS / 2 && Math.abs(deltaZ) > DIMENSIONS_TOWN_HALL / 2 )||( Math.abs(deltaZ) < WIDTH_PATHS / 2 && Math.abs(deltaX) > DIMENSIONS_TOWN_HALL / 2 )) {
            return "PATH";
        }

        double adjustedDeltaX = deltaX - Math.signum(deltaX) * (DIMENSIONS_TOWN_HALL / 2 - DIMENSIONS_BUILDINGS);
        double adjustedDeltaZ = deltaZ - Math.signum(deltaZ) * (DIMENSIONS_TOWN_HALL / 2 - DIMENSIONS_BUILDINGS);

        if (Math.abs(adjustedDeltaX % (DIMENSIONS_BUILDINGS + WIDTH_PATHS)) >= DIMENSIONS_BUILDINGS || Math.abs(adjustedDeltaZ % (DIMENSIONS_BUILDINGS + WIDTH_PATHS)) >= DIMENSIONS_BUILDINGS) {
            return "PATH";
        }

        double cellX = adjustedDeltaX / (DIMENSIONS_BUILDINGS + WIDTH_PATHS) + 1 * adjustedDeltaX / Math.abs(adjustedDeltaX);
        double cellZ = adjustedDeltaZ / (DIMENSIONS_BUILDINGS + WIDTH_PATHS) + 1 * adjustedDeltaZ / Math.abs(adjustedDeltaZ);

        return String.format("X%dZ%d", (int) cellX, (int) cellZ);
    }

    public static String getCellOrigin(String cellID) {
        if (cellID.equals("PATH")) {
            return null;
        }

        double xIndex;
        double zIndex;

        try {
            xIndex = Double.parseDouble(cellID.split("Z")[0].substring(1));
            zIndex = Double.parseDouble(cellID.split("Z")[1]);
        }catch (Exception e) {
            Bukkit.getLogger().warning("Invalid cellID: " + cellID);
            return null;
        }

        double xCoordinate = (xIndex - 1 * Math.signum(xIndex)) * (int)(DIMENSIONS_BUILDINGS + WIDTH_PATHS) + Math.signum(xIndex) * Math.round(WIDTH_PATHS / 2) + (int)X_TOWN_HALL;
        double zCoordinate = (zIndex - 1 * Math.signum(zIndex)) * (int)(DIMENSIONS_BUILDINGS + WIDTH_PATHS) + Math.signum(zIndex) * Math.round(WIDTH_PATHS / 2) + (int)Z_TOWN_HALL;
        return String.format("%d;%d;%d", (int) xCoordinate, Y_LEVEL, (int) zCoordinate);

    }

}
