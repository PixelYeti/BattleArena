package com.pixelyeti.Arena.Listeners;

import com.pixelyeti.Arena.Main;
import com.pixelyeti.Arena.Util.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Callum on 10/06/2015.
 */
public class Join implements Listener {

    private static Main plugin;

    public Join(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();
        ServerUtils.giveStartingItems(p);
        p.teleport(ServerUtils.getServerSpawn());

        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (pl.getPlayerTime() == pl.getWorld().getFullTime() + 1) {
                pl.hidePlayer(p);
            }
        }
    }
}
