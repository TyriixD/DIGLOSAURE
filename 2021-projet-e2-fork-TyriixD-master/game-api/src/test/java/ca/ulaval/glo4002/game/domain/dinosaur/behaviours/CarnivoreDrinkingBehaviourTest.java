package ca.ulaval.glo4002.game.domain.dinosaur.behaviours;

import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CarnivoreDrinkingBehaviourTest {
    private LunchBox lunchBoxMock;
    private Weight weight;
    private DrinkingBehaviour carnivoreDrinkingBehaviour;
    private String carnivoreWater;

    @BeforeEach
    public void setupCarnivoreDrinkingBehaviour() {
        lunchBoxMock = mock(LunchBox.class);
        carnivoreDrinkingBehaviour = new CarnivoreDrinkingBehaviour();
        weight = Weight.fromKg(1000);
        carnivoreWater = "meat";
    }

    @Test
    public void whenDrinkingCarnivoreWaterForTheFirstTime_thenConsumeDoubleCarnivoreWaterQuantity() {
        carnivoreDrinkingBehaviour.drink(carnivoreWater, weight, lunchBoxMock);

        int doubleQuantity = 1200;
        verify(lunchBoxMock).consumeWaterForCarnivores(doubleQuantity);
    }

    @Test
    public void givenHavingDrankBefore_whenDrinkingAgain_thenConsumeNormalCarnivoreWaterQuantity() {
        firstCallToDrink();

        carnivoreDrinkingBehaviour.drink(carnivoreWater, weight, lunchBoxMock);

        int normalQuantity = 600;
        verify(lunchBoxMock).consumeWaterForCarnivores(normalQuantity);
    }

    private void firstCallToDrink() {
        carnivoreDrinkingBehaviour.drink("meat", weight, lunchBoxMock);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithCarnivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = carnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertTrue(resourcesNeeds.waterForCarnivoresQuantity > 0);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoHerbivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = carnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.waterForHerbivoresQuantity);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoBurgerIsCreated() {
        ResourcesNeeds resourcesNeeds = carnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.burgerQuantity);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithSaladIsCreated() {
        ResourcesNeeds resourcesNeeds = carnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.saladQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalNormalResourcesNeeds_whenCalculatingResourcesNeeds_thenCarnivoreWaterResourcesNeedsIsCeiled() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1256);
        firstCallToDrink();

        ResourcesNeeds resourcesNeeds = carnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledNormalCarnivoreWaterResourcesNeeds = 754;
        assertEquals(ceiledNormalCarnivoreWaterResourcesNeeds, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalDoubledResourcesNeed_whenCalculatingResourcesNeeds_thenCarnivoreWaterResourcesNeedsIsCeiledAfterDoubling() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1257);

        ResourcesNeeds resourcesNeeds = carnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledDoubledCarnivoreResourcesNeeds = 1509;
        assertEquals(ceiledDoubledCarnivoreResourcesNeeds, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void givenMarkedAsThirsty_whenCalculatingRequiredResources_thenSaladResourcesNeedsIsDoubled() {
        carnivoreDrinkingBehaviour.markAsThirsty();

        ResourcesNeeds resourcesNeeds = carnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        int doubledSaladResourcesNeeds = 1200;
        assertEquals(doubledSaladResourcesNeeds, resourcesNeeds.waterForCarnivoresQuantity);
    }

}
