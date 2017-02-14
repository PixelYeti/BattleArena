package com.pixelyeti.BattleArena.Listeners;

import com.pixelyeti.BattleArena.GameMechs.Classes.BaseClasses.BaseClass;
import com.pixelyeti.BattleArena.GameMechs.Classes.ClassManager;
import com.pixelyeti.BattleArena.GameMechs.Classes.SpawnSmokeCircle;
import com.pixelyeti.BattleArena.GameMechs.Classes.SubClasses.Iceman;
import com.pixelyeti.BattleArena.GameMechs.Classes.SubClasses.Sorcerer;
import com.pixelyeti.BattleArena.GameMechs.GameGUI;
import com.pixelyeti.BattleArena.Main;
import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        BaseClass bClass = ClassManager.getPlayer(player.getUniqueId());
        if (player.getInventory().getItemInMainHand().getType() == Material.WATCH) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR
                    || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (player.getPlayerTime() == player.getWorld().getFullTime()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        player.hidePlayer(p);
                    }
                    player.setPlayerTime(player.getWorld().getFullTime() + 1, true);
                    player.sendMessage(ChatColor.GOLD
                            + "Players are now invisible");
                } else {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        player.showPlayer(p);
                    }
                    player.resetPlayerTime();
                    player.sendMessage(ChatColor.GOLD
                            + "Players are now visible");
                }
            }
        } else if (player.getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR
                    || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                GameGUI.openInventory(player.getUniqueId());
            }
        } else if (player.getInventory().getItemInMainHand().getType() == Material.STICK) {
            Sorcerer i = (Sorcerer) ClassManager.getPlayer(player.getUniqueId());
            i.giveStartingItems();
        } else if (player.getInventory().getItemInMainHand().getType() == Material.LEATHER_CHESTPLATE) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                    || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                        ChatColor.WHITE + "Frosty Armor")) {
                    event.setCancelled(true);
                    boolean allNull = true;
                    for (ItemStack i : player.getInventory().getArmorContents()) {
                        if (i != null) {
                            allNull = false;
                            break;
                        }
                    }
                    if (!allNull) {
                        player.getInventory().setArmorContents(null);
                        return;
                    }
                    player.sendMessage("Here");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2));
                    Iceman i = (Iceman) ClassManager.getPlayer(player.getUniqueId());
                    i.drainMana(5, 2);
                }
            }
        } else if (player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            if (bClass instanceof Sorcerer) {
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    Sorcerer s = (Sorcerer) ClassManager.getPlayer(player.getUniqueId());
                    if (s != null)
                        if (s.useMana(50))
                            player.launchProjectile(Snowball.class);
                }
            }

            //  (x – h)^2 + (y – k)^2 = r^2
        } else if (player.getInventory().getItemInMainHand().getType() == Material.SULPHUR) {
            if (bClass instanceof Sorcerer) {
                if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
                    Sorcerer s = (Sorcerer) ClassManager.getPlayer(player.getUniqueId());
                    if (s.useMana(150)) {
                        ItemStack stack = player.getInventory().getItemInMainHand();
                        stack.setAmount(stack.getAmount() - 1);
                        new SpawnSmokeCircle(player.getLocation(), 2).runTaskTimer(Main.getInstance(), 0L, 10L);
                    }
                }
            }
        } else if (player.getInventory().getItemInMainHand().getType() == Material.SADDLE) {
            if (bClass instanceof Sorcerer) {
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                }
            }
        }
    }

    public void createHelix(Player player) {
        Location loc = player.getLocation();
        int radius = 2;
        for(double y = 0; y <= 50; y+=0.07) {
            double x = radius * Math.cos(y);
            double z = radius * Math.sin(y);
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.SMOKE_LARGE,
                    true, (float) (loc.getX() + x), (float) (loc.getY() + y), (float) (loc.getZ() + z), 0,
                    0, 0, 0, 1);
            for(Player online : Bukkit.getOnlinePlayers()) {
                if (online.getUniqueId() != player.getUniqueId())
                    ((CraftPlayer)online).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }
}
