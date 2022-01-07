package ca.ulaval.glo4002.game.domain.resources;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PantryTest {
    private Pantry pantry;
    private RationStrategy rationStrategyMock;

    @BeforeEach
    public void setupPantry() {
        rationStrategyMock = mock(RationStrategy.class);
        pantry = new Pantry(rationStrategyMock);
    }

    @Test
    public void givenResources_whenAddingTheseResources_thenThesesResourcesAreAddedAsFreshResources() {
        int quantity = 50;
        Resources resources = new Resources(ResourceInfo.WATER, ResourceState.FRESH, quantity);

        pantry.addResources(resources);

        List<Resources> resourcesList = pantry.getAllResources();
        assertEquals(1, resourcesList.size());
    }

    @Test
    public void givenResourcesNeeds_whenPreparingLunchBox_thenPrepareLunchBoxIsCalled() {
        int validQuantity = 50;
        ResourcesNeeds resourcesNeeds = new ResourcesNeeds(validQuantity, validQuantity, validQuantity, validQuantity);

        pantry.prepareLunchBox(resourcesNeeds);

        verify(rationStrategyMock).prepareLunchBox(any(), any(), any());
    }

    @Test
    public void givenExpiredResourcesWithFreshResources_whenRemovingExpiredResources_thenExpiredResourcesAreRemovedFromFreshResources() {
        int quantity = 50;
        Resources resources = new Resources(ResourceInfo.WATER, ResourceState.FRESH, quantity);
        pantry.addResources(resources);
        decrementShelfLifeUntilExpired(resources);

        pantry.removeExpiredResources();

        List<Resources> resourcesList = pantry.getAllResources();
        assertTrue(resourcesList.get(0).isExpired());
    }

    private void decrementShelfLifeUntilExpired(Resources resources) {
        while (resources.getState().equals(ResourceState.FRESH)) {
            resources.decrementShelfLife();
        }
    }

    @Test
    public void whenResettingPantry_thenNoResourcesRemaining() {
        pantry.reset();

        assertEquals(0, pantry.getAllResources().size());
    }

}
