package dev.tp.commands;

import dev.tp.TpPluginLoader;
import dev.tp.system.WarpManager;
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

        if (args.length != 1) {
            sender.sendMessage("§cUsage: /warp <name>");
            return false;
        }

        WarpManager manager = plugin.getWarpManager();
        String warpName = args[0];

        player.teleport(manager.getWarp(warpName).get().toLocation());
        return true;
    }
}
