package de.kuscheltiermafia.kingdoms.mobs;

import de.kuscheltiermafia.kingdoms.stats.Stat;

public class MobStatModel {

    double health;
    double actualHealth;
    double defense;
    double damage;
    double strength;
    double speed;
    double ferocity;

    public MobStatModel(double health, double defense, double damage, double strength, double speed, double ferocity) {
        this.health = health;
        this.defense = defense;
        this.damage = damage;
        this.strength = strength;
        this.speed = speed;
        this.ferocity = ferocity;
        initializeIndirectStats();
    }

    public void initializeIndirectStats() {
        this.actualHealth = this.health;
    }

    public double getStat(Stat stat) {
        switch (stat) {
            case HEALTH:
                return this.health;
            case DEFENSE:
                return this.defense;
            case DAMAGE:
                return this.damage;
            case STRENGTH:
                return this.strength;
            case SPEED:
                return this.speed;
            case FEROCITY:
                return this.ferocity;
            default:
                return 0;
        }
    }

    public void setStat(Stat stat, double value) {
        switch (stat) {
            case HEALTH:
                this.health = value;
                break;
            case DEFENSE:
                this.defense = value;
                break;
            case DAMAGE:
                this.damage = value;
                break;
            case STRENGTH:
                this.strength = value;
                break;
            case SPEED:
                this.speed = value;
                break;
            case FEROCITY:
                this.ferocity = value;
                break;
            default:
                break;
        }
    }

    public double getActiveHealth() {
        return actualHealth;
    }

    public void setActiveHealth(double actualHealth) {
        this.actualHealth = actualHealth;
    }
}
