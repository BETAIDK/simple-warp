package idan.dev.warp.utils;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class OfflineLocation {

    private final String world;
    private final double x, y, z;
    private final float yaw, pitch;

    public OfflineLocation(String world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Location toLocation(World world) {
        if (world == null) world = Bukkit.getWorld(this.world);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public Location toLocation() {
        return toLocation(null);
    }

    @Override
    public String toString() {
        return world + " " + x + " " + y + " " + z + " " + yaw + " " + pitch;
    }

    public static OfflineLocation from(@NonNull Location location) {
        return new OfflineLocation(
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch()
        );
    }

    public static OfflineLocation of(String s) {
        if (s == null || s.length() == 0) return null;
        String[] args = s.split("\\s+");
        return switch (args.length) {
            case 3 -> new OfflineLocation(null, Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), 0, 0);
            case 4 -> new OfflineLocation(args[0], Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), 0, 0);
            case 5 -> new OfflineLocation(null, Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Float.parseFloat(args[3]), Float.parseFloat(args[4]));
            case 6 -> new OfflineLocation(args[0], Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Float.parseFloat(args[4]), Float.parseFloat(args[5]));
            default -> null;
        };
    }
}