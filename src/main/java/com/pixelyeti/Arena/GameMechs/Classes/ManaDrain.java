package com.pixelyeti.Arena.GameMechs.Classes;

import com.pixelyeti.Arena.GameMechs.Classes.BaseClasses.ManaClass;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaDrain extends BukkitRunnable {

    ManaClass player;
    int rate;

    public ManaDrain(ManaClass player, int rate) {
        this.player = player;
        this.rate = rate;
    }

    @Override
    public void run() {
        if (!player.useMana(rate)) {
            player.getPlayer().sendMessage("You ran out of mana");
            player.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            cancel();
        }
    }
}
