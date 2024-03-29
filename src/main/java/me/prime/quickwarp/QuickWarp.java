package me.prime.quickwarp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class QuickWarp extends JavaPlugin {

    private FileConfiguration config;
    private Map<String, Location> warpLocations;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        warpLocations = new HashMap<>();
        warpLocations.put("overworld", new Location(Bukkit.getWorld("world"), 0, 70, 0));
        warpLocations.put("nether", new Location(Bukkit.getWorld("world_nether"), 0, 70, 0));
        warpLocations.put("end", new Location(Bukkit.getWorld("world_the_end"), 0, 70, 0));
        getCommand("qw").setExecutor(this);
    }

    private Map<Player, Long> lastCommandTime = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }
    
        Player player = (Player) sender;
    
        if (!player.hasPermission("quickwarp.use")) {
            if (config.getBoolean("permissions.quickwarp.use")) {
                if (!player.isOp()) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                    return true;
                }
            } else {
                return true;
            }
        }
    
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /qw <overworld|nether|end>");
            return true;
        }
    
        String dimension = args[0].toLowerCase();
    
        if (!warpLocations.containsKey(dimension)) {
            player.sendMessage(ChatColor.RED + "Invalid dimension. Use overworld, nether, or end.");
            return true;
        }
    
        Location warpLocation = warpLocations.get(dimension);
    
        if (player.getWorld().equals(warpLocation.getWorld())) {
            player.sendMessage(ChatColor.RED + "You cannot teleport to the same dimension!");
            return true;
        }
    
        if (System.currentTimeMillis() - lastCommandTime.getOrDefault(player, 0L) < 3000) {
            player.sendMessage(ChatColor.RED + "Please wait 3 seconds before using the quickwarp command again.");
            return true;
        }
    
        // Spawn a spiralling fire particle effect at the player's location
        Location playerLocation = player.getLocation();
        for (int i = 0; i < 10; i++) {
            playerLocation.getWorld().spawnParticle(Particle.FLAME, playerLocation, 1, 0, 0, 0, 0.1);
            playerLocation.add(0, 0.5, 0);
        }
    
        // Set the player's invulnerability to true for 5 seconds
        player.setInvulnerable(true);
        Bukkit.getScheduler().runTaskLater(this, () -> player.setInvulnerable(false), 100);
    
        lastCommandTime.put(player, System.currentTimeMillis());
    
        player.teleport(warpLocation);
        player.sendMessage(ChatColor.GREEN + "Teleported to " + dimension + ".");
        return true;
    }

    public void sendHelpMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "QuickWarp Help:");
        sender.sendMessage(ChatColor.GREEN + "/qw <dimension> - Teleport to the specified dimension.");
        sender.sendMessage(ChatColor.GREEN + "/qw help - Display this help message.");
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}