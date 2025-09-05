package de.kuscheltiermafia.kingdoms.overlays;

import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.stats.PlayerStatModel;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_21_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBarHandler {

    public static void actionBarUpdate() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.getGameMode().equals(GameMode.ADVENTURE) || player.getGameMode().equals(GameMode.SURVIVAL)) {
                PlayerStatModel stats = Kingdoms.playerStatModelIdentifier.get(player);
                if (stats != null) {
                    String message = ChatColor.RED + "" + stats.getActiveHealth() + " / " +
                            stats.getStat(Stat.HEALTH) + "❤" + "  " +
                            ChatColor.BLUE + (int) Math.round(stats.getActiveMana()) + " / " +
                            (int) Math.round(stats.getMaxMana()) + "✎";

                    Component comp = Component.literal(message);
                    ClientboundSetActionBarTextPacket packet = new ClientboundSetActionBarTextPacket(comp);
                    ((CraftPlayer) player).getHandle().connection.send(packet);
                }
            }
        }
    }
}
