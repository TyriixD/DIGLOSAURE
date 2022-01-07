package ca.ulaval.glo4002.game.domain.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LunchBoxTest {

    private LunchBox lunchBoxTest;
    private boolean enoughFood;
    private Integer maximumValue;

    @BeforeEach
    public void setup() {
        enoughFood = false;
        maximumValue = Integer.MAX_VALUE;
        int burgerQuantity = 100;
        int saladQuantity = 100;
        int waterForHerbivoresQuantity = 190;
        int waterForCarnivoresQuantity = 100;
        lunchBoxTest = new LunchBox(burgerQuantity, saladQuantity, waterForCarnivoresQuantity,
                                    waterForHerbivoresQuantity);
    }

    @Test
    public void givenEnoughBurgers_whenConsumeBurgers_thenShouldReturnTrue() {
        enoughFood = lunchBoxTest.consumeBurgers(6);

        assertTrue(enoughFood);
    }

    @Test
    public void givenAnAbsurdAmountToConsume_whenConsumeBurgers_thenShouldReturnFalse() {
        enoughFood = lunchBoxTest.consumeBurgers(maximumValue);

        assertFalse(enoughFood);
    }

    @Test
    public void givenEnoughSalad_whenConsumeSalads_thenShouldReturnTrue() {
        enoughFood = lunchBoxTest.consumeSalads(10);

        assertTrue(enoughFood);
    }

    @Test
    public void givenAnAbsurdAmountToConsume_whenConsumeSalads_thenShouldReturnFalse() {
        enoughFood = lunchBoxTest.consumeSalads(maximumValue);

        assertFalse(enoughFood);
    }

    @Test
    public void givenEnoughWaterForCarnivores_whenConsumeWaterForCarnivores_thenShouldReturnTrue() {
        enoughFood = lunchBoxTest.consumeWaterForCarnivores(10);

        assertTrue(enoughFood);
    }

    @Test
    public void givenAnAbsurdAmountToConsume_whenConsumeWaterForCarnivores_thenShouldReturnFalse() {
        enoughFood = lunchBoxTest.consumeWaterForCarnivores(maximumValue);

        assertFalse(enoughFood);
    }

    @Test
    public void givenEnoughWaterForHerbivores_whenConsumeWaterForHerbivores_thenShouldReturnTrue() {
        enoughFood = lunchBoxTest.consumeWaterForHerbivores(10);

        assertTrue(enoughFood);
    }

    @Test
    public void givenAnAbsurdAmountToConsume_whenConsumeWaterForHerbivores_thenShouldReturnFalse() {
        enoughFood = lunchBoxTest.consumeWaterForHerbivores(maximumValue);

        assertFalse(enoughFood);
    }
}
