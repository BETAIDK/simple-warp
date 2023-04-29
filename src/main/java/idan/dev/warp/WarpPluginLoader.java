package idan.dev.warp;

import idan.dev.warp.commands.SetWarp;
import idan.dev.warp.commands.UnWarp;
import idan.dev.warp.commands.Warp;
import idan.dev.warp.system.WarpManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WarpPluginLoader extends JavaPlugin {

    @Getter private WarpManager warpManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        saveConfig();

        Bukkit.getServer().getConsoleSender().sendMessage("§a§lRunning plugin: §rSimple-warp v0.1");
        Bukkit.getServer().getConsoleSender().sendMessage("§a§lRunning on Bukkit §r- Spigot");
        Bukkit.getServer().getConsoleSender().sendMessage("§a§lMade by: §rBeta#8084");

        // register commands
        getCommand("setWarp").setExecutor(new SetWarp(this));
        getCommand("warp").setExecutor(new Warp(this));
        getCommand("unWarp").setExecutor(new UnWarp(this));

        warpManager = new WarpManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
