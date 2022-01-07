package ca.ulaval.glo4002.game.api.breed.assemblers;

import ca.ulaval.glo4002.game.api.breed.dtos.DinosaurBreedingRequest;
import ca.ulaval.glo4002.game.application.breed.dtos.DinosaurBreedingDto;

public class DinosaurBreedingDtoAssembler {
    public DinosaurBreedingDto toDto(DinosaurBreedingRequest request) {
        return new DinosaurBreedingDto(request.getName(), request.getFatherName(), request.getMotherName());
    }
}
