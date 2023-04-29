package dev.tp;

import dev.tp.commands.SetWarp;
import dev.tp.commands.UnWarp;
import dev.tp.commands.Warp;
import dev.tp.listeners.OnPlayerQuitEvent;
import dev.tp.system.TpaManager;
import dev.tp.system.WarpManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class TpPluginLoader extends JavaPlugin {

    @Getter private WarpManager warpManager;
    @Getter private TpaManager tpaManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        saveConfig();

        // register listeners
        getServer().getPluginManager().registerEvents(new OnPlayerQuitEvent(this), this);

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
