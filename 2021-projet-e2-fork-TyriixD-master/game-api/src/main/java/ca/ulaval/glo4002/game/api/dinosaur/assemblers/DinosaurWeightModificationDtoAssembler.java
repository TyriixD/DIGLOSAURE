package ca.ulaval.glo4002.game.api.dinosaur.assemblers;

import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurWeightModificationRequest;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurWeightModificationDto;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;

public class DinosaurWeightModificationDtoAssembler {
    public DinosaurWeightModificationDto toDto(String name, DinosaurWeightModificationRequest creationRequest) {
        return new DinosaurWeightModificationDto(name, Weight.fromKg(creationRequest.getWeight()));
    }
}
