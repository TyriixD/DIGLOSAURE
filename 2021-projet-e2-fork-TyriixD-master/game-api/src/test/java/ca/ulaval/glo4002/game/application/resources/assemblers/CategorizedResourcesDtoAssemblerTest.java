package ca.ulaval.glo4002.game.application.resources.assemblers;

import ca.ulaval.glo4002.game.application.resources.dtos.CategorizedResourcesDto;
import ca.ulaval.glo4002.game.domain.resources.ResourceInfo;
import ca.ulaval.glo4002.game.domain.resources.ResourceState;
import ca.ulaval.glo4002.game.domain.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategorizedResourcesDtoAssemblerTest {
    private CategorizedResourcesDtoAssembler categorizedResourcesAssembler;
    private List<Resources> resources;

    @BeforeEach
    public void setUp() {
        categorizedResourcesAssembler = new CategorizedResourcesDtoAssembler();
        resources = new ArrayList<>();
    }

    @Test
    public void givenAResourceListWithFreshBurgers_whenToDto_thenCategorizedResourcesDtoContainsFreshBurgers() {
        int someQuantity = 42;
        resources.add(new Resources(ResourceInfo.BURGER, ResourceState.FRESH, someQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        assertEquals(someQuantity, resourcesDto.getFresh().burgerQuantity);
    }

    @Test
    public void givenAResourceListWithFreshBurgersSpreadAcrossMultipleResources_whenToDto_thenCategorizedResourcesDtoContainsCorrectAmountOfBurgers() {
        int firstQuantity = 12;
        int secondQuantity = 21;
        resources.add(new Resources(ResourceInfo.BURGER, ResourceState.FRESH, firstQuantity));
        resources.add(new Resources(ResourceInfo.BURGER, ResourceState.FRESH, secondQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        int totalQuantity = 33;
        assertEquals(totalQuantity, resourcesDto.getFresh().burgerQuantity);
    }

    @Test
    public void givenAResourceListWithFreshSalad_whenToDto_thenCategorizedResourcesDtoContainsFreshSalad() {
        int someQuantity = 42;
        resources.add(new Resources(ResourceInfo.SALAD, ResourceState.FRESH, someQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        assertEquals(someQuantity, resourcesDto.getFresh().saladQuantity);
    }

    @Test
    public void givenAResourceListWithFreshWater_whenToDto_thenCategorizedResourcesDtoContainsFreshWater() {
        int someQuantity = 28;
        resources.add(new Resources(ResourceInfo.WATER, ResourceState.FRESH, someQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        assertEquals(someQuantity, resourcesDto.getFresh().waterQuantity);
    }

    @Test
    public void givenAResourceListWithConsumedBurgers_whenToDto_thenCategorizedResourcesDtoContainsConsumedBurgers() {
        int someQuantity = 23;
        resources.add(new Resources(ResourceInfo.BURGER, ResourceState.CONSUMED, someQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        assertEquals(someQuantity, resourcesDto.getConsumed().burgerQuantity);
    }

    @Test
    public void givenAResourceListWithConsumedSalad_whenToDto_thenCategorizedResourcesDtoContainsConsumedSalad() {
        int someQuantity = 52;
        resources.add(new Resources(ResourceInfo.SALAD, ResourceState.CONSUMED, someQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        assertEquals(someQuantity, resourcesDto.getConsumed().saladQuantity);
    }

    @Test
    public void givenAResourceListWithConsumedWater_whenToDto_thenCategorizedResourcesDtoContainsConsumedWater() {
        int someQuantity = 42;
        resources.add(new Resources(ResourceInfo.WATER, ResourceState.CONSUMED, someQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        assertEquals(someQuantity, resourcesDto.getConsumed().waterQuantity);
    }

    @Test
    public void givenAResourceListWithExpiredBurgers_whenToDto_thenCategorizedResourcesDtoContainsExpiredBurgers() {
        int someQuantity = 13;
        resources.add(new Resources(ResourceInfo.BURGER, ResourceState.EXPIRED, someQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        assertEquals(someQuantity, resourcesDto.getExpired().burgerQuantity);
    }

    @Test
    public void givenAResourceListWithExpiredSalad_whenToDto_thenCategorizedResourcesDtoContainsExpiredSalad() {
        int someQuantity = 15;
        resources.add(new Resources(ResourceInfo.SALAD, ResourceState.EXPIRED, someQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        assertEquals(someQuantity, resourcesDto.getExpired().saladQuantity);
    }

    @Test
    public void givenAResourceListWithExpiredWater_whenToDto_thenCategorizedResourcesDtoContainsExpiredWater() {
        int someQuantity = 23;
        resources.add(new Resources(ResourceInfo.WATER, ResourceState.EXPIRED, someQuantity));

        CategorizedResourcesDto resourcesDto = categorizedResourcesAssembler.toDto(resources);

        assertEquals(someQuantity, resourcesDto.getExpired().waterQuantity);
    }
}
