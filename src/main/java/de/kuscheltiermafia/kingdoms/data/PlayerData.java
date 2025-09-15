package de.kuscheltiermafia.kingdoms.data;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.skills.Skill;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import de.kuscheltiermafia.kingdoms.stats.models.PlayerStatModel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PlayerData {

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

    PlayerStatModel correspondingStatModel;

    public PlayerData() {
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

    public static boolean setupConfig(Player player) throws IOException {
        PlayerData image = new PlayerData();

        File file = new File(PlayerUtility.getFolderPath(player) + "stats.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        boolean newFileCreated = file.createNewFile();

        if (newFileCreated) {
            Bukkit.getLogger().info("Creating new stats.yml for player " + player.getName());
            InputStream defaultConfigStream = Kingdoms.getPlugin().getResource("stats.yml");
            if (defaultConfigStream == null) {
                Kingdoms.getPlugin().getLogger().warning("Could not find default stats.yml in plugin resources while trying to initiate stats.yml for player " + player.getName() + ". If this error persists, please contact the plugin authors.");
                return false;
            }
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultConfigStream));

            config.setDefaults(defaultConfig);
            config.options().copyDefaults(true);
            config.save(file);
        }else{
            Bukkit.getLogger().info("Loading existing stats.yml for player " + player.getName());

            PlayerStatModel imageStatModel = new PlayerStatModel();
            image.setCorrespondingStatModel(imageStatModel);
            Kingdoms.playerStatModelIdentifier.put(player, imageStatModel);
            image.loadStats(config);
        }

        PlayerUtility.setPlayerImage(player, image);
        return true;
    }

    public void loadStats(YamlConfiguration config) {

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

        if(this.correspondingStatModel.getStat(Stat.HEALTH, false) == 0) {
            this.correspondingStatModel.resetStats();
        }else {
            this.correspondingStatModel.setStat(Stat.HEALTH, config.getDouble("Stats.Health"), false);
            this.correspondingStatModel.setStat(Stat.DEFENSE, config.getDouble("Stats.Defense"), false);
            this.correspondingStatModel.setStat(Stat.INTELLIGENCE, config.getDouble("Stats.Intelligence"), false);
            this.correspondingStatModel.setStat(Stat.MANA_REGENERATION, config.getDouble("Stats.ManaRegeneration"), false);
            this.correspondingStatModel.setStat(Stat.CRIT_CHANCE, config.getDouble("Stats.CritChance"), false);
            this.correspondingStatModel.setStat(Stat.CRIT_DAMAGE, config.getDouble("Stats.CritDamage"), false);
            this.correspondingStatModel.setStat(Stat.DAMAGE, config.getDouble("Stats.Damage"), false);
            this.correspondingStatModel.setStat(Stat.STRENGTH, config.getDouble("Stats.Strength"), false);
            this.correspondingStatModel.setStat(Stat.SPEED, config.getDouble("Stats.Speed"), false);
            this.correspondingStatModel.setStat(Stat.LUCK, config.getDouble("Stats.Luck"), false);
            this.correspondingStatModel.setStat(Stat.BREAKING_SPEED, config.getDouble("Stats.BreakingSpeed"), false);
            this.correspondingStatModel.setStat(Stat.FORTUNE, config.getDouble("Stats.Fortune"), false);
            this.correspondingStatModel.setStat(Stat.SPELLBOUND, config.getDouble("Stats.Spellbound"), false);
            this.correspondingStatModel.setStat(Stat.OVERHEAL, config.getDouble("Stats.Overheal"), false);
            this.correspondingStatModel.setStat(Stat.VEIL, config.getDouble("Stats.Veil"), false);
            this.correspondingStatModel.setStat(Stat.LIFESTEAL, config.getDouble("Stats.Lifesteal"), false);
            this.correspondingStatModel.setStat(Stat.ABSORPTION, config.getDouble("Stats.Absorption"), false);
            this.correspondingStatModel.setStat(Stat.SPREAD, config.getDouble("Stats.Spread"), false);
            this.correspondingStatModel.setStat(Stat.MANA_STEAL, config.getDouble("Stats.ManaSteal"), false);
            this.correspondingStatModel.setStat(Stat.FEROCITY, config.getDouble("Stats.Ferocity"), false);
            this.correspondingStatModel.setStat(Stat.VITALITY, config.getDouble("Stats.Vitality"), false);
        }
        this.correspondingStatModel.initializeIndirectStats(true);
    }

    public void storeData(Player player) {
        File file = new File(PlayerUtility.getFolderPath(player) + "stats.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        // Save skill levels and XP
        config.set("Combat.Level", this.getCombatLevel());
        config.set("Combat.XP", this.getCombatXP());
        config.set("Mining.Level", this.getMiningLevel());
        config.set("Mining.XP", this.getMiningXP());
        config.set("Foraging.Level", this.getForagingLevel());
        config.set("Foraging.XP", this.getForagingXP());
        config.set("Enchanting.Level", this.getEnchantingLevel());
        config.set("Enchanting.XP", this.getEnchantingXP());
        config.set("Farming.Level", this.getFarmingLevel());
        config.set("Farming.XP", this.getFarmingXP());
        config.set("Fishing.Level", this.getFishingLevel());
        config.set("Fishing.XP", this.getFishingXP());
        config.set("Building.Level", this.getBuildingLevel());
        config.set("Building.XP", this.getBuildingXP());
        config.set("Alchemy.Level", this.getAlchemyLevel());
        config.set("Alchemy.XP", this.getAlchemyXP());
        config.set("Wizardry.Level", this.getWizardryLevel());
        config.set("Wizardry.XP", this.getWizardryXP());
        config.set("Tinkering.Level", this.getTinkeringLevel());
        config.set("Tinkering.XP", this.getTinkeringXP());
        config.set("Hunting.Level", this.getHuntingLevel());
        config.set("Hunting.XP", this.getHuntingXP());

        // Save stats from the correspondingStatModel
        if (this.correspondingStatModel != null) {
            config.set("Stats.Health", this.correspondingStatModel.getStat(Stat.HEALTH, false));
            config.set("Stats.Defense", this.correspondingStatModel.getStat(Stat.DEFENSE, false));
            config.set("Stats.Intelligence", this.correspondingStatModel.getStat(Stat.INTELLIGENCE, false));
            config.set("Stats.ManaRegeneration", this.correspondingStatModel.getStat(Stat.MANA_REGENERATION, false));
            config.set("Stats.CritChance", this.correspondingStatModel.getStat(Stat.CRIT_CHANCE, false));
            config.set("Stats.CritDamage", this.correspondingStatModel.getStat(Stat.CRIT_DAMAGE, false));
            config.set("Stats.Damage", this.correspondingStatModel.getStat(Stat.DAMAGE, false));
            config.set("Stats.Strength", this.correspondingStatModel.getStat(Stat.STRENGTH, false));
            config.set("Stats.Speed", this.correspondingStatModel.getStat(Stat.SPEED, false));
            config.set("Stats.Luck", this.correspondingStatModel.getStat(Stat.LUCK, false));
            config.set("Stats.BreakingSpeed", this.correspondingStatModel.getStat(Stat.BREAKING_SPEED, false));
            config.set("Stats.Fortune", this.correspondingStatModel.getStat(Stat.FORTUNE, false));
            config.set("Stats.Spellbound", this.correspondingStatModel.getStat(Stat.SPELLBOUND, false));
            config.set("Stats.Overheal", this.correspondingStatModel.getStat(Stat.OVERHEAL, false));
            config.set("Stats.Veil", this.correspondingStatModel.getStat(Stat.VEIL, false));
            config.set("Stats.Lifesteal", this.correspondingStatModel.getStat(Stat.LIFESTEAL, false));
            config.set("Stats.Absorption", this.correspondingStatModel.getStat(Stat.ABSORPTION, false));
            config.set("Stats.Spread", this.correspondingStatModel.getStat(Stat.SPREAD, false));
            config.set("Stats.ManaSteal", this.correspondingStatModel.getStat(Stat.MANA_STEAL, false));
            config.set("Stats.Ferocity", this.correspondingStatModel.getStat(Stat.FEROCITY, false));
            config.set("Stats.Vitality", this.correspondingStatModel.getStat(Stat.VITALITY, false));
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning("Failed to save stats.yml for player " + player.getName());
        }
    }

    public double getValueBySkill(Skill skill, boolean asXP) {
        switch (skill) {
            case COMBAT:
                if (asXP) {
                    return combatXP;
                }else{
                    return combatLevel;
                }
            case MINING:
                if (asXP) {
                    return miningXP;
                }else{
                    return miningLevel;
                }
            case FORAGING:
                if (asXP) {
                    return foragingXP;
                }else{
                    return foragingLevel;
                }
            case ENCHANTING:
                if (asXP) {
                    return enchantingXP;
                }else{
                    return enchantingLevel;
                }
            case FARMING:
                if (asXP) {
                    return farmingXP;
                }else{
                    return farmingLevel;
                }
            case FISHING:
                if (asXP) {
                    return fishingXP;
                }else{
                    return fishingLevel;
                }
            case BUILDING:
                if (asXP) {
                    return buildingXP;
                }else{
                    return buildingLevel;
                }
            case ALCHEMY:
                if (asXP) {
                    return alchemyXP;
                }else{
                    return alchemyLevel;
                }
            case WIZARDRY:
                if (asXP) {
                    return wizardryXP;
                }else{
                    return wizardryLevel;
                }
            case TINKERING:
                if (asXP) {
                    return tinkeringXP;
                }else{
                    return tinkeringLevel;
                }
            case HUNTING:
                if (asXP) {
                    return huntingXP;
                }else{
                    return huntingLevel;
                }
            default:
                throw new IllegalArgumentException("Unknown skill: " + skill);
        }
    }

    public void setValueBySkill(Skill skill, double value, boolean asXP) {
        switch (skill) {
            case COMBAT:
                if (asXP) {
                    this.setCombatXP(value);
                } else {
                    this.setCombatLevel((int) value);
                }
                break;
            case MINING:
                if (asXP) {
                    this.setMiningXP(value);
                } else {
                    this.setMiningLevel((int) value);
                }
                break;
            case FORAGING:
                if (asXP) {
                    this.setForagingXP(value);
                } else {
                    this.setForagingLevel((int) value);
                }
                break;
            case ENCHANTING:
                if (asXP) {
                    this.setEnchantingXP(value);
                } else {
                    this.setEnchantingLevel((int) value);
                }
                break;
            case FARMING:
                if (asXP) {
                    this.setFarmingXP(value);
                } else {
                    this.setFarmingLevel((int) value);
                }
                break;
            case FISHING:
                if (asXP) {
                    this.setFishingXP(value);
                } else {
                    this.setFishingLevel((int) value);
                }
                break;
            case BUILDING:
                if (asXP) {
                    this.setBuildingXP(value);
                } else {
                    this.setBuildingLevel((int) value);
                }
                break;
            case ALCHEMY:
                if (asXP) {
                    this.setAlchemyXP(value);
                } else {
                    this.setAlchemyLevel((int) value);
                }
                break;
            case WIZARDRY:
                if (asXP) {
                    this.setWizardryXP(value);
                } else {
                    this.setWizardryLevel((int) value);
                }
                break;
            case TINKERING:
                if (asXP) {
                    this.setTinkeringXP(value);
                } else {
                    this.setTinkeringLevel((int) value);
                }
                break;
            case HUNTING:
                if (asXP) {
                    this.setHuntingXP(value);
                } else {
                    this.setHuntingLevel((int) value);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown skill: " + skill);
        }
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

    public PlayerStatModel getCorrespondingStatModel() {
        return correspondingStatModel;
    }

    public void setCorrespondingStatModel(PlayerStatModel correspondingStatModel) {
        this.correspondingStatModel = correspondingStatModel;
    }
}
