package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesCreationDto;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourcesFactoryTest {
    private ResourcesCreationDto resourcesCreationDto;
    private ResourcesFactory resourcesFactory;
    private final int resourceQuantity = 50;
    private final int expectedQuantity = 1;

    @BeforeEach
    public void setUpResourceFactory() {
        resourcesFactory = new ResourcesFactory();
    }

    @Test
    public void givenAResourceDtoWithOnlyBurgers_whenCreatingResources_thenASingleResourceIsCreated() {
        resourcesCreationDto = new ResourcesCreationDto(resourceQuantity, 0, 0);

        List<Resources> resources = resourcesFactory.create(resourcesCreationDto.getBurgerQuantity(), 0, 0);

        assertEquals(expectedQuantity, resources.size());
    }

    @Test
    public void givenAResourceDtoWithOnlyBurgers_whenCreatingResources_thenBurgerResourceIsCreated() {
        resourcesCreationDto = new ResourcesCreationDto(resourceQuantity, 0, 0);

        List<Resources> resources = resourcesFactory.create(resourcesCreationDto.getBurgerQuantity(), 0, 0);

        assertEquals("burger", resources.get(0).getInfo().type);
    }

    @Test
    public void givenAResourceDtoWithOnlySalads_whenCreatingResources_thenASingleResourceIsCreated() {
        resourcesCreationDto = new ResourcesCreationDto(0, resourceQuantity, 0);

        List<Resources> resources = resourcesFactory.create(0, resourcesCreationDto.getSaladQuantity(), 0);

        assertEquals(expectedQuantity, resources.size());
    }

    @Test
    public void givenAResourceDtoWithOnlySalads_whenCreatingResources_thenSaladResourceIsCreated() {
        resourcesCreationDto = new ResourcesCreationDto(0, resourceQuantity, 0);

        List<Resources> resources = resourcesFactory.create(0, resourcesCreationDto.getSaladQuantity(), 0);

        assertEquals("salad", resources.get(0).getInfo().type);
    }

    @Test
    public void givenAResourceDtoWithOnlyWater_whenCreatingResources_thenASingleResourceIsCreated() {
        resourcesCreationDto = new ResourcesCreationDto(0, 0, resourceQuantity);

        List<Resources> resources = resourcesFactory.create(0, 0, resourcesCreationDto.getWaterQuantity());

        assertEquals(expectedQuantity, resources.size());
    }

    @Test
    public void givenAResourceDtoWithOnlyWater_whenCreatingResources_thenWaterResourceIsCreated() {
        resourcesCreationDto = new ResourcesCreationDto(0, 0, resourceQuantity);

        List<Resources> resources = resourcesFactory.create(0, 0, resourcesCreationDto.getWaterQuantity());

        assertEquals("water", resources.get(0).getInfo().type);
    }
}
