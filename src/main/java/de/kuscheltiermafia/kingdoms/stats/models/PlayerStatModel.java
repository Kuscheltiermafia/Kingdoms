package de.kuscheltiermafia.kingdoms.stats.models;

import de.kuscheltiermafia.kingdoms.stats.Stat;

public class PlayerStatModel extends StatModel {

    private double actualHealth;
    private double effectiveHealth;
    private double actualMana;
    private double maxMana;

    @Override
    public void resetStats() {
        this.setStat(Stat.HEALTH, 100, false);
        this.setStat(Stat.DEFENSE, 0, false);
        this.setStat(Stat.INTELLIGENCE, 100, false);
        this.setStat(Stat.MANA_REGENERATION, 2, false);
        this.setStat(Stat.CRIT_CHANCE, 5, false);
        this.setStat(Stat.CRIT_DAMAGE, 10, false);
        this.setStat(Stat.DAMAGE, 5, false);
        this.setStat(Stat.STRENGTH, 5, false);
        this.setStat(Stat.SPEED, 100, false);
        this.setStat(Stat.LUCK, 0, false);
        this.setStat(Stat.BREAKING_SPEED, 100, false);
        this.setStat(Stat.FORTUNE, 0, false);
        this.setStat(Stat.SPELLBOUND, 0, false);
        this.setStat(Stat.OVERHEAL, 0, false);
        this.setStat(Stat.VEIL, 0, false);
        this.setStat(Stat.LIFESTEAL, 0, false);
        this.setStat(Stat.ABSORPTION, 0, false);
        this.setStat(Stat.SPREAD, 0, false);
        this.setStat(Stat.MANA_STEAL, 0, false);
        this.setStat(Stat.VITALITY, 0, false);
        this.setStat(Stat.FEROCITY, 0, false);

        initializeIndirectStats(true);
    }

    public void initializeIndirectStats(boolean resetHealthMana) {
        this.effectiveHealth = this.getStat(Stat.HEALTH, true) * (1 + (this.getStat(Stat.DEFENSE, true) / 100));
        this.maxMana = this.getStat(Stat.INTELLIGENCE, true) * (1 + (this.getStat(Stat.SPELLBOUND, true) / 100));

        if(resetHealthMana) {
            this.actualHealth = this.effectiveHealth;
            this.actualMana = this.maxMana;
        }
    }

    @Override
    public double getActiveHealth() {
        return actualHealth;
    }

    @Override
    public double getMaxHealth() {
        return effectiveHealth;
    }

    @Override
    public void setActiveHealth(double activeHealth) {
        this.actualHealth = Math.round(activeHealth);
    }

    @Override
    public double getActiveMana() {
        return actualMana;
    }

    @Override
    public void setActiveMana(double activeMana) {
        this.actualMana = Math.round(activeMana);
    }

    @Override
    public double getMaxMana() {
        return this.maxMana;
    }
}
