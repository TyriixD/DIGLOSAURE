package ca.ulaval.glo4002.game.domain.resources;

import java.util.ArrayList;
import java.util.List;

public class ResourcesFactory {
    public List<Resources> create(int burgerQuantity, int saladQuantity, int waterQuantity) {
        List<Resources> resources = new ArrayList<>();

        if (burgerQuantity > 0) {
            resources.add(new Resources(ResourceInfo.BURGER, ResourceState.FRESH, burgerQuantity));
        }

        if (saladQuantity > 0) {
            resources.add(new Resources(ResourceInfo.SALAD, ResourceState.FRESH, saladQuantity));
        }

        if (waterQuantity > 0) {
            resources.add(new Resources(ResourceInfo.WATER, ResourceState.FRESH, waterQuantity));
        }

        return resources;
    }
}
