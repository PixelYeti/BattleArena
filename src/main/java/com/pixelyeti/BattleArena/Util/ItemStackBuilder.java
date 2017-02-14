package com.pixelyeti.BattleArena.Util;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Callum on 09/06/2015.
 */
public class ItemStackBuilder {

    public static ItemStack createCustomItemStack(Material m, String name, ChatColor colour, int size, int data, boolean unbreakable, String... lore) {
        ItemStack i = new ItemStack(m, size, (short) 0, (byte) data);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(colour + name);
        List<String> text = new ArrayList<String>();
        for (String s : lore) {
            if (s.equalsIgnoreCase("~~"))
                continue;
            text.add(ChatColor.DARK_PURPLE.toString() + ChatColor.ITALIC + s);
        }
        im.setLore(text);
        if (unbreakable)
            im.setUnbreakable(true);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createCustomItemStack(Material m, String name, ChatColor colour, int size, int data, String... lore) {
        return createCustomItemStack(m, name, colour, size, data, false, lore);
    }

    public static ItemStack createCustomItemStack(Material m, String name, ChatColor colour, int size, boolean unbreakable, String... lore) {
        return createCustomItemStack(m, name, colour, size, 0, unbreakable, lore);
    }

    public static ItemStack createCustomItemStack(Material m, String name, ChatColor colour, int size, String... lore) {
        return createCustomItemStack(m, name, colour, size, 0, lore);
    }

    public static ItemStack createLeatherArmor(Material m, String name, ChatColor colour, int size, int red, int green, int blue
            , String... lore) {
        ItemStack i = new ItemStack(m, size);
        LeatherArmorMeta lam = (LeatherArmorMeta) i.getItemMeta();
        lam.setColor(Color.fromRGB(red, green, blue));
        lam.setDisplayName(colour + name);
        List<String> text = new ArrayList<String>();
        for (String s : lore) {
            if (s.equalsIgnoreCase("~~"))
                continue;
            text.add(ChatColor.DARK_PURPLE.toString() + ChatColor.ITALIC + s);
        }
        lam.setLore(text);
        i.setItemMeta(lam);
        return i;
    }

    public static ItemStack createShopItem(Material m, String name, ChatColor colour, String price, String description) {
        ItemStack i = createCustomItemStack(m, name, colour, 1, 0);
        ItemMeta im = i.getItemMeta();
        List<String> is = new ArrayList<String>();
        is.add(ChatColor.DARK_GRAY + description);
        is.add(ChatColor.LIGHT_PURPLE + price + " xp levels!");
        im.setLore(is);
        return i;
    }

}
