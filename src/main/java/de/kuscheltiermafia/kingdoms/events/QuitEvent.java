package de.kuscheltiermafia.kingdoms.events;

import de.kuscheltiermafia.kingdoms.data.PlayerData;
import de.kuscheltiermafia.kingdoms.data.PlayerUtility;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        PlayerData image = PlayerUtility.getPlayerImage(player);
        File file = new File(PlayerUtility.getFolderPath(player) + "stats.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("Combat.XP", image.getCombatXP());
        config.set("Combat.Level", image.getCombatLevel());
        config.set("Mining.XP", image.getMiningXP());
        config.set("Mining.Level", image.getMiningLevel());
        config.set("Foraging.XP", image.getForagingXP());
        config.set("Foraging.Level", image.getForagingLevel());
        config.set("Enchanting.XP", image.getEnchantingXP());
        config.set("Enchanting.Level", image.getEnchantingLevel());
        config.set("Farming.XP", image.getFarmingXP());
        config.set("Farming.Level", image.getFarmingLevel());
        config.set("Fishing.XP", image.getFishingXP());
        config.set("Fishing.Level", image.getFishingLevel());
        config.set("Building.XP", image.getBuildingXP());
        config.set("Building.Level", image.getBuildingLevel());
        config.set("Alchemy.XP", image.getAlchemyXP());
        config.set("Alchemy.Level", image.getAlchemyLevel());
        config.set("Wizardry.XP", image.getWizardryXP());
        config.set("Wizardry.Level", image.getWizardryLevel());
        config.set("Tinkering.XP", image.getTinkeringXP());
        config.set("Tinkering.Level", image.getTinkeringLevel());
        config.set("Hunting.XP", image.getHuntingXP());
        config.set("Hunting.Level", image.getHuntingLevel());

        try {
            config.save(file);
        } catch (Exception exception) {
            Bukkit.getLogger().warning("Could not save player stats for " + player.getName());
        }
        PlayerUtility.setPlayerImage(player,null);
    }

}
