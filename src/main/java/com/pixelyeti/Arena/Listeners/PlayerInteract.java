package com.pixelyeti.Arena.Listeners;

import com.pixelyeti.Arena.GameMechs.Classes.BaseClasses.BaseClass;
import com.pixelyeti.Arena.GameMechs.Classes.BaseClasses.ManaClass;
import com.pixelyeti.Arena.GameMechs.Classes.ClassManager;
import com.pixelyeti.Arena.GameMechs.Classes.SubClasses.Iceman;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == Material.WATCH) {
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
        } else if (player.getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR
                    || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                GameGUI.openInventory(player.getUniqueId());
            }
        } else if (player.getInventory().getItemInMainHand().getType() == Material.STICK) {
            Iceman i = (Iceman) ClassManager.getPlayer(player.getUniqueId());
            i.giveStartingItems();
        } else if (player.getInventory().getItemInMainHand().getType() == Material.LEATHER_CHESTPLATE) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                    || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                        ChatColor.WHITE + "Frosty Armor")) {
                    event.setCancelled(true);
                    boolean allNull = true;
                    for (ItemStack i : player.getInventory().getArmorContents()) {
                        if (i != null) {
                            allNull = false;
                            break;
                        }
                    }
                    if (!allNull) {
                        player.getInventory().setArmorContents(null);
                        return;
                    }
                    player.sendMessage("Here");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2));
                    Iceman i = (Iceman) ClassManager.getPlayer(player.getUniqueId());
                    i.drainMana(5, 2);
                }
            }
        }
    }
}
