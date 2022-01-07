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

public class CarnivoreEatingBehaviourTest {
    private CarnivoreEatingBehaviour carnivoreEatingBehaviour;
    private LunchBox lunchBoxMock;
    private Weight weight;
    private String meat;

    @BeforeEach
    public void setUpCarnivoreEatingBehaviour() {
        lunchBoxMock = mock(LunchBox.class);
        carnivoreEatingBehaviour = new CarnivoreEatingBehaviour();
        weight = Weight.fromKg(1000);
        meat = "meat";
    }

    @Test
    public void whenEatingForTheFirstTime_thenConsumeDoubleBurgerQuantity() {
        carnivoreEatingBehaviour.eat(meat, weight, lunchBoxMock);

        int doubleQuantity = 2;
        verify(lunchBoxMock).consumeBurgers(doubleQuantity);
    }

    @Test
    public void givenHavingEatenBefore_whenEatingAgain_thenConsumeNormalBurgerQuantity() {
        firstCallToEat();

        carnivoreEatingBehaviour.eat(meat, weight, lunchBoxMock);

        int normalQuantity = 1;
        verify(lunchBoxMock).consumeBurgers(normalQuantity);
    }

    private void firstCallToEat() {
        carnivoreEatingBehaviour.eat(meat, weight, lunchBoxMock);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithBurgersIsCreated() {
        ResourcesNeeds resourcesNeeds = carnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertTrue(resourcesNeeds.burgerQuantity > 0);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoSaladIsCreated() {
        ResourcesNeeds resourcesNeeds = carnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.saladQuantity);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoCarnivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = carnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoHerbivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = carnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.waterForHerbivoresQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalNormalResourcesNeeds_whenCalculatingResourcesNeeds_thenBurgerResourcesNeedsIsCeiled() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1256);
        firstCallToEat();

        ResourcesNeeds resourcesNeeds = carnivoreEatingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledBurgerResourcesNeeds = 2;
        assertEquals(ceiledBurgerResourcesNeeds, resourcesNeeds.burgerQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalDoubledResourcesNeed_whenCalculatingResourcesNeeds_thenCarnivoreWaterResourcesNeedsIsCeiledAfterDoubling() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1256);

        ResourcesNeeds resourcesNeeds = carnivoreEatingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledDoubledBurgerResourcesNeeds = 3;
        assertEquals(ceiledDoubledBurgerResourcesNeeds, resourcesNeeds.burgerQuantity);
    }

    @Test
    public void givenMarkedAsHungry_whenCalculatingRequiredResources_thenResourcesNeedsIsDoubled() {
        carnivoreEatingBehaviour.markAsHungry();

        ResourcesNeeds resourcesNeeds = carnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        int doubledBurgerResourcesNeeds = 2;
        assertEquals(doubledBurgerResourcesNeeds, resourcesNeeds.burgerQuantity);
    }
}
