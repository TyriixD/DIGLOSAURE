package ca.ulaval.glo4002.game.domain.dinosaur.behaviours;

import ca.ulaval.glo4002.game.domain.dinosaur.FeedingMultiplier;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;
import java.io.Serializable;

public class OmnivoreEatingBehaviour implements EatingBehaviour, Serializable {
    private boolean isHungry = true;
    private boolean eatAsMeatEaterWasCalled = false;
    private boolean eatAsPlantEaterWasCalled = false;

    @Override
    public boolean eat(String resourceType, Weight weight, LunchBox lunchBox) {
        boolean hasEaten = false;

        if (resourceType.equals("meat")) {
            eatAsMeatEaterWasCalled = true;
            int requiredBurgerAmount = (int) calculateRequiredBurgerAmount(weight);
            hasEaten = lunchBox.consumeBurgers(requiredBurgerAmount);
        } else if (resourceType.equals("plant")) {
            eatAsPlantEaterWasCalled = true;
            int requiredSaladAmount = (int) calculateRequiredSaladAmount(weight);
            hasEaten = lunchBox.consumeSalads(requiredSaladAmount);
        }

        if (eatAsMeatEaterWasCalled && eatAsPlantEaterWasCalled) {
            isHungry = false;
        }

        return hasEaten;
    }

    @Override
    public ResourcesNeeds calculateRequiredResourcesNeeds(Weight weight) {
        int requiredSaladAmount = (int) calculateRequiredSaladAmount(weight);
        int requiredBurgerAmount = (int) calculateRequiredBurgerAmount(weight);

        return new ResourcesNeeds(requiredBurgerAmount, requiredSaladAmount, 0, 0);
    }

    private double calculateRequiredSaladAmount(Weight weight) {
        double saladDailyNeed = (weight.getWeightKg() * FeedingMultiplier.HERBIVORE.eatingMultiplier) /
            FeedingMultiplier.HERBIVORE.divider / 2;

        if (isHungry) {
            saladDailyNeed *= 2;
        }

        return Math.ceil(saladDailyNeed);
    }

    private double calculateRequiredBurgerAmount(Weight weight) {
        double burgerDailyNeed = (weight.getWeightKg() * FeedingMultiplier.CARNIVORE.eatingMultiplier) /
            FeedingMultiplier.CARNIVORE.divider / 2;

        if (isHungry) {
            burgerDailyNeed *= 2;
        }

        return Math.ceil(burgerDailyNeed);
    }

    @Override
    public void markAsHungry() {
        isHungry = true;
    }

    @Override
    public double getStrengthMultiplier() {
        return FeedingMultiplier.CARNIVORE.strengthMultiplier;
    }

    @Override
    public boolean isHungry() {
        return isHungry;
    }
}
