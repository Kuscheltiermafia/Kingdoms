package de.kuscheltiermafia.kingdoms.stats;

public class PlayerStatModel {

    double health;
    double actualHealth;
    double defense;
    double intelligence;
    double actualMana;
    double maxMana;
    double manaRegeneration;
    double critChance;
    double critDamage;
    double damage;
    double strength;
    double speed;
    double luck;
    double breakingSpeed;
    double fortune;
    double spellbound;
    double overheal;
    double veil;
    double lifesteal;
    double absorption;
    double spread;
    double manaSteal;

    public void resetStats() {
        this.health = 20;
        this.defense = 0;
        this.intelligence = 10;
        this.manaRegeneration = 2;
        this.critChance = 0.05;
        this.critDamage = 1;
        this.damage = 4;
        this.strength = 4;
        this.speed = 0.1;
        this.luck = 0;
        this.breakingSpeed = 1;
        this.fortune = 0;
        this.spellbound = 0;
        this.overheal = 0;
        this.veil = 0;
        this.lifesteal = 0;
        this.absorption = 0;
        this.spread = 0;
        this.manaSteal = 0;
        this.actualHealth = this.health;
        this.maxMana = this.intelligence * (1 + (this.spellbound / 100));
        this.actualMana = this.maxMana;
    }

    public double getStat(Stat stat) {
        switch (stat) {
            case HEALTH:
                return health;
            case DEFENSE:
                return defense;
            case INTELLIGENCE:
                return intelligence;
            case MANA_REGENERATION:
                return manaRegeneration;
            case CRIT_CHANCE:
                return critChance;
            case CRIT_DAMAGE:
                return critDamage;
            case DAMAGE:
                return damage;
            case STRENGTH:
                return strength;
            case SPEED:
                return speed;
            case LUCK:
                return luck;
            case BREAKING_SPEED:
                return breakingSpeed;
            case FORTUNE:
                return fortune;
            case SPELLBOUND:
                return spellbound;
            case OVERHEAL:
                return overheal;
            case VEIL:
                return veil;
            case LIFESTEAL:
                return lifesteal;
            case ABSORPTION:
                return absorption;
            case SPREAD:
                return spread;
            case MANA_STEAL:
                return manaSteal;
            default:
                throw new IllegalArgumentException("Unknown stat: " + stat);
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
            case INTELLIGENCE:
                this.intelligence = value;
                break;
            case MANA_REGENERATION:
                this.manaRegeneration = value;
                break;
            case CRIT_CHANCE:
                this.critChance = value;
                break;
            case CRIT_DAMAGE:
                this.critDamage = value;
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
            case LUCK:
                this.luck = value;
                break;
            case BREAKING_SPEED:
                this.breakingSpeed = value;
                break;
            case FORTUNE:
                this.fortune = value;
                break;
            case SPELLBOUND:
                this.spellbound = value;
                break;
            case OVERHEAL:
                this.overheal = value;
                break;
            case VEIL:
                this.veil = value;
                break;
            case LIFESTEAL:
                this.lifesteal = value;
                break;
            case ABSORPTION:
                this.absorption = value;
                break;
            case SPREAD:
                this.spread = value;
                break;
            case MANA_STEAL:
                this.manaSteal = value;
                break;
            default:
                throw new IllegalArgumentException("Unknown stat: " + stat);
        }
    }

    public double getActiveHealth() {
        return actualHealth;
    }
    public void setActiveHealth(double activeMana) {
        this.actualHealth = actualHealth;
    }

    public double getActiveMana() {
        return actualMana;
    }

    public void setActiveMana(double activeMana) {
        this.actualMana = actualMana;
    }

    public double getMaxMana() {
        return this.maxMana;
    }
}
