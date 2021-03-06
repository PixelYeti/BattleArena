package com.pixelyeti.BattleArena.GameMechs.Classes;

import com.pixelyeti.BattleArena.GameMechs.Classes.BaseClasses.ManaClass;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaGen extends BukkitRunnable {

    private ManaClass player;

    public ManaGen(ManaClass player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (!player.addMana()) {
            player.isGenning = false;
            cancel();
        }
    }
}
