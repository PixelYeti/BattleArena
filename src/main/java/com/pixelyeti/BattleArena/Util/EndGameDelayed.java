package com.pixelyeti.BattleArena.Util;

import com.pixelyeti.BattleArena.GameMechs.Game;
import com.pixelyeti.BattleArena.GameMechs.GameManager;
import com.pixelyeti.BattleArena.GameMechs.GameState;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class EndGameDelayed extends BukkitRunnable {

    private Game g;

    public EndGameDelayed(Game g) {
        this.g = g;
    }

    @Override
    public void run() {
        ArrayList<UUID> players = (ArrayList<UUID>) g.getPlayers().clone();
        for (UUID id : players) {
            Player p = Bukkit.getPlayer(id);
            if (ServerUtils.getServerSpawn() != null)
                p.teleport(ServerUtils.getServerSpawn());
            else
                p.teleport(p.getWorld().getSpawnLocation());
            p.getInventory().clear();
            ServerUtils.giveStartingItems(p);
            GameManager.removeFromGame(id);
        }

        g.gameState = GameState.RESTARTING;

        World gameWorld = Bukkit.getWorld(g.map.getWorldFileName() + g.gameName);
        File worldFolder = gameWorld.getWorldFolder();

        if (Bukkit.unloadWorld(gameWorld, false)) {
            FileHandler.deleteWorldFolder(worldFolder);
        }

        g.gameState = GameState.WAITING;
        g.map = null;
    }
}
