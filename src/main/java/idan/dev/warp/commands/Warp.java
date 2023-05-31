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
import java.util.Set;

import static idan.dev.warp.utils.TextUtils.MUST_BE_PLAYER_ERROR;
import static idan.dev.warp.utils.TextUtils.PREFIX;
import static org.bukkit.Bukkit.getLogger;

public class Warp implements CommandExecutor, TabCompleter {

    private final WarpPluginLoader plugin;

    public Warp(WarpPluginLoader plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            getLogger().severe(MUST_BE_PLAYER_ERROR);
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§c§lUsage: /warp <name>");
            return false;
        }

        WarpManager manager = plugin.getWarpManager();
        String warpName = args[0];

        if (manager.getWarp(warpName).isEmpty()) {
            player.sendMessage(PREFIX + "§cWarp: " + warpName + " does not exist!");
            return false;
        }
        player.teleport(manager.getWarp(warpName).get().toLocation());
        player.sendMessage(PREFIX + "§aYou have been teleported to " + warpName + "!");
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