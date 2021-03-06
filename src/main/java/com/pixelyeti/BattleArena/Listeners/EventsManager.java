package com.pixelyeti.BattleArena.Listeners;

import com.pixelyeti.BattleArena.Main;
import org.bukkit.plugin.PluginManager;

/**
 * Created by Callum on 22/06/2015.
 */
public class EventsManager {

    public static void registerEvents(PluginManager pm) {
        pm.registerEvents(new InvClick(), Main.getInstance());
        pm.registerEvents(new Join(Main.getInstance()), Main.getInstance());
        pm.registerEvents(new PlayerInteract(), Main.getInstance());
        pm.registerEvents(new PlayerLeave(), Main.getInstance());
        pm.registerEvents(new PlayerDeath(), Main.getInstance());
        pm.registerEvents(new ChatEvent(), Main.getInstance());
        pm.registerEvents(new BlockPlace(), Main.getInstance());
        pm.registerEvents(new SnowballHit(), Main.getInstance());
        pm.registerEvents(new ExplodeEvent(), Main.getInstance());

    }
}
