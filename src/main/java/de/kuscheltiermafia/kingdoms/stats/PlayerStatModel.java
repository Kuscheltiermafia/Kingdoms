package de.kuscheltiermafia.kingdoms.stats;

public class PlayerStatModel {

    float health;
    float defense;
    float intelligence;
    float manaRegeneration;
    float critChance;
    float critDamage;
    float damage;
    float strength;
    float speed;
    float luck;
    float breakingSpeed;
    float fortune;
    float spellbound;

    public PlayerStatModel(float health, float defense, float intelligence, float manaRegeneration, float critChance, float critDamage, float damage, float strength, float speed, float luck, float breakingSpeed, float fortune, float spellbound) {
        this.health = health;
        this.defense = defense;
        this.intelligence = intelligence;
        this.manaRegeneration = manaRegeneration;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.damage = damage;
        this.strength = strength;
        this.speed = speed;
        this.luck = luck;
        this.breakingSpeed = breakingSpeed;
        this.fortune = fortune;
        this.spellbound = spellbound;
    }

    public void resetStats() {
        this.health = 20f;
        this.defense = 0f;
        this.intelligence = 10f;
        this.manaRegeneration = 2f;
        this.critChance = 0.05f;
        this.critDamage = 1f;
        this.damage = 4f;
        this.strength = 4f;
        this.speed = 0.1f;
        this.luck = 0f;
        this.breakingSpeed = 1f;
        this.fortune = 0f;
        this.spellbound = 0f;
    }



    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    public float getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(float intelligence) {
        this.intelligence = intelligence;
    }

    public float getManaRegeneration() {
        return manaRegeneration;
    }

    public void setManaRegeneration(float manaRegeneration) {
        this.manaRegeneration = manaRegeneration;
    }

    public float getCritChance() {
        return critChance;
    }

    public void setCritChance(float critChance) {
        this.critChance = critChance;
    }

    public float getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(float critDamage) {
        this.critDamage = critDamage;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getLuck() {
        return luck;
    }

    public void setLuck(float luck) {
        this.luck = luck;
    }

    public float getBreakingSpeed() {
        return breakingSpeed;
    }

    public void setBreakingSpeed(float breakingSpeed) {
        this.breakingSpeed = breakingSpeed;
    }

    public float getFortune() {
        return fortune;
    }

    public void setFortune(float fortune) {
        this.fortune = fortune;
    }

    public float getSpellbound() {
        return spellbound;
    }

    public void setSpellbound(float spellbound) {
        this.spellbound = spellbound;
    }
}
