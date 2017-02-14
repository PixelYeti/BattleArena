package com.pixelyeti.BattleArena.Listeners;

import com.pixelyeti.BattleArena.GameMechs.GameManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (GameManager.getPlayersGame(e.getEntity().getUniqueId()) == null)
            return;
        List<ItemStack> newDrops = new ArrayList<ItemStack>();
        for (ItemStack i : e.getDrops()) {
            if (i.getType() == Material.GOLD_NUGGET)
                newDrops.add(i);
        }
        e.getDrops().clear();
        e.getDrops().addAll(newDrops);
    }
}
