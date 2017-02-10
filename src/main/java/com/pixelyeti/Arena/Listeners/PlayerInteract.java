package com.pixelyeti.Arena.Listeners;

import com.pixelyeti.Arena.GameMechs.GameGUI;
import com.pixelyeti.Arena.GameMechs.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;

public class PlayerInteract implements Listener {

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getPlayer().getItemInHand().getType() == Material.WATCH) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR
                    || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (player.getPlayerTime() == player.getWorld().getFullTime()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        player.hidePlayer(p);
                    }
                    player.setPlayerTime(player.getWorld().getFullTime() + 1, true);
                    player.sendMessage(ChatColor.GOLD
                            + "Players are now invisible");
                } else {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        player.showPlayer(p);
                    }
                    player.resetPlayerTime();
                    player.sendMessage(ChatColor.GOLD
                            + "Players are now visible");
                }
            }
        } else if (player.getItemInHand().getType() == Material.NETHER_STAR) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR
                    || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                GameGUI.openInventory(player.getUniqueId());
            }
        }
    }
}
