package com.pixelyeti.Arena.GameMechs.Classes.BaseClasses;

import com.pixelyeti.Arena.GameMechs.Classes.ManaDrain;
import com.pixelyeti.Arena.GameMechs.Classes.ManaGen;
import com.pixelyeti.Arena.Main;
import org.bukkit.ChatColor;

import java.util.UUID;

public class ManaClass extends BaseClass {

    public boolean isGenning = false;

    public ManaClass(UUID uuid) {
        super(uuid);
        this.classType = ClassType.MANA;
    }

    @Override
    public void initiate() {
        super.initiate();
        player.setHealthScale(16);
        player.setDisplayName(ChatColor.AQUA + player.getName());
        genMana();
    }

    public boolean addMana() {
        if (player.getExp() >= 1)
            return false;
        player.setExp((float) (player.getExp() + 0.001));
        return true;
    }

    public boolean useMana(int manaCost) {
        if (player.getExp() * 1000 >= manaCost) {
            player.setExp(player.getExp() - (float)(manaCost)/1000);
            if (!isGenning)
                genMana();
            return true;
        }
        return false;
    }

    public void genMana() {
        isGenning = true;
        new ManaGen(this).runTaskTimer(Main.getInstance(), 0L, 1L);
    }

    public void drainMana(int rate, long timePeriod) {
        new ManaDrain(this, rate).runTaskTimer(Main.getInstance(), 0L, timePeriod);
    }

}
