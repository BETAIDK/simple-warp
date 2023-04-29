package dev.tp.system;

import dev.tp.utils.OfflineLocation;
import lombok.NonNull;

import java.util.*;

public class TpaManager {

    private final Map<UUID, Set<UUID>> tpaRequests = new HashMap<>();

    public void addRequest(@NonNull UUID sender, @NonNull UUID target) {
        Set<UUID> requests = tpaRequests.getOrDefault(sender, new HashSet<>());
        requests.add(target);
        tpaRequests.put(sender, requests);
    }

    public void removeRequest(@NonNull UUID sender, @NonNull UUID target) {
        Set<UUID> requests = tpaRequests.getOrDefault(sender, new HashSet<>());
        requests.remove(target);
        tpaRequests.put(sender, requests);
    }

    public Optional<Set<UUID>> getTarget(@NonNull UUID target) {
        return Optional.ofNullable(tpaRequests.get(target));
    }

    public boolean hasRequest(@NonNull UUID sender, @NonNull UUID target) {
        Set<UUID> requests = tpaRequests.get(sender);
        return requests != null && requests.contains(target);
    }

    public Set<UUID> getRequests(@NonNull UUID sender) {
        return tpaRequests.getOrDefault(sender, new HashSet<>());
    }

    public void clearRequests(UUID sender) {
        tpaRequests.remove(sender);
    }

    public void clearAllRequests() {
        tpaRequests.clear();
    }
}
