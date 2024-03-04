// WK owns u and all

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class QuickWarp extends JavaPlugin implements CommandExecutor {

    private FileConfiguration config;
    private Map<String, Location> warpLocations;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        warpLocations = new HashMap<>();
        warpLocations.put("overworld", new Location(Bukkit.getWorld("world"), 0, 70, 0));
        warpLocations.put("nether", new Location(Bukkit.getWorld("DIM-1"), 0, 70, 0));
        warpLocations.put("end", new Location(Bukkit.getWorld("DIM1"), 0, 70, 0));
        getCommand("qw").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

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

        if (player.getWorld().getEnvironment().equals(warpLocation.getWorld().getEnvironment())) {
            player.sendMessage(ChatColor.RED + config.getString("errorMSG"));
            return true;
        }

        player.teleport(warpLocation);
        player.sendMessage(ChatColor.GREEN + "Teleported to " + dimension + ".");
        return true;
    }
}
