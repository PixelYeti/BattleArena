package com.pixelyeti.BattleArena.Executors;

import com.pixelyeti.BattleArena.GameMechs.Classes.BaseClasses.*;
import com.pixelyeti.BattleArena.GameMechs.Classes.ClassManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length > 0) {
            ClassManager.addPlayer(((Player)commandSender).getUniqueId(), ClassType.MANA);
            commandSender.sendMessage("Hello");
            return false;
        }
        new BaseClass(((Player)commandSender).getUniqueId()).initiate();
        return false;
    }
}
