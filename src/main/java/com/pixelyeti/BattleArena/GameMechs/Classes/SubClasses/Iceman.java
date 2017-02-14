package com.pixelyeti.BattleArena.GameMechs.Classes.SubClasses;

import com.pixelyeti.BattleArena.GameMechs.Classes.BaseClasses.ManaClass;
import com.pixelyeti.BattleArena.Util.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
       Main: Bow that causes slowness for 1s after hitting.
             Decent damage.
  Abilities: Frosty armor drains mana quickly but in combat is useful as it gives resistance 2
             Ice wall creates a wall of ice infront of the player and removes
             all negative potion effects. Used to escape enemies.
 */

public class Iceman extends ManaClass {

    public Iceman(UUID uuid) {
        super(uuid);
    }

    public void giveStartingItems() {
        List<ItemStack> items = new ArrayList<>();
        items.add(ItemStackBuilder.createCustomItemStack(Material.BOW, "Slow bow", ChatColor.AQUA, 1, true,
                "This bow will", "give any player", "who gets hit ", "slowness for a", "second"));
        items.get(0).addEnchantment(Enchantment.ARROW_INFINITE, 1);
        items.add(ItemStackBuilder.createLeatherArmor(Material.LEATHER_CHESTPLATE, "Frosty Armor", ChatColor.WHITE, 1,
                255,255,255, "Right click to equip!", "This armor will", "give you resistance", "2 but at a large",
                "mana cost!", ChatColor.AQUA + "Mana Cost: 50/s"));
        items.add(ItemStackBuilder.createCustomItemStack(Material.PACKED_ICE, "Ice wall", ChatColor.DARK_AQUA, 1, true,
                "Right click to", "activate the ice", "wall. This will", "also remove negative", "potion effects!",
                ChatColor.AQUA + "Mana Cost: 500"));
        player.getInventory().clear();
        for (ItemStack i : items)
            player.getInventory().addItem(i);
    }


}
