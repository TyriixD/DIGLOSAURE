package ca.ulaval.glo4002.game.api.resources.assemblers;

import ca.ulaval.glo4002.game.api.resources.dtos.ResourcesCreationRequest;
import ca.ulaval.glo4002.game.api.resources.exceptions.InvalidResourceQuantityException;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesCreationDto;

public class ResourcesCreationDtoAssembler {
    public ResourcesCreationDto toDto(ResourcesCreationRequest request) {
        validateResourceRequest(request);

        return new ResourcesCreationDto(request.getQtyBurger(), request.getQtySalad(), request.getQtyWater());
    }

    private void validateResourceRequest(ResourcesCreationRequest request) {
        validateQuantity(request.getQtyBurger());
        validateQuantity(request.getQtySalad());
        validateQuantity(request.getQtyWater());
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new InvalidResourceQuantityException();
        }
    }
}
