package ca.ulaval.glo4002.game.domain.resources;

import java.util.List;

public interface RationStrategy {
    LunchBox prepareLunchBox(ResourcesNeeds order, List<Resources> freshResources, List<Resources> consumedResources);
}
