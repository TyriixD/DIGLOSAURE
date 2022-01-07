package ca.ulaval.glo4002.game.infrastructure.external_service.breed.assemblers;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.infrastructure.external_service.breed.dtos.BreedingRequest;

public class BreedingRequestAssembler {
    public BreedingRequest toBreedingRequest(Dinosaur father, Dinosaur mother) {
        return new BreedingRequest(father.getSpecies().name, mother.getSpecies().name);
    }
}
