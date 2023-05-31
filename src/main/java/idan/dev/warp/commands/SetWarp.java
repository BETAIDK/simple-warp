package idan.dev.warp.commands;

import idan.dev.warp.WarpPluginLoader;
import idan.dev.warp.managers.WarpManager;
import idan.dev.warp.utils.OfflineLocation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static idan.dev.warp.utils.TextUtils.*;
import static org.bukkit.Bukkit.getLogger;

public class SetWarp implements CommandExecutor {

    private final WarpPluginLoader plugin;

    public SetWarp(WarpPluginLoader plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            getLogger().severe(MUST_BE_PLAYER_ERROR);
            return true;
        }

        if (!player.hasPermission("plugin.admin")) {
            sender.sendMessage(NO_PERMISSION_ERROR);
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage("§c§lUsage: /setwarp <name>");
            return false;
        }

        WarpManager manager = plugin.getWarpManager();
        String warpName = args[0];

        manager.addWarp(warpName, OfflineLocation.from(player.getLocation()));
        player.sendMessage(PREFIX + "§aWarp has been set by the name:" + warpName);
        return true;
    }
}
