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

public class HerbivoreEatingBehaviourTest {
    private HerbivoreEatingBehaviour herbivoreEatingBehaviour;
    private LunchBox lunchBoxMock;
    private Weight weight;
    private String plant;

    @BeforeEach
    public void setUpHerbivoreEatingBehaviour() {
        lunchBoxMock = mock(LunchBox.class);
        herbivoreEatingBehaviour = new HerbivoreEatingBehaviour();
        weight = Weight.fromKg(1000);
        plant = "plant";
    }

    @Test
    public void whenEatingForTheFirstTime_thenConsumeDoubleSaladQuantity() {
        herbivoreEatingBehaviour.eat(plant, weight, lunchBoxMock);

        int doubleQuantity = 5;
        verify(lunchBoxMock).consumeSalads(doubleQuantity);
    }

    @Test
    public void givenHavingEatenBefore_whenEatingAgain_thenConsumeNormalSaladQuantity() {
        firstCallToEat();

        herbivoreEatingBehaviour.eat(plant, weight, lunchBoxMock);

        int normalQuantity = 3;
        verify(lunchBoxMock).consumeSalads(normalQuantity);
    }

    private void firstCallToEat() {
        herbivoreEatingBehaviour.eat(plant, weight, lunchBoxMock);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithSaladsIsCreated() {
        ResourcesNeeds resourcesNeeds = herbivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertTrue(resourcesNeeds.saladQuantity > 0);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoBurgerIsCreated() {
        ResourcesNeeds resourcesNeeds = herbivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.burgerQuantity);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoCarnivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = herbivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourcesNeedsWithNoHerbivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = herbivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.waterForHerbivoresQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalResourcesNeeds_whenCalculatingResourcesNeeds_thenSaladResourcesNeedsIsCeiled() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1256);
        firstCallToEat();

        ResourcesNeeds resourcesNeeds = herbivoreEatingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledSaladResourcesNeeds = 4;
        assertEquals(ceiledSaladResourcesNeeds, resourcesNeeds.saladQuantity);
    }

    @Test
    public void givenWeightThatGivesFractionalDoubledResourcesNeed_whenCalculatingResourcesNeeds_thenCarnivoreWaterResourcesNeedsIsCeiledAfterDoubling() {
        Weight weightThatGivesFractionalResourcesNeed = Weight.fromKg(1256);

        ResourcesNeeds resourcesNeeds = herbivoreEatingBehaviour.calculateRequiredResourcesNeeds(
            weightThatGivesFractionalResourcesNeed);

        int ceiledDoubledSaladResourcesNeeds = 7;
        assertEquals(ceiledDoubledSaladResourcesNeeds, resourcesNeeds.saladQuantity);
    }

    @Test
    public void givenMarkedAsHungry_whenCalculatingRequiredResources_thenResourcesNeedsIsDoubled() {
        herbivoreEatingBehaviour.markAsHungry();

        ResourcesNeeds resourcesNeeds = herbivoreEatingBehaviour.calculateRequiredResourcesNeeds(weight);

        int doubledSaladResourcesNeeds = 5;
        assertEquals(doubledSaladResourcesNeeds, resourcesNeeds.saladQuantity);
    }

    @Test
    public void whenMarkingAsHungry_thenBehaviourIsHungry() {
        herbivoreEatingBehaviour.markAsHungry();

        assertTrue(herbivoreEatingBehaviour.isHungry());
    }
}
