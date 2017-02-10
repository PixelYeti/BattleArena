package com.pixelyeti.Arena.Listeners;

import com.pixelyeti.Arena.GameMechs.GameGUI;
import com.pixelyeti.Arena.GameMechs.GameManager;
import com.pixelyeti.Arena.GameMechs.GameState;
import com.pixelyeti.Arena.Util.StringUtilities;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.Team;

/**
 * Created by Callum on 11/06/2015.
 */
public class InvClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        if (e.getInventory().getName().equalsIgnoreCase(GameGUI.gameGUI.getName())) {
            System.out.println(e.getCurrentItem());
            if (e.getCurrentItem().getType() == Material.STAINED_CLAY) {
                e.setCancelled(true);
                String game = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).substring(5);
                String pGame = GameManager.getPlayersGame(e.getWhoClicked().getUniqueId());
                if (pGame != null) {
                    e.getWhoClicked().sendMessage(StringUtilities.errorMessage + " Already in a game!");
                    return;
                }
                if (GameManager.getGame(game).gameState == GameState.WAITING) {
                    GameManager.addToGame(game, e.getWhoClicked().getUniqueId());
                } else {
                    e.getWhoClicked().sendMessage(StringUtilities.prefix + ChatColor.RED + " You cannot join that game!");
                }
            }
        } else {
            return;
        }
        e.setCancelled(true);
    }
}
