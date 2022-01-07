package ca.ulaval.glo4002.game.infrastructure.external_service.breed.assemblers;

import ca.ulaval.glo4002.game.application.breed.dtos.BreedingResponse;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.infrastructure.external_service.breed.dtos.BreedingResult;

public class BreedingResponseAssembler {
    public BreedingResponse toBreedingResponse(BreedingResult result) {
        return new BreedingResponse(SpeciesInfo.getSpeciesInfo(result.getOffspring()),
                                    GenderInfo.getGenderInfo(result.getGender()));
    }
}
