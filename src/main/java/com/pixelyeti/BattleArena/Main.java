package com.pixelyeti.BattleArena;

import com.pixelyeti.BattleArena.Executors.ExecutorManager;
import com.pixelyeti.BattleArena.GameMechs.*;
import com.pixelyeti.BattleArena.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static ConfigurationSection mapSection;

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.saveConfig();
        this.reloadConfig();

        this.instance = this;

        mapSection = this.getConfig().getConfigurationSection("Maps");

        GameGUI.initiate();

        GameManager.createGames();
        saveConfig();

        ExecutorManager.registerExecutors(this);

        PluginManager pm = Bukkit.getServer().getPluginManager();
        EventsManager.registerEvents(pm);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> MapManager.initiateMaps(), 20L);
    }

    @Override
    public void onDisable() {
    }


}
