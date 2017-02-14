package com.pixelyeti.Arena.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SnowballHit implements Listener {

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent e) {
        if (e.getEntityType() == EntityType.SNOWBALL) {
            e.getHitBlock().getWorld().createExplosion(e.getHitBlock().getLocation(), 4, false);
        }
    }

}
