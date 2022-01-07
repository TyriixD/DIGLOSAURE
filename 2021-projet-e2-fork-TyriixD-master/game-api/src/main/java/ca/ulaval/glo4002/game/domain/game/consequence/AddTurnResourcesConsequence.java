package ca.ulaval.glo4002.game.domain.game.consequence;

import ca.ulaval.glo4002.game.domain.game.Park;
import ca.ulaval.glo4002.game.domain.resources.ResourceInfo;
import ca.ulaval.glo4002.game.domain.resources.ResourceState;
import ca.ulaval.glo4002.game.domain.resources.Resources;

public class AddTurnResourcesConsequence implements Consequence {
    private final Park park;

    public AddTurnResourcesConsequence(Park park) {
        this.park = park;
    }

    @Override
    public void apply() {
        park.addResources(new Resources(ResourceInfo.WATER, ResourceState.FRESH, 10000));
        park.addResources(new Resources(ResourceInfo.BURGER, ResourceState.FRESH, 100));
        park.addResources(new Resources(ResourceInfo.SALAD, ResourceState.FRESH, 250));
    }
}
