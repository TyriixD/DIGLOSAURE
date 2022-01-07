package ca.ulaval.glo4002.game.domain.dinosaur.behaviours;

import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;

import java.io.Serializable;

import static java.lang.Math.ceil;

public class OmnivoreDrinkingBehaviour implements DrinkingBehaviour, Serializable {
    private static final double DRINK_MULTIPLIER = 0.6;
    private boolean isThirsty = true;
    private boolean drinkAsMeatEaterWasCalled = false;
    private boolean drinkAsPlantEaterWasCalled = false;

    @Override
    public boolean drink(String resourceType, Weight weight, LunchBox lunchBox) {
        int requiredWaterAmountOfEachRation = calculateRequiredWaterAmountOfEachRation(weight);
        boolean hasDrank = false;

        if (resourceType.equals("meat")) {
            drinkAsMeatEaterWasCalled = true;
            hasDrank = lunchBox.consumeWaterForCarnivores(requiredWaterAmountOfEachRation);
        } else if (resourceType.equals("plant")) {
            drinkAsPlantEaterWasCalled = true;
            hasDrank = lunchBox.consumeWaterForHerbivores(requiredWaterAmountOfEachRation);
        }

        if (drinkAsMeatEaterWasCalled && drinkAsPlantEaterWasCalled) {
            isThirsty = false;
        }

        return hasDrank;
    }

    @Override
    public ResourcesNeeds calculateRequiredResourcesNeeds(Weight weight) {
        int requiredWaterAmountOfEachRation = calculateRequiredWaterAmountOfEachRation(weight);
        return new ResourcesNeeds(0, 0, requiredWaterAmountOfEachRation, requiredWaterAmountOfEachRation);
    }

    private int calculateRequiredWaterAmountOfEachRation(Weight weight) {
        double waterToConsume = weight.getWeightKg() * DRINK_MULTIPLIER;

        if (isThirsty) {
            waterToConsume *= 2;
        }

        double amountBeforeDividingInTwoPartitions = ceil(waterToConsume);
        return (int) ceil(amountBeforeDividingInTwoPartitions / 2);
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
