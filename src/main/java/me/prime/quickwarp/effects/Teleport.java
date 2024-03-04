package me.prime.quickwarp.effects;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Teleport {

    public static void playEffect(Player player, Location location) {
        Location start = player.getLocation().add(0, 1, 0);
        Location end = location.add(0, 1, 0);
        Vector direction = end.toVector().subtract(start.toVector()).normalize();
        double distance = start.distance(end);
        int steps = (int) Math.ceil(distance / 0.5);

        for (int i = 0; i < steps; i++) {
            Location current = start.clone().add(direction.clone().multiply(i * 0.5));
            player.getWorld().spawnParticle(Particle.FLAME, current, 1, 0, 0, 0, 0);
        }
    }
}