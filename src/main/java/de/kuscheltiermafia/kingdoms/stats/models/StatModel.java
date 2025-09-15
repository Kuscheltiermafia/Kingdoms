package de.kuscheltiermafia.kingdoms.stats.models;

import de.kuscheltiermafia.kingdoms.stats.Stat;

import java.util.HashMap;

public class StatModel {

    protected HashMap<Stat, Double> baseStatMap = new HashMap<>();

    protected HashMap<Stat, Double> finalStatMap = new HashMap<>();

    public StatModel() {
        for (Stat stat : Stat.values()) {
            baseStatMap.put(stat, 0.0);
        }
    }

    public void resetStats() {}
    public void initFinalStats() {finalStatMap = new HashMap<>(baseStatMap);}

    public double getStat(de.kuscheltiermafia.kingdoms.stats.Stat stat, boolean finalStat) {
        if(finalStat) return finalStatMap.getOrDefault(stat, 0.0);
        return baseStatMap.getOrDefault(stat, 0.0);
    }

    public void setStat(de.kuscheltiermafia.kingdoms.stats.Stat stat, double value, boolean finalStat) {
        if(finalStat) {
            finalStatMap.put(stat, value);
            return;
        }
        baseStatMap.put(stat, value);
    }

    public double getActiveHealth() {return 0;}
    public double getMaxHealth() {return 0;}

    public void setActiveHealth(double health) {}

    public double getActiveMana() {
        return 0;
    }

    public void setActiveMana(double activeMana) {}

    public double getMaxMana() {return 0;}
}
