package ca.ulaval.glo4002.game.domain.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResourcesTest {
    private final int initialQuantity = 10;
    private Resources resources;

    @BeforeEach
    public void setUpResource() {
        resources = new Resources(ResourceInfo.SALAD, ResourceState.FRESH, initialQuantity);
    }

    private void decrementShelfLifeByCertainAmount(int amount) {
        for (int i = 0; i < amount; i++) {
            resources.decrementShelfLife();
        }
    }

    @Test
    public void givenInsufficientResources_whenConsuming_thenConsumedQuantityIsEqualToAvailableQuantity() {
        int moreThanAvailableQuantity = initialQuantity + 1;

        int resourceConsumed = resources.consume(moreThanAvailableQuantity);

        assertEquals(initialQuantity, resourceConsumed);
    }

    @Test
    public void whenConsumingLessOrEqualQuantityThanAvailable_thenQuantityConsumedIsEqualToQuantityToConsume() {
        int quantityToConsume = initialQuantity - 1;
        int resourceConsumed = resources.consume(quantityToConsume);

        assertEquals(quantityToConsume, resourceConsumed);
    }

    @Test
    public void whenDecrementingShelfLifeUntilExpiration_thenResourceIsExpired() {
        decrementShelfLifeByCertainAmount(ResourceInfo.SALAD.shelfLife + 1);

        assertTrue(resources.isExpired());
    }

    @Test
    public void whenDecrementingShelfLifeLessThanExpirationTime_thenResourceIsNotExpired() {
        decrementShelfLifeByCertainAmount(ResourceInfo.SALAD.shelfLife);

        assertFalse(resources.isExpired());
    }

    @Test
    public void whenConsumingTheQuantityAvailable_thenResourceIsEmpty() {
        resources.consume(initialQuantity);

        assertTrue(resources.isEmpty());
    }

    @Test
    public void whenConsumingLessThanQuantityAvailable_thenResourceIsNotEmpty() {
        resources.consume(initialQuantity - 1);

        assertFalse(resources.isEmpty());
    }

}
