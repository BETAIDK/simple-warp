package dev.tp.commands;

import dev.tp.TpPluginLoader;
import dev.tp.sys.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {

    private final TpPluginLoader plugin;

    public Warp(TpPluginLoader plugin) {
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

        player.teleport(manager.getWarp(warpName).get().toLocation());
        return true;
    }
}
