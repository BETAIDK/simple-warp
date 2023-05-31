package idan.dev.warp;

import idan.dev.warp.commands.SetWarp;
import idan.dev.warp.commands.UnWarp;
import idan.dev.warp.commands.Warp;
import idan.dev.warp.commands.WarpList;
import idan.dev.warp.managers.WarpManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class WarpPluginLoader extends JavaPlugin {

    @Getter
    private WarpManager warpManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Startup message
        getServer().getConsoleSender().sendMessage("§a§lRunning plugin: §rSimple-warp v0.1");
        getServer().getConsoleSender().sendMessage("§a§lRunning on Bukkit §r- Paper");
        getServer().getConsoleSender().sendMessage("§a§lMade by: §rBeta#8084");

        // Register commands
        getCommand("setWarp").setExecutor(new SetWarp(this));
        getCommand("warp").setExecutor(new Warp(this));
        getCommand("unWarp").setExecutor(new UnWarp(this));
        getCommand("warpList").setExecutor(new WarpList(this));

        warpManager = new WarpManager(this);
    }
}
