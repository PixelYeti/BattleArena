package com.pixelyeti.BattleArena.Listeners;

import com.pixelyeti.BattleArena.GameMechs.Classes.BaseClasses.ClassType;
import com.pixelyeti.BattleArena.GameMechs.Classes.ClassManager;
import com.pixelyeti.BattleArena.GameMechs.Classes.IceWallRemove;
import com.pixelyeti.BattleArena.GameMechs.Classes.SubClasses.Iceman;
import com.pixelyeti.BattleArena.Main;
import com.pixelyeti.BattleArena.Util.ServerUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class BlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.getBlockPlaced().getType() == Material.PACKED_ICE) {
            if (ClassManager.getPlayer(e.getPlayer().getUniqueId()).getClassType() == ClassType.ICEMAN) {
                Iceman i = (Iceman) ClassManager.getPlayer(e.getPlayer().getUniqueId());
                if (i.useMana(500)) {
                    ArrayList<Location> locations = new ArrayList<>();
                    BlockFace b = yawToFace(e.getPlayer().getLocation().getYaw());
                    for (int width = -1; width <= 1; width++) {
                        for (int y = 0; y <= 2; y++) {
                            Location l = e.getBlockPlaced().getLocation().add((b == BlockFace.NORTH || b == BlockFace.SOUTH) ? width : 0,
                                    y, (b == BlockFace.NORTH || b == BlockFace.SOUTH) ? 0 : width);
                            if (l.getBlock().getType() == Material.AIR || l.getBlock().getType() == Material.PACKED_ICE) {
                                locations.add(l);
                                locations.get(locations.size() - 1).getBlock().setType(Material.PACKED_ICE);
                            }
                        }
                    }
                    new IceWallRemove(locations, 20, 10).runTaskTimer(Main.getInstance(), 0L, 20L);
                    ServerUtils.removeAllNegativePotions(e.getPlayer());
                } else {
                    e.setCancelled(true);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    public BlockFace yawToFace(float yaw) {
        return axis[Math.round(yaw / 90f) & 0x3].getOppositeFace();
    }

    private static final BlockFace[] axis = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
}
