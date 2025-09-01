package de.kuscheltiermafia.kingdoms.overlays;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.stats.PlayerStatModel;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthBar {
    public static void startHealthBarUpdater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if(player.getGameMode().equals(GameMode.ADVENTURE) || player.getGameMode().equals(GameMode.SURVIVAL)) {
                        PlayerStatModel stats = Kingdoms.playerStatModelIdentifier.get(player);
                        if (stats != null) {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "" + stats.getActualHealth() + " / " + stats.getHealth() + "❤"));
                        }
                    }
                }
            }
        }.runTaskTimer(Kingdoms.getPlugin(), 0L, 2);
    }
}
