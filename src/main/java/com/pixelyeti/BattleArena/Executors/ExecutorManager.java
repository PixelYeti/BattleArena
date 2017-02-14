package com.pixelyeti.BattleArena.Executors;

import com.pixelyeti.BattleArena.Main;

/**
 * Created by Callum on 09/01/2016.
 */
public class ExecutorManager {

    public static void registerExecutors(Main m) {
        //setSpawn = new SetSpawn(Main.instance);
        //Main.instance.getCommand("setspawn").setExecutor(setSpawn);
        m.getCommand("setclass").setExecutor(new SetClass());
    }
}
