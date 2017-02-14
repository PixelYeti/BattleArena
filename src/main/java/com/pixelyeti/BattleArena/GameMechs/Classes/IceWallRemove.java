package com.pixelyeti.BattleArena.GameMechs.Classes;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class IceWallRemove extends BukkitRunnable {

    private ArrayList<Location> locations = new ArrayList<>();
    private int i, changeToIce;

    public IceWallRemove(ArrayList<Location> locations, int start, int changeToIce) {
        this.locations = locations;
        this.i = start;
        this.changeToIce = changeToIce;
    }

    @Override
    public void run() {
        if (i == changeToIce) {
            for (Location l : locations) {
                l.getBlock().setType(Material.ICE);
            }
        } else if (i == 0) {
            for (Location l : locations) {
                l.getBlock().setType(Material.AIR);
            }
            cancel();
        }
        i--;
    }
}
