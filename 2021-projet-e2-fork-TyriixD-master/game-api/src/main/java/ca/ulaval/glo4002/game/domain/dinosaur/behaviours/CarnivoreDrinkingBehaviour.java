package ca.ulaval.glo4002.game.domain.dinosaur.behaviours;

import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;
import java.io.Serializable;

public class CarnivoreDrinkingBehaviour implements DrinkingBehaviour, Serializable {
    private static final double DRINK_MULTIPLIER = 0.6;
    private boolean isThirsty = true;

    @Override
    public boolean drink(String resourceType, Weight weight, LunchBox lunchBox) {
        int requiredWaterAmount = (int) calculateRequiredWaterAmount(weight);
        isThirsty = false;
        return lunchBox.consumeWaterForCarnivores(requiredWaterAmount);
    }

    @Override
    public ResourcesNeeds calculateRequiredResourcesNeeds(Weight weight) {
        int requiredWaterAmount = (int) calculateRequiredWaterAmount(weight);
        return new ResourcesNeeds(0, 0, requiredWaterAmount, 0);
    }

    private double calculateRequiredWaterAmount(Weight weight) {
        double waterToConsume = weight.getWeightKg() * DRINK_MULTIPLIER;

        if (isThirsty) {
            waterToConsume *= 2;
        }

        return Math.ceil(waterToConsume);
    }

    @Override
    public void markAsThirsty() {
        isThirsty = true;
    }

    @Override
    public boolean isThirsty() {
        return isThirsty;
    }
}
