package ca.ulaval.glo4002.game.api.resources.assemblers;

import ca.ulaval.glo4002.game.api.resources.dtos.CategorizedResourcesResponse;
import ca.ulaval.glo4002.game.api.resources.dtos.ResourcesResponse;
import ca.ulaval.glo4002.game.application.resources.dtos.CategorizedResourcesDto;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;

public class ResourcesResponseAssembler {
    public CategorizedResourcesResponse toCategorizedResponse(CategorizedResourcesDto dto) {
        ResourcesResponse freshResourcesResponse = toResponse(dto.getFresh());
        ResourcesResponse expiredResourcesResponse = toResponse(dto.getExpired());
        ResourcesResponse consumedResourcesResponse = toResponse(dto.getConsumed());

        return new CategorizedResourcesResponse(freshResourcesResponse, expiredResourcesResponse,
                                                consumedResourcesResponse);
    }

    private ResourcesResponse toResponse(ResourcesDto dto) {
        return new ResourcesResponse(dto.getBurgerQuantity(), dto.getSaladQuantity(), dto.getWaterQuantity());
    }
}
