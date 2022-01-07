package ca.ulaval.glo4002.game.domain.dinosaur.behaviours;

import ca.ulaval.glo4002.game.domain.dinosaur.FeedingMultiplier;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;
import java.io.Serializable;

public class CarnivoreEatingBehaviour implements EatingBehaviour, Serializable {
    private boolean doubleResourcesNeeds = true;

    @Override
    public boolean eat(String resourceType, Weight weight, LunchBox lunchBox) {
        int requiredFoodAmount = (int) calculateRequiredFoodAmount(weight);
        doubleResourcesNeeds = false;
        return lunchBox.consumeBurgers(requiredFoodAmount);
    }

    @Override
    public ResourcesNeeds calculateRequiredResourcesNeeds(Weight weight) {
        int requiredBurgerAmount = (int) calculateRequiredFoodAmount(weight);
        return new ResourcesNeeds(requiredBurgerAmount, 0, 0, 0);
    }

    private double calculateRequiredFoodAmount(Weight weight) {
        double foodToConsume =
            weight.getWeightKg() * FeedingMultiplier.CARNIVORE.eatingMultiplier / FeedingMultiplier.CARNIVORE.divider;

        if (doubleResourcesNeeds) {
            foodToConsume *= 2;
        }

        return Math.ceil(foodToConsume);
    }

    @Override
    public void markAsHungry() {
        doubleResourcesNeeds = true;
    }

    @Override
    public double getStrengthMultiplier() {
        return FeedingMultiplier.CARNIVORE.strengthMultiplier;
    }

    @Override
    public boolean isHungry() {
        return doubleResourcesNeeds;
    }
}
