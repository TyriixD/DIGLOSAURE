package ca.ulaval.glo4002.game.domain.dinosaur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeightTest {
    private static final int ADDED_VALUES_IN_KG = 10;
    private static final int SMALLER_VALUE_IN_KG = 10;
    private static final int VALUE_IN_KG = 20;
    private Weight weight;

    @BeforeEach
    void setUp() {
        weight = Weight.fromKg(VALUE_IN_KG);
    }

    @Test
    public void givenASmallerWeight_whenIsSmallerThan_thenReturnTrue() {
        Weight smallerWeight = Weight.fromKg(SMALLER_VALUE_IN_KG);

        assertTrue(smallerWeight.isSmallerThan(weight));
    }

    @Test
    public void givenMoreWeight_whenAddingWeight_thenReturnWeightWithAddedWeight() {
        int expectedWeight = VALUE_IN_KG + ADDED_VALUES_IN_KG;
        Weight addedWeight = Weight.fromKg(ADDED_VALUES_IN_KG);

        weight = weight.addWeight(addedWeight);

        assertEquals(expectedWeight, weight.getWeightKg());
    }

    @Test
    public void givenDifferentClassObject_whenEquals_thenNotEquals() {
        Object nullObject = new Object();

        assertNotEquals(weight, nullObject);
    }

    @Test
    public void givenSameObject_whenEquals_thenReturnTrue() {
        assertEquals(weight, weight);
    }

    @Test
    public void givenTwoWeightWithSameValue_whenEquals_thenReturnTrue() {
        Weight sameWeight = Weight.fromKg(VALUE_IN_KG);

        assertEquals(weight, sameWeight);
    }
}
