package me.prime.quickwarp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QuickWarp extends JavaPlugin {

    private Map<String, Location> warpLocations;
    private String errorMSG;

    @Override
    public void onEnable() {
        loadConfig();
        getCommand("qw").setExecutor(this);
    }

    public QuickWarp() {
        warpLocations = new HashMap<>();
        errorMSG = "You are already in that dimension!";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            sendHelpMessage(player);
            return true;
        }

        String dimension = args[0].toLowerCase();

        if (!warpLocations.containsKey(dimension)) {
            player.sendMessage(ChatColor.RED + "Invalid dimension. Use overworld, nether, or end.");
            return true;
        }

        Location warpLocation = warpLocations.get(dimension);

        World.Environment environment = player.getWorld().getEnvironment();
        if (environment.equals(warpLocation.getWorld().getEnvironment())) {
            player.sendMessage(ChatColor.RED + errorMSG);
            return true;
        }

        player.teleport(warpLocation);
        player.sendMessage(ChatColor.GREEN + "Teleported to " + dimension + ".");
        return true;
    }

    public void sendHelpMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "QuickWarp Help:");
        sender.sendMessage(ChatColor.GREEN + "/qw <dimension> - Teleport to the specified dimension.");
        sender.sendMessage(ChatColor.GREEN + "/qw help - Display this help message.");
    }

    public void saveConfig() {
        File configFile = new File(getDataFolder(), "config.txt");

        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
            }

            FileWriter writer = new FileWriter(configFile);

            for (Map.Entry<String, Location> entry : warpLocations.entrySet()) {
                String dimension = entry.getKey();
                Location location = entry.getValue();

                World world = location.getWorld();
                double x = location.getX();
                double y = location.getY();
                double z = location.getZ();

                writer.write(dimension + "=" + world.getName() + "," + x + "," + y + "," + z + "\n");
            }

            writer.close();

        } catch (IOException e) {
            getLogger().severe("Error while saving config.txt: " + e.getMessage());
        }
    }

    public void loadConfig() {
        warpLocations.clear();
        File configFile = new File(getDataFolder(), "config.txt");

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                getLogger().severe("Error while creating config.txt: " + e.getMessage());
                return;
            }
        }

        try {
            Scanner scanner = new Scanner(configFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("=");
                if (parts.length < 2) continue;

                String dimension = parts[0].trim();
                String[] coords = parts[1].split(",");

                if (coords.length < 4) continue;

                String worldName = coords[0].trim();
                double x = Double.parseDouble(coords[1].trim());
                double y = Double.parseDouble(coords[2].trim());
                double z = Double.parseDouble(coords[3].trim());

                World world = Bukkit.getWorld(worldName);
                if (world == null) {
                    getLogger().warning("Unknown world: " + worldName);
                    continue;
                }

                Location location = new Location(world, x, y, z);
                warpLocations.put(dimension, location);
            }

            scanner.close();
        } catch (Exception e) {
            getLogger().severe("Error while loading config.txt: " + e.getMessage());
        }
    }
}