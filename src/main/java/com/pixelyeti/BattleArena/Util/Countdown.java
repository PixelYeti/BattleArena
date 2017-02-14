package com.pixelyeti.BattleArena.Util;

import com.pixelyeti.BattleArena.GameMechs.Game;
import com.pixelyeti.BattleArena.GameMechs.Map;
import com.pixelyeti.BattleArena.GameMechs.MapManager;
import com.pixelyeti.BattleArena.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Callum on 30/10/2016.
 */
public class Countdown extends BukkitRunnable {

    private Game g;
    private int i;
    private int tpTime;
    private int startTime;
    private ArrayList<Integer> countingNums;

    private Map m;

    public Countdown(Game g, int start, int tpTime, int... cNums) {
        this.g = g;
        this.i = start;
        this.startTime = start;
        this.tpTime = tpTime;
        this.countingNums = new ArrayList<Integer>();

        for (int c : cNums) {
            countingNums.add(c);
        }
    }

    public void run() {
        for (UUID id : g.players) {
            Player p = Bukkit.getPlayer(id);
            p.setLevel(i);
        }

        if (i == startTime) {
            if (!(Main.getInstance().getConfig().getBoolean("Game.ChooseMapBefore"))) {
                m = MapManager.selectMap();
                g.map = m;
            } else {
                m = g.map;
            }
            FileHandler.copyWorld(Bukkit.getWorld(g.map.getWorldFileName()), g.map.getWorldFileName() + g.gameName);
            for (UUID id : g.players) {
                Player p = Bukkit.getPlayer(id);
                p.sendMessage(StringUtilities.prefix + ChatColor.AQUA + "The chosen map is: " + ChatColor.GOLD +
                        m.getName());
            }
        } else if (i == tpTime) {
            Map m = g.map;
            m.teleportToSpawns(m.getName(), g);
            for (UUID id : g.players) {
                //StartingItems.get(id);
                Bukkit.getPlayer(id).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 10));
            }
        } else if (i == 0) {
            for (UUID id : g.players) {
                Player p = Bukkit.getPlayer(id);
                p.sendTitle(ChatColor.GOLD + "Go!", ChatColor.AQUA + "THe person with the most kills wins!", 5, 10, 5);
            }
            cancel();
            return;
        }

        if (countingNums.contains(i)) {
            for (UUID id : g.players) {
                Player p = Bukkit.getPlayer(id);
                p.sendMessage(StringUtilities.prefix + ChatColor.GREEN + "Time remaining " + i);
                if (i > 3) {
                    p.sendTitle(ChatColor.GREEN + "" + i, null, 5, 10, 5);
                } else {
                    p.sendTitle(ChatColor.RED + "" + i, null, 5, 10, 5);
                }
            }
        }
        i--;
    }
}