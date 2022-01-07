package ca.ulaval.glo4002.game.domain.dinosaur.behaviours;

import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HerbivoreDrinkingBehaviourTest {
    private HerbivoreDrinkingBehaviour herbivoreDrinkingBehaviour;
    private LunchBox lunchBoxMock;
    private Weight weight;
    private String resourceType;

    @BeforeEach
    void setUpHerbivoreDrinkingBehaviour() {
        lunchBoxMock = mock(LunchBox.class);
        herbivoreDrinkingBehaviour = new HerbivoreDrinkingBehaviour();
        weight = Weight.fromKg(1000);
        resourceType = "plant";
    }

    @Test
    void whenDrinkingForTheFirstTime_thenShouldConsumeDoubleWaterQuantity() {
        herbivoreDrinkingBehaviour.drink(resourceType, weight, lunchBoxMock);

        int doubleQuantity = 1200;
        verify(lunchBoxMock).consumeWaterForHerbivores(doubleQuantity);
    }

    @Test
    void givenHavingDrunkBefore_whenDrinkingForTheFirstTime_thenShouldConsumeNormalWaterQuantity() {
        firstCallToDrink();

        herbivoreDrinkingBehaviour.drink(resourceType, weight, lunchBoxMock);

        int normalQuantity = 600;
        verify(lunchBoxMock).consumeWaterForHerbivores(normalQuantity);
    }

    private void firstCallToDrink() {
        herbivoreDrinkingBehaviour.drink(resourceType, weight, lunchBoxMock);
    }

    @Test
    void whenDrinking_thenShouldNotBeThirsty() {
        herbivoreDrinkingBehaviour.drink(resourceType, weight, lunchBoxMock);

        assertFalse(herbivoreDrinkingBehaviour.isThirsty());
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourceNeedsWithHerbivoreWaterIsCreated() {
        ResourcesNeeds resourcesNeeds = herbivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertTrue(resourcesNeeds.waterForHerbivoresQuantity > 0);
    }

    @Test
    public void whenCalculatingRequiredResourcesNeeds_thenResourceNeedsWithNoOtherResourcesIsCreated() {
        ResourcesNeeds resourcesNeeds = herbivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(0, resourcesNeeds.burgerQuantity);
        assertEquals(0, resourcesNeeds.saladQuantity);
        assertEquals(0, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void givenAFirstCallToDrink_whenMarkingAsThirsty_thenResourceNeedsIsDoubled() {
        firstCallToDrink();

        herbivoreDrinkingBehaviour.markAsThirsty();
        ResourcesNeeds resourcesNeeds = herbivoreDrinkingBehaviour.calculateRequiredResourcesNeeds(weight);

        assertEquals(1200, resourcesNeeds.waterForHerbivoresQuantity);
    }

    @Test
    void whenMarkingAsThirsty_thenShouldBeThirsty() {
        herbivoreDrinkingBehaviour.markAsThirsty();

        assertTrue(herbivoreDrinkingBehaviour.isThirsty());
    }
}
