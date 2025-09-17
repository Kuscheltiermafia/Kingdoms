package de.kuscheltiermafia.kingdoms.menus;

import org.bukkit.entity.Player;

import java.util.HashMap;

public interface IPaginatedGui {
    HashMap<Player, Integer> pages = new HashMap<>();

    default boolean isPaginated() {
        return true;
    };

    void setPage(Player p, int page);
    int getPage(Player p);
    void nextPage(Player p);
    void previousPage(Player p);
}
