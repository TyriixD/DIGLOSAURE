package ca.ulaval.glo4002.game.domain.dinosaur.behaviours;

import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;

public interface DrinkingBehaviour {
    boolean drink(String resourceType, Weight weight, LunchBox lunchBox);

    ResourcesNeeds calculateRequiredResourcesNeeds(Weight weight);

    void markAsThirsty();

    boolean isThirsty();
}
