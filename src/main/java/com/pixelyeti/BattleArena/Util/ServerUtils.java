package com.pixelyeti.BattleArena.Util;

import com.pixelyeti.BattleArena.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * Created by Callum on 09/02/2017.
 */
public class ServerUtils {

    public static void giveStartingItems(Player p) {
        p.getInventory().setItem(0, ItemStackBuilder.createCustomItemStack(Material.NETHER_STAR, "Game Selector",
                ChatColor.AQUA, 1));
        p.getInventory().setItem(8, ItemStackBuilder.createCustomItemStack(Material.WATCH, "Hide players",
                ChatColor.GREEN, 1));
        p.getInventory().setItem(4, ItemStackBuilder.createCustomItemStack(Material.BARRIER, "Return to Hub",
                ChatColor.RED, 1));
    }

    public static Location getServerSpawn() {
        ConfigurationSection serverSection = Main.getInstance().getConfig().getConfigurationSection("Server");
        float yaw = (float) serverSection.getDouble("Spawn.Yaw");
        Location l = new Location(Bukkit.getWorld(serverSection.getString("Spawn.World")), serverSection.getDouble("Spawn.X"),
                serverSection.getDouble("Spawn.Y"), serverSection.getDouble("Spawn.Z"),
                yaw, (float) serverSection.getDouble("Spawn.Pitch"));

        return l;
    }

    public enum NegativeEffects{
        CONFUSION, HARM, HUNGER,POISON, SLOW_DIGGING, SLOW, WEAKNESS, WITHER
    }

    public static void removeAllNegativePotions(Player player){
        for(PotionEffect effects: player.getActivePotionEffects()){
            for(NegativeEffects bad: NegativeEffects.values()){
                if(effects.getType().getName().equalsIgnoreCase(bad.name())){
                    player.removePotionEffect(effects.getType());
                }
            }
        }
    }

}
