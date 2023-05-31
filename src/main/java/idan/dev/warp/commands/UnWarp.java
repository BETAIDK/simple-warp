package idan.dev.warp.commands;

import idan.dev.warp.WarpPluginLoader;
import idan.dev.warp.managers.WarpManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static idan.dev.warp.utils.TextUtils.*;
import static org.bukkit.Bukkit.getLogger;

public class UnWarp implements CommandExecutor, TabCompleter {

    private final WarpPluginLoader plugin;

    public UnWarp(WarpPluginLoader plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            getLogger().severe(MUST_BE_PLAYER_ERROR);
            return true;
        }

        if (!player.hasPermission("plugin.admin")) {
            sender.sendMessage(NO_PERMISSION_ERROR);
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage("§c§lUsage: /unwarp <name>");
            return false;
        }

        WarpManager manager = plugin.getWarpManager();
        String warpName = args[0];

        if (manager.getWarp(warpName).isEmpty()) return false;
        manager.removeWarp(warpName);
        player.sendMessage(Component.text(PREFIX + "§awarp: " + warpName + " was successful removed!"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) return null;
        List<String> completionName = new ArrayList<>();

        plugin.getWarpManager().getWarpsName().forEach(name -> {
            if (name.toLowerCase().startsWith(args[0].toLowerCase())) completionName.add(name);
        });

        return completionName;
    }
}
