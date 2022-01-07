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

public class OmnivoreDrinkingBehaviourTest {
    private OmnivoreDrinkingBehaviour omnivoreDrinkingBehaviour;
    private LunchBox lunchBoxMock;
    private Weight weight;

    @BeforeEach
    public void setOmnivoreDrinkingBehaviour() {
        lunchBoxMock = mock(LunchBox.class);
        omnivoreDrinkingBehaviour = new OmnivoreDrinkingBehaviour();
        weight = Weight.fromKg(1000);
    }

    @Test
    public void whenDrinkingCarnivoreWaterForTheFirstTime_thenConsumeDoubleCarnivoreWaterQuantity() {
        String carnivoreWater = "meat";
        omnivoreDrinkingBehaviour.drink(carnivoreWater, weight, lunchBoxMock);

        int doubleQuantity = 600;
        verify(lunchBoxMock).consumeWaterForCarnivores(doubleQuantity);
    }

    @Test
    public void whenDrinkingHerbivoreeWaterForTheFirstTime_thenConsumeDoubleCarnivoreWaterQuantity() {
        String herbivoreWater = "plant";
        omnivoreDrinkingBehaviour.drink(herbivoreWater, weight, lunchBoxMock);

        int doubleQuantity = 600;
        verify(lunchBoxMock).consumeWaterForHerbivores(doubleQuantity);
    }

    @Test
    public void givenHavingDrankBothWaterResourceTypesBefore_whenDrinkingCarnivoreWaterAgain_thenConsumeNormalCarnivoreWaterQuantity() {
        drinkBothWaterResourceTypes();

        String carnivoreWater = "meat";
        omnivoreDrinkingBehaviour.drink(carnivoreWater, weight, lunchBoxMock);

        int normalQuantity = 300;
        verify(lunchBoxMock).consumeWaterForCarnivores(normalQuantity);
    }

    @Test
    public void givenHavingDrankBothWaterResourceTypesBefore_whenDrinkingHerbivoreWaterAgain_thenConsumeNormalHerbivoreWaterQuantity() {
        drinkBothWaterResourceTypes();

        String herbivoreWater = "plant";
        omnivoreDrinkingBehaviour.drink(herbivoreWater, weight, lunchBoxMock);

        int normalQuantity = 300;
        verify(lunchBoxMock).consumeWaterForHerbivores(normalQuantity);
    }

    private void drinkBothWaterResourceTypes() {
        omnivoreDrinkingBehaviour.drink("meat", weight, lunchBoxMock);
        omnivoreDrinkingBehaviour.drink("plant", weight, lunchBoxMock);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourceNeedsWithCarnivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertTrue(resourcesNeeds.waterForCarnivoresQuantity > 0);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourceNeedsWithHerbivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertTrue(resourcesNeeds.waterForHerbivoresQuantity > 0);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourceNeedsWithNoBurgerIsCreated() {
        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.burgerQuantity);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourceNeedsWithNoSaladIsCreated() {
        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.saladQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalDoubledResourcesNeeds_whenCalculatingResourcesNeeds_thenCarnivoreWaterResourcesNeedsIsCeiledBeforeSplitting() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1256);

        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledDoubledSplittedQuantity = 754;
        assertEquals(ceiledDoubledSplittedQuantity, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalDoubledResourcesNeeds_whenCalculatingResourcesNeeds_thenHerbivoreWaterResourcesNeedsIsCeiledBeforeSplitting() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1256);

        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledDoubledSplittedQuantity = 754;
        assertEquals(ceiledDoubledSplittedQuantity, resourcesNeeds.waterForHerbivoresQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalNormalResourcesNeeds_whenCalculatingResourcesNeeds_thenCarnivoreWaterResourcesNeedsIsCeiledAfterSplitting() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1257);
        drinkBothWaterResourceTypes();

        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledNormalSplittedQuantity = 378;
        assertEquals(ceiledNormalSplittedQuantity, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalNormalResourcesNeeds_whenCalculatingResourcesNeeds_thenHerbivoreWaterResourcesNeedsIsCeiledAfterSplitting() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1257);
        drinkBothWaterResourceTypes();

        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledNormalSplittedQuantity = 378;
        assertEquals(ceiledNormalSplittedQuantity, resourcesNeeds.waterForHerbivoresQuantity);
    }

    @Test
    public void givenMarkedAsThirsty_whenCalculatingRequiredResources_thenCarnivoreWaterResourcesNeedsIsDoubled() {
        omnivoreDrinkingBehaviour.markAsThirsty();

        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        int doubledCarnivoreWaterResourceNeeds = 600;
        assertEquals(doubledCarnivoreWaterResourceNeeds, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void givenMarkedAsThirsty_whenCalculatingRequiredResources_thenHerbivoreWaterResourcesNeedsIsDoubled() {
        omnivoreDrinkingBehaviour.markAsThirsty();

        ResourcesNeeds resourcesNeeds = omnivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        int doubledHerbivoreWaterResourceNeeds = 600;
        assertEquals(doubledHerbivoreWaterResourceNeeds, resourcesNeeds.waterForHerbivoresQuantity);
    }
}
