package ca.ulaval.glo4002.game.domain.game.action;

import ca.ulaval.glo4002.game.domain.game.Park;
import ca.ulaval.glo4002.game.domain.resources.Resources;

public class ResourceCreationAction implements Action {
    private final Resources resources;
    private final Park park;

    public ResourceCreationAction(Resources resources, Park park) {
        this.resources = resources;
        this.park = park;
    }

    @Override
    public void execute() {
        park.addResources(resources);
    }
}
