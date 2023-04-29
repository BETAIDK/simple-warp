package idan.dev.warp.commands;

import idan.dev.warp.WarpPluginLoader;
import idan.dev.warp.system.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class Warp implements CommandExecutor, TabCompleter {

    private final WarpPluginLoader plugin;

    public Warp(WarpPluginLoader plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().severe("Only player allowed to use commands");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§cUsage: /warp <name>");
            return false;
        }

        WarpManager manager = plugin.getWarpManager();
        String warpName = args[0];

        player.teleport(manager.getWarp(warpName).get().toLocation());
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
