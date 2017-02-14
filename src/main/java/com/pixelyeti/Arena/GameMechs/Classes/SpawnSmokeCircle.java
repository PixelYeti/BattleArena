package com.pixelyeti.Arena.GameMechs.Classes;

import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnSmokeCircle extends BukkitRunnable {

    private Location playerLoc;
    private int radius;
    private int i = 0;

    public SpawnSmokeCircle(Location playerLoc, int radius) {
        this.playerLoc = playerLoc;
        this.radius = radius;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 360; i+=2) {
            double x = radius*Math.cos(Math.toRadians(i));
            double z = radius*Math.sin(Math.toRadians(i));
            PacketPlayOutWorldParticles packet;
            for (float y = 0; y <= 2.4;y+=0.8) {
                packet = new PacketPlayOutWorldParticles(EnumParticle.SMOKE_LARGE,
                        true, (float) (playerLoc.getX() + x), (float) playerLoc.getY() + y, (float) (playerLoc.getZ() + z),
                        0, 0, 0, 0, 1);
                for (Player online : Bukkit.getOnlinePlayers())
                    ((CraftPlayer) online).getHandle().playerConnection.sendPacket(packet);
            }
        }
        if (i == 10)
            cancel();
        i++;
    }
}
