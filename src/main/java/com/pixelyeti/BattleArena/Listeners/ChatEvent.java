package com.pixelyeti.BattleArena.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setFormat(e.getPlayer().getDisplayName() + ChatColor.RESET + ": " + e.getMessage());
    }
}
