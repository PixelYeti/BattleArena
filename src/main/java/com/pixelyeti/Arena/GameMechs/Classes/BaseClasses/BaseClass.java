package com.pixelyeti.Arena.GameMechs.Classes.BaseClasses;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BaseClass {

    protected Player player;
    protected ClassType classType;

    public BaseClass(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
    }

    public void initiate() {
        player.setHealthScale(20);
        player.setLevel(3);
        player.setExp(0);
        player.setDisplayName(ChatColor.GREEN + player.getName());
    }

    public Player getPlayer() {
        return this.player;
    }

    public ClassType getClassType() {
        return this.classType;
    }

    public void removeLife() {
        if (player.getLevel() == 0) {
            player.sendMessage(ChatColor.RED + "You died!!!!");
        }
        player.setLevel(player.getLevel() - 1);
    }

    public void addLife() {
        this.player.setLevel(player.getLevel() + 1);
    }



}
