package ca.ulaval.glo4002.game.domain.dinosaur.behaviours;

import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;

public interface EatingBehaviour {
    boolean eat(String resourceType, Weight weight, LunchBox lunchBox);

    ResourcesNeeds calculateRequiredResourcesNeeds(Weight weight);

    void markAsHungry();

    double getStrengthMultiplier();

    boolean isHungry();
}
