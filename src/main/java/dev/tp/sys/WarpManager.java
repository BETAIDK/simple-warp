package dev.tp.sys;

import dev.tp.TpPluginLoader;
import dev.tp.utils.OfflineLocation;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class WarpManager {

    public static final Pattern WARP_REGEX = Pattern.compile("^[a-zA-Z0-9_]{1,20}$");

    private final TpPluginLoader plugin;
    private final Map<String, OfflineLocation> warps;

    public WarpManager(TpPluginLoader plugin) {
        this.plugin = plugin;
        warps = new HashMap<>();

        loadConfig();
    }

    public void addWarp(@NonNull String warpName, @NonNull OfflineLocation location) {
        if (!WARP_REGEX.matcher(warpName).matches()) return;
        warps.put(warpName, location);
        saveConfig();
    }

    public void removeWarp(@NonNull String warpName) {
        if (!WARP_REGEX.matcher(warpName).matches()) return;
        if (warps.remove(warpName) == null) return;
        plugin.getConfig().set("warps." + warpName, null);
    }

    public Set<String> getWarpsName() {
        return warps.keySet();
    }

    public Optional<OfflineLocation> getWarp(@NonNull String warpName) {
        return Optional.ofNullable(warps.get(warpName));
    }

    public void loadConfig() {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("warps");
        if (section == null) return;
        for (String key : section.getKeys(false))
            warps.put(key, OfflineLocation.of(section.getString(key)));
    }

    public void saveConfig() {
        for (var entry : warps.entrySet()) {
            plugin.getConfig().set("warps." + entry.getKey(), entry.getValue().toString());
        }
        plugin.saveConfig();
    }
}
