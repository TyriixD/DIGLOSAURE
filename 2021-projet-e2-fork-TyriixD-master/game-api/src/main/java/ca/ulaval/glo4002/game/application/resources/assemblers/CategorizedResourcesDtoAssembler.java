package ca.ulaval.glo4002.game.application.resources.assemblers;

import ca.ulaval.glo4002.game.application.resources.dtos.CategorizedResourcesDto;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;
import ca.ulaval.glo4002.game.domain.resources.ResourceInfo;
import ca.ulaval.glo4002.game.domain.resources.ResourceState;
import ca.ulaval.glo4002.game.domain.resources.Resources;

import java.util.List;

public class CategorizedResourcesDtoAssembler {
    public CategorizedResourcesDto toDto(List<Resources> resources) {
        CategorizedResourcesDto categorizedResourcesDto = initializeEmptyCategorizedResourceDto();

        for (Resources resource : resources) {
            ResourceState state = resource.getState();
            ResourceInfo type = resource.getInfo();
            int quantity = resource.getQuantity();

            switch (state) {
                case FRESH:
                    updateResourceDtoQuantity(type, quantity, categorizedResourcesDto.getFresh());
                    break;
                case EXPIRED:
                    updateResourceDtoQuantity(type, quantity, categorizedResourcesDto.getExpired());
                    break;
                case CONSUMED:
                    updateResourceDtoQuantity(type, quantity, categorizedResourcesDto.getConsumed());
                    break;
            }
        }

        return categorizedResourcesDto;
    }

    private void updateResourceDtoQuantity(ResourceInfo type, int quantity, ResourcesDto resourcesDto) {
        switch (type) {
            case BURGER:
                resourcesDto.burgerQuantity += quantity;
                break;
            case SALAD:
                resourcesDto.saladQuantity += quantity;
                break;
            case WATER:
                resourcesDto.waterQuantity += quantity;
                break;
        }
    }

    private CategorizedResourcesDto initializeEmptyCategorizedResourceDto() {
        ResourcesDto burgerDto = new ResourcesDto(0, 0, 0);
        ResourcesDto saladDto = new ResourcesDto(0, 0, 0);
        ResourcesDto waterDto = new ResourcesDto(0, 0, 0);
        return new CategorizedResourcesDto(burgerDto, saladDto, waterDto);
    }
}
