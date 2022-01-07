package ca.ulaval.glo4002.game.domain.dinosaur.behaviours;

import ca.ulaval.glo4002.game.domain.dinosaur.FeedingMultiplier;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;
import java.io.Serializable;

public class HerbivoreEatingBehaviour implements EatingBehaviour, Serializable {
    private boolean isHungry = true;

    @Override
    public boolean eat(String resourceType, Weight weight, LunchBox lunchBox) {
        int requiredFoodAmount = (int) calculateRequiredFoodAmount(weight);
        isHungry = false;
        return lunchBox.consumeSalads(requiredFoodAmount);
    }

    @Override
    public ResourcesNeeds calculateRequiredResourcesNeeds(Weight weight) {
        int requiredSaladAmount = (int) calculateRequiredFoodAmount(weight);
        return new ResourcesNeeds(0, requiredSaladAmount, 0, 0);
    }

    private double calculateRequiredFoodAmount(Weight weight) {
        double foodToConsume =
            weight.getWeightKg() * FeedingMultiplier.HERBIVORE.eatingMultiplier / FeedingMultiplier.HERBIVORE.divider;

        if (isHungry) {
            foodToConsume *= 2;
        }

        return Math.ceil(foodToConsume);
    }

    @Override
    public void markAsHungry() {
        isHungry = true;
    }

    @Override
    public double getStrengthMultiplier() {
        return FeedingMultiplier.HERBIVORE.strengthMultiplier;
    }

    @Override
    public boolean isHungry() {
        return isHungry;
    }
}
