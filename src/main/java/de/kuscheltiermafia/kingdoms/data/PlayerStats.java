package de.kuscheltiermafia.kingdoms.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PlayerStats {

    double combatXP;
    int combatLevel;

    double miningXP;
    int miningLevel;

    double foragingXP;
    int foragingLevel;

    double enchantingXP;
    int enchantingLevel;

    double farmingXP;
    int farmingLevel;

    double fishingXP;
    int fishingLevel;

    double buildingXP;
    int buildingLevel;

    double alchemyXP;
    int alchemyLevel;

    double wizardryXP;
    int wizardryLevel;

    double tinkeringXP;
    int tinkeringLevel;

    double huntingXP;
    int huntingLevel;

    public PlayerStats() {
        // Initialize all stats to 0
        this.combatXP = 0;
        this.combatLevel = 0;
        this.miningXP = 0;
        this.miningLevel = 0;
        this.foragingXP = 0;
        this.foragingLevel = 0;
        this.enchantingXP = 0;
        this.enchantingLevel = 0;
        this.farmingXP = 0;
        this.farmingLevel = 0;
        this.fishingXP = 0;
        this.fishingLevel = 0;
        this.buildingXP = 0;
        this.buildingLevel = 0;
        this.alchemyXP = 0;
        this.alchemyLevel = 0;
        this.wizardryXP = 0;
        this.wizardryLevel = 0;
        this.tinkeringXP = 0;
        this.tinkeringLevel = 0;
        this.huntingXP = 0;
        this.huntingLevel = 0;
    }


    public void loadStats(File file){

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        this.setCombatLevel(config.getInt("Combat.Level"));
        this.setCombatXP(config.getDouble("Combat.XP"));
        this.setMiningLevel(config.getInt("Mining.Level"));
        this.setMiningXP(config.getDouble("Mining.XP"));
        this.setForagingLevel(config.getInt("Foraging.Level"));
        this.setForagingXP(config.getDouble("Foraging.XP"));
        this.setEnchantingLevel(config.getInt("Enchanting.Level"));
        this.setEnchantingXP(config.getDouble("Enchanting.XP"));
        this.setFarmingLevel(config.getInt("Farming.Level"));
        this.setFarmingXP(config.getDouble("Farming.XP"));
        this.setFishingLevel(config.getInt("Fishing.Level"));
        this.setFishingXP(config.getDouble("Fishing.XP"));
        this.setBuildingLevel(config.getInt("Building.Level"));
        this.setBuildingXP(config.getDouble("Building.XP"));
        this.setAlchemyLevel(config.getInt("Alchemy.Level"));
        this.setAlchemyXP(config.getDouble("Alchemy.XP"));
        this.setWizardryLevel(config.getInt("Wizardry.Level"));
        this.setWizardryXP(config.getDouble("Wizardry.XP"));
        this.setTinkeringLevel(config.getInt("Tinkering.Level"));
        this.setTinkeringXP(config.getDouble("Tinkering.XP"));
        this.setHuntingLevel(config.getInt("Hunting.Level"));
        this.setHuntingXP(config.getDouble("Hunting.XP"));

    }

    public double getLevelXP(int level) {
        return Math.pow(level, 2) * 50;
    }

    public double getCombatXP() {
        return combatXP;
    }

    public void setCombatXP(double combatXP) {
        this.combatXP = combatXP;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public void setCombatLevel(int combatLevel) {
        this.combatLevel = combatLevel;
    }

    public void addCombatXP(double xp) {
        this.combatXP += xp;
        while (this.combatXP >= getLevelXP(this.combatLevel + 1)) {
            this.combatXP -= getLevelXP(this.combatLevel + 1);
            this.combatLevel++;
        }
    }

    public double getMiningXP() {
        return miningXP;
    }

    public void setMiningXP(double miningXP) {
        this.miningXP = miningXP;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    public void setMiningLevel(int miningLevel) {
        this.miningLevel = miningLevel;
    }

    public void addMiningXP(double xp) {
        this.miningXP += xp;
        while (this.miningXP >= getLevelXP(this.miningLevel + 1)) {
            this.miningXP -= getLevelXP(this.miningLevel + 1);
            this.miningLevel++;
        }
    }

    public double getForagingXP() {
        return foragingXP;
    }

    public void setForagingXP(double foragingXP) {
        this.foragingXP = foragingXP;
    }

    public int getForagingLevel() {
        return foragingLevel;
    }

    public void setForagingLevel(int foragingLevel) {
        this.foragingLevel = foragingLevel;
    }

    public void addForagingXP(double xp) {
        this.foragingXP += xp;
        while (this.foragingXP >= getLevelXP(this.foragingLevel + 1)) {
            this.foragingXP -= getLevelXP(this.foragingLevel + 1);
            this.foragingLevel++;
        }
    }

    public double getEnchantingXP() {
        return enchantingXP;
    }

    public void setEnchantingXP(double enchantingXP) {
        this.enchantingXP = enchantingXP;
    }

    public int getEnchantingLevel() {
        return enchantingLevel;
    }

    public void setEnchantingLevel(int enchantingLevel) {
        this.enchantingLevel = enchantingLevel;
    }

    public void addEnchantingXP(double xp) {
        this.enchantingXP += xp;
        while (this.enchantingXP >= getLevelXP(this.enchantingLevel + 1)) {
            this.enchantingXP -= getLevelXP(this.enchantingLevel + 1);
            this.enchantingLevel++;
        }
    }

    public double getFarmingXP() {
        return farmingXP;
    }

    public void setFarmingXP(double farmingXP) {
        this.farmingXP = farmingXP;
    }

    public int getFarmingLevel() {
        return farmingLevel;
    }

    public void setFarmingLevel(int farmingLevel) {
        this.farmingLevel = farmingLevel;
    }

    public void addFarmingXP(double xp) {
        this.farmingXP += xp;
        while (this.farmingXP >= getLevelXP(this.farmingLevel + 1)) {
            this.farmingXP -= getLevelXP(this.farmingLevel + 1);
            this.farmingLevel++;
        }
    }

    public double getFishingXP() {
        return fishingXP;
    }

    public void setFishingXP(double fishingXP) {
        this.fishingXP = fishingXP;
    }

    public int getFishingLevel() {
        return fishingLevel;
    }

    public void setFishingLevel(int fishingLevel) {
        this.fishingLevel = fishingLevel;
    }

    public void addFishingXP(double xp) {
        this.fishingXP += xp;
        while (this.fishingXP >= getLevelXP(this.fishingLevel + 1)) {
            this.fishingXP -= getLevelXP(this.fishingLevel + 1);
            this.fishingLevel++;
        }
    }

    public double getBuildingXP() {
        return buildingXP;
    }

    public void setBuildingXP(double buildingXP) {
        this.buildingXP = buildingXP;
    }

    public int getBuildingLevel() {
        return buildingLevel;
    }

    public void setBuildingLevel(int buildingLevel) {
        this.buildingLevel = buildingLevel;
    }

    public void addBuildingXP(double xp) {
        this.buildingXP += xp;
        while (this.buildingXP >= getLevelXP(this.buildingLevel + 1)) {
            this.buildingXP -= getLevelXP(this.buildingLevel + 1);
            this.buildingLevel++;
        }
    }

    public double getAlchemyXP() {
        return alchemyXP;
    }

    public void setAlchemyXP(double alchemyXP) {
        this.alchemyXP = alchemyXP;
    }

    public int getAlchemyLevel() {
        return alchemyLevel;
    }

    public void setAlchemyLevel(int alchemyLevel) {
        this.alchemyLevel = alchemyLevel;
    }

    public void addAlchemyXP(double xp) {
        this.alchemyXP += xp;
        while (this.alchemyXP >= getLevelXP(this.alchemyLevel + 1)) {
            this.alchemyXP -= getLevelXP(this.alchemyLevel + 1);
            this.alchemyLevel++;
        }
    }

    public double getWizardryXP() {
        return wizardryXP;
    }

    public void setWizardryXP(double wizardryXP) {
        this.wizardryXP = wizardryXP;
    }

    public int getWizardryLevel() {
        return wizardryLevel;
    }

    public void setWizardryLevel(int wizardryLevel) {
        this.wizardryLevel = wizardryLevel;
    }

    public void addWizardryXP(double xp) {
        this.wizardryXP += xp;
        while (this.wizardryXP >= getLevelXP(this.wizardryLevel + 1)) {
            this.wizardryXP -= getLevelXP(this.wizardryLevel + 1);
            this.wizardryLevel++;
        }
    }

    public double getTinkeringXP() {
        return tinkeringXP;
    }

    public void setTinkeringXP(double tinkeringXP) {
        this.tinkeringXP = tinkeringXP;
    }

    public int getTinkeringLevel() {
        return tinkeringLevel;
    }

    public void setTinkeringLevel(int tinkeringLevel) {
        this.tinkeringLevel = tinkeringLevel;
    }

    public void addTinkeringXP(double xp) {
        this.tinkeringXP += xp;
        while (this.tinkeringXP >= getLevelXP(this.tinkeringLevel + 1)) {
            this.tinkeringXP -= getLevelXP(this.tinkeringLevel + 1);
            this.tinkeringLevel++;
        }
    }

    public double getHuntingXP() {
        return huntingXP;
    }

    public void setHuntingXP(double huntingXP) {
        this.huntingXP = huntingXP;
    }

    public int getHuntingLevel() {
        return huntingLevel;
    }

    public void setHuntingLevel(int huntingLevel) {
        this.huntingLevel = huntingLevel;
    }

    public void addHuntingXP(double xp) {
        this.huntingXP += xp;
        while (this.huntingXP >= getLevelXP(this.huntingLevel + 1)) {
            this.huntingXP -= getLevelXP(this.huntingLevel + 1);
            this.huntingLevel++;
        }
    }
}
