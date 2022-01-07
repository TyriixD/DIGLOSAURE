package ca.ulaval.glo4002.game.domain.dinosaur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrengthTest {
    private static final int STRENGTH_VALUE = 2;
    private static final int SMALLER_VALUE = 1;
    private static final int GREATER_VALUE = 3;
    private Strength strength;

    @BeforeEach
    void setUp() {
        strength = new Strength(STRENGTH_VALUE);
    }

    @Test
    public void givenASmallerStrength_whenCallingIsSmallerThan_thenReturnTrue() {
        Strength smallerStrength = new Strength(SMALLER_VALUE);

        assertTrue(smallerStrength.isSmallerThan(strength));
    }

    @Test
    public void givenAGreaterStrength_whenCallingIsGreaterThan_thenReturnTrue() {
        Strength greaterStrength = new Strength(GREATER_VALUE);

        assertTrue(greaterStrength.isGreaterThan(strength));
    }

    @Test
    public void givenDifferentClassObject_whenEquals_thenNotEquals() {
        Object nullObject = new Object();

        assertNotEquals(strength, nullObject);
    }

    @Test
    public void givenSameObject_whenEquals_thenReturnTrue() {
        assertEquals(strength, strength);
    }

    @Test
    public void givenTwoStrengthWithSameValue_whenEquals_thenReturnTrue() {
        Strength sameStrength = new Strength(STRENGTH_VALUE);

        assertEquals(this.strength, sameStrength);
    }
}
