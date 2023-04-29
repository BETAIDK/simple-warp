package dev.tp.commands;

import dev.tp.WarpPluginLoader;
import dev.tp.system.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class UnWarp implements CommandExecutor, TabCompleter {

    private final WarpPluginLoader plugin;

    public UnWarp(WarpPluginLoader plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().severe("Only player allowed to use commands");
            return true;
        }

        if (!player.hasPermission("plugin.admin.unwarp")) {
            sender.sendMessage("§cYou don't have permission to use this command");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage("§cUsage: /unwarp <name>");
            return false;
        }

        WarpManager manager = plugin.getWarpManager();
        String warpName = args[0];

        if (manager.getWarp(warpName).isEmpty()) return false;
        manager.removeWarp(warpName);
        player.sendMessage("§awarp " + warpName + " was successful removed!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            WarpManager manager = plugin.getWarpManager();
            Set<String> warpNames = manager.getWarpsName();

            return warpNames.stream().toList();
        }
        return null;
    }
}
