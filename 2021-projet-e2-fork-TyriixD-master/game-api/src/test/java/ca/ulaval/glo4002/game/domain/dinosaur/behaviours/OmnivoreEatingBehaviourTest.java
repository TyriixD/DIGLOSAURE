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

public class OmnivoreEatingBehaviourTest {
    private OmnivoreEatingBehaviour omnivoreEatingBehaviour;
    private LunchBox lunchBoxMock;
    private Weight weight;

    @BeforeEach
    public void setOmnivoreEatingBehaviourp() {
        lunchBoxMock = mock(LunchBox.class);
        omnivoreEatingBehaviour = new OmnivoreEatingBehaviour();
        weight = Weight.fromKg(2000);
    }

    @Test
    public void whenEatingForTheFirstTime_thenConsumeDoubleBurgerQuantity() {
        String meat = "meat";
        omnivoreEatingBehaviour.eat(meat, weight, lunchBoxMock);

        int doubleQuantity = 2;
        verify(lunchBoxMock).consumeBurgers(doubleQuantity);
    }

    @Test
    public void whenEatingForTheFirstTime_thenConsumeDoubleSaladQuantity() {
        String plant = "plant";
        omnivoreEatingBehaviour.eat(plant, weight, lunchBoxMock);

        int doubleQuantity = 5;
        verify(lunchBoxMock).consumeSalads(doubleQuantity);
    }

    @Test
    public void givenHavingEatenBefore_whenEatingAgain_thenConsumeNormalBurgerQuantity() {
        String meat = "meat";
        firstCallToEat();

        omnivoreEatingBehaviour.eat(meat, weight, lunchBoxMock);

        int doubleQuantity = 2;
        verify(lunchBoxMock).consumeBurgers(doubleQuantity);
    }

    @Test
    public void givenHavingEatenBefore_whenEatingAgain_thenConsumeNormalSaladQuantity() {
        String plant = "plant";
        firstCallToEat();

        omnivoreEatingBehaviour.eat(plant, weight, lunchBoxMock);

        int doubleQuantity = 3;
        verify(lunchBoxMock).consumeSalads(doubleQuantity);
    }

    private void firstCallToEat() {
        omnivoreEatingBehaviour.eat("meat", weight, lunchBoxMock);
        omnivoreEatingBehaviour.eat("plant", weight, lunchBoxMock);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithBurgersIsCreated() {
        ResourcesNeeds resourcesNeeds = omnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertTrue(resourcesNeeds.burgerQuantity > 0);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithSaladsIsCreated() {
        ResourcesNeeds resourcesNeeds = omnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertTrue(resourcesNeeds.saladQuantity > 0);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoCarnivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = omnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoHerbivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = omnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.waterForHerbivoresQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalResourcesNeeds_whenCalculatingResourcesNeeds_thenBurgerResourcesNeedsIsCeiled() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1256);
        firstCallToEat();

        ResourcesNeeds resourcesNeeds = omnivoreEatingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledBurgerResourcesNeeds = 2;
        assertEquals(ceiledBurgerResourcesNeeds, resourcesNeeds.saladQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalResourcesNeeds_whenCalculatingResourcesNeeds_thenSaladResourcesNeedsIsCeiled() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1256);
        firstCallToEat();

        ResourcesNeeds resourcesNeeds = omnivoreEatingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledSaladResourcesNeeds = 1;
        assertEquals(ceiledSaladResourcesNeeds, resourcesNeeds.burgerQuantity);
    }

    @Test
    public void givenMarkedAsHungry_whenCalculatingRequiredResources_thenBurgerResourcesNeedsIsDoubled() {
        omnivoreEatingBehaviour.markAsHungry();

        ResourcesNeeds resourcesNeeds = omnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        int doubledBurgerResourcesNeeds = 2;
        assertEquals(doubledBurgerResourcesNeeds, resourcesNeeds.burgerQuantity);
    }

    @Test
    public void givenMarkedAsHungry_whenCalculatingRequiredResources_thenSaladResourcesNeedsIsDoubled() {
        omnivoreEatingBehaviour.markAsHungry();

        ResourcesNeeds resourcesNeeds = omnivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        int doubledSaladResourcesNeeds = 5;
        assertEquals(doubledSaladResourcesNeeds, resourcesNeeds.saladQuantity);
    }
}
