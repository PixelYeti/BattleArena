package com.pixelyeti.BattleArena.Listeners;

import com.pixelyeti.BattleArena.GameMechs.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Callum on 29/10/2016.
 */
public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        GameManager.removeFromGame(e.getPlayer().getUniqueId());
    }

}
