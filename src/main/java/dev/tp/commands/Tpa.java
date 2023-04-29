package dev.tp.commands;

import dev.tp.TpPluginLoader;
import dev.tp.system.TpaManager;
import dev.tp.system.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Tpa implements CommandExecutor, TabCompleter {

    private final TpPluginLoader plugin;

    public Tpa(TpPluginLoader plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().severe("Only player allowed to use commands");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§cUsage: /tpa <name>");
            return false;
        }

        TpaManager manager = plugin.getTpaManager();
        UUID target = UUID.fromString(args[0]);

        if (manager.getTarget(target).isEmpty()) return false;
        manager.addRequest(player.getUniqueId(), target);
        player.sendMessage("§aRequest sent to " + args[0]);
        sender.sendMessage("§aYou have a request from " + player.getName());
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
