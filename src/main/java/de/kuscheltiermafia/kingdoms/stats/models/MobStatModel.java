package de.kuscheltiermafia.kingdoms.stats.models;

import de.kuscheltiermafia.kingdoms.stats.Stat;

public class MobStatModel extends StatModel {

    private double actualHealth;
    private double effectiveHealth;

    public MobStatModel(double health, double defense, double damage, double strength, double speed, double ferocity) {
        this.setStat(Stat.HEALTH, health, false);
        this.setStat(Stat.DEFENSE, defense, false);
        this.setStat(Stat.DAMAGE, damage, false);
        this.setStat(Stat.STRENGTH, strength, false);
        this.setStat(Stat.SPEED, speed, false);
        this.setStat(Stat.FEROCITY, ferocity, false);
        initializeIndirectStats();
    }

    public void initializeIndirectStats() {
        this.effectiveHealth = this.getStat(Stat.HEALTH, false) * (1 + (this.getStat(Stat.DEFENSE, false) / 100));
        this.actualHealth = this.effectiveHealth;
        initFinalStats();
    }

    @Override
    public double getActiveHealth() {
        return actualHealth;
    }

    @Override
    public void setActiveHealth(double actualHealth) {
        this.actualHealth = actualHealth;
    }
}
