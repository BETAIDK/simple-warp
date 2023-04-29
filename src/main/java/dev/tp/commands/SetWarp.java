package dev.tp.commands;

import dev.tp.WarpPluginLoader;
import dev.tp.system.WarpManager;
import dev.tp.utils.OfflineLocation;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {

    private final WarpPluginLoader plugin;

    public SetWarp(WarpPluginLoader plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().severe("Only player allowed to use commands");
            return true;
        }

        if (!player.hasPermission("plugin.admin.setwarp")) {
            sender.sendMessage("§cYou don't have permission to use this command");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage("§cUsage: /setwarp <name>");
            return false;
        }

        WarpManager manager = plugin.getWarpManager();
        String warpName = args[0];

        manager.addWarp(warpName, OfflineLocation.from(player.getLocation()));
        player.sendMessage("§aWarp has been set by the name:" + warpName);
        return true;
    }
}
