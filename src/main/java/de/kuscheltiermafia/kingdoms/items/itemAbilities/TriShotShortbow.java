package de.kuscheltiermafia.kingdoms.items.itemAbilities;

import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;

public class TriShotShortbow extends ItemAbility {

    public TriShotShortbow() {
        super("Shortbow", "tri_shortbow", new String[]{"Shoots three arrows instantly."}, 0, 0, AbilityType.GENERAL_CLICK, AbilityDisplayType.ITEM_TYPE);
    }

    @Override
    public void onGeneralClick(Player p, Event e) {
        PlayerInteractEvent event = (PlayerInteractEvent) e;
        event.setCancelled(true);

        Arrow arrow = p.launchProjectile(Arrow.class);
        Arrow arrowRight = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
        Arrow arrowLeft = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);

        arrow.setShooter(p);
        arrowRight.setShooter(p);
        arrowLeft.setShooter(p);

        arrowRight.setRotation(arrow.getLocation().getYaw() + 45, arrow.getLocation().getPitch());
        arrowRight.setVelocity(arrow.getVelocity());
        arrowLeft.setRotation(arrow.getLocation().getYaw() - 45, arrow.getLocation().getPitch());
        arrowLeft.setVelocity(arrow.getVelocity());

        p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
    }
}
