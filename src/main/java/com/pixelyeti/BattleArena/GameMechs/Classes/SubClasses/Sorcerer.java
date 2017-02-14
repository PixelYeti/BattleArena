package com.pixelyeti.BattleArena.GameMechs.Classes.SubClasses;

import com.pixelyeti.BattleArena.GameMechs.Classes.BaseClasses.ManaClass;
import com.pixelyeti.BattleArena.Util.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
    Launches firework-like projectiles that explode upon impact causing splash damage.
    Can also create cloud of smoke.
    Has ability to ‘fly’ (ride invisible fast horse’ at a large drain of mana.
 */

public class Sorcerer extends ManaClass {
    public Sorcerer(UUID uuid) {
        super(uuid);
    }

    public void giveStartingItems() {
        List<ItemStack> items = new ArrayList<>();
        items.add(ItemStackBuilder.createCustomItemStack(Material.BLAZE_ROD, "Sorcerers Staff", ChatColor.GOLD, 1,
                "Right cick to fire", "projectiles at your", "opponents!", ChatColor.AQUA + "Mana Cost: 50"));
        items.add(ItemStackBuilder.createCustomItemStack(Material.SULPHUR, "Smoke Bomb", ChatColor.DARK_GRAY, 3,
                "Left click on the", "ground to produce a", "smoke bomb!", ChatColor.AQUA + "Mana Cost: 150"));
        items.add(ItemStackBuilder.createCustomItemStack(Material.SADDLE, "Mana Dash", ChatColor.LIGHT_PURPLE, 1,
                "Right click to call", "your trusty steed!", "Ride your phantom", "horse into battle!",
                ChatColor.AQUA + "Mana Cost: 100/s"));
        player.getInventory().clear();
        for (ItemStack i : items)
            player.getInventory().addItem(i);
    }
}
