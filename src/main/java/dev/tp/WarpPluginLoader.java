package dev.tp;

import dev.tp.commands.*;
import dev.tp.system.WarpManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class WarpPluginLoader extends JavaPlugin {

    @Getter private WarpManager warpManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        saveConfig();

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
