package idan.dev.warp.commands;

import idan.dev.warp.WarpPluginLoader;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static idan.dev.warp.utils.TextUtils.MUST_BE_PLAYER_ERROR;
import static idan.dev.warp.utils.TextUtils.NO_PERMISSION_ERROR;
import static org.bukkit.Bukkit.getLogger;

public class WarpList implements CommandExecutor {

    private final WarpPluginLoader plugin;

    public WarpList(WarpPluginLoader plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            getLogger().severe(MUST_BE_PLAYER_ERROR);
            return true;
        }

        if (!player.hasPermission("plugin.admin")) {
            sender.sendMessage(Component.text(NO_PERMISSION_ERROR));
            return false;
        }

        player.sendMessage("§7{§r");
        plugin.getWarpManager().getWarpsName().forEach(warpName -> player.sendMessage(Component.text("  §e" + warpName + "§r,")));
        player.sendMessage("§7}");
        return true;
    }
}
