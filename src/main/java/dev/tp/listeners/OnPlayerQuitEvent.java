package dev.tp.listeners;

import dev.tp.TpPluginLoader;
import dev.tp.system.TpaManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuitEvent implements Listener {

    private final TpPluginLoader plugin;

    public OnPlayerQuitEvent(TpPluginLoader plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        TpaManager manager = plugin.getTpaManager();
        manager.clearAllRequests();
    }
}
