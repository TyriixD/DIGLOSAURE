package ca.ulaval.glo4002.game.domain.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pantry implements Serializable {
    private final List<Resources> freshResources = new ArrayList<>();
    private final List<Resources> consumedResources = new ArrayList<>();
    private final List<Resources> expiredResources = new ArrayList<>();
    private final RationStrategy rationStrategy;

    public Pantry(RationStrategy rationStrategy) {
        this.rationStrategy = rationStrategy;
    }

    public void addResources(Resources resources) {
        freshResources.add(resources);
    }

    public List<Resources> getAllResources() {
        List<Resources> allResources = new ArrayList<>();

        allResources.addAll(freshResources);
        allResources.addAll(consumedResources);
        allResources.addAll(expiredResources);

        return allResources;
    }

    public LunchBox prepareLunchBox(ResourcesNeeds resourcesNeeds) {
        return rationStrategy.prepareLunchBox(resourcesNeeds, freshResources, consumedResources);
    }

    public void removeExpiredResources() {
        for (Resources resources : freshResources) {
            resources.decrementShelfLife();

            if (resources.isExpired()) {
                expiredResources.add(resources);
            }
        }

        freshResources.removeAll(expiredResources);
    }

    public void reset() {
        freshResources.clear();
        consumedResources.clear();
        expiredResources.clear();
    }

}
