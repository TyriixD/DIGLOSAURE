package ca.ulaval.glo4002.game.api.resources.assembler;

import ca.ulaval.glo4002.game.api.resources.assemblers.ResourcesResponseAssembler;
import ca.ulaval.glo4002.game.api.resources.dtos.CategorizedResourcesResponse;
import ca.ulaval.glo4002.game.application.resources.dtos.CategorizedResourcesDto;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourcesResponseAssemblerTest {
    private final int freshResourcesQuantity = 50;
    private final int consumedResourcesQuantity = 45;
    private final int expiredResourcesQuantity = 40;
    private ResourcesResponseAssembler resourcesResponseAssembler;
    private CategorizedResourcesDto categorizedResourcesDto;

    @BeforeEach
    public void setupResourcesResponseAssembler() {
        resourcesResponseAssembler = new ResourcesResponseAssembler();
        prepareCategorizedResourcesDto();
    }

    private void prepareCategorizedResourcesDto() {
        ResourcesDto freshResourcesDto = new ResourcesDto(freshResourcesQuantity, freshResourcesQuantity,
                                                          freshResourcesQuantity);
        ResourcesDto consumedResourcesDto = new ResourcesDto(consumedResourcesQuantity, consumedResourcesQuantity,
                                                             consumedResourcesQuantity);
        ResourcesDto expiredResourcesDto = new ResourcesDto(expiredResourcesQuantity, expiredResourcesQuantity,
                                                            expiredResourcesQuantity);
        categorizedResourcesDto = new CategorizedResourcesDto(freshResourcesDto, expiredResourcesDto,
                                                              consumedResourcesDto);
    }

    @Test
    public void whenToCategorizedResponse_thenSendFreshResourcesToCategorizedResourcesResponse() {
        CategorizedResourcesResponse categorizedResourcesResponse = resourcesResponseAssembler.toCategorizedResponse(
            categorizedResourcesDto);

        assertEquals(freshResourcesQuantity, categorizedResourcesResponse.fresh.qtyBurger);
    }

    @Test
    public void whenToCategorizedResponse_thenSendConsumedResourcesToCategorizedResourcesResponse() {
        CategorizedResourcesResponse categorizedResourcesResponse = resourcesResponseAssembler.toCategorizedResponse(
            categorizedResourcesDto);

        assertEquals(consumedResourcesQuantity, categorizedResourcesResponse.consumed.qtyBurger);
    }

    @Test
    public void whenToCategorizedResponse_thenSendExpiredResourcesToCategorizedResourcesResponse() {
        CategorizedResourcesResponse categorizedResourcesResponse = resourcesResponseAssembler.toCategorizedResponse(
            categorizedResourcesDto);

        assertEquals(expiredResourcesQuantity, categorizedResourcesResponse.expired.qtyBurger);
    }
}
