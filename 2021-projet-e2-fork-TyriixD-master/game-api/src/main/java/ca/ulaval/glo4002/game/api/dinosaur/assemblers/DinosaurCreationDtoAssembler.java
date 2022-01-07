package ca.ulaval.glo4002.game.api.dinosaur.assemblers;

import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurCreationRequest;
import ca.ulaval.glo4002.game.api.dinosaur.exceptions.InvalidWeightException;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;

public class DinosaurCreationDtoAssembler {
    private final int minimumWeight = 100;

    public DinosaurCreationDto toDto(DinosaurCreationRequest creationRequest) {
        if (creationRequest.getWeight() < minimumWeight) {
            throw new InvalidWeightException();
        }

        return new DinosaurCreationDto(creationRequest.getName(), Weight.fromKg(creationRequest.getWeight()),
                                       GenderInfo.getGenderInfo(creationRequest.getGender()),
                                       SpeciesInfo.getSpeciesInfo(creationRequest.getSpecies()));
    }
}
