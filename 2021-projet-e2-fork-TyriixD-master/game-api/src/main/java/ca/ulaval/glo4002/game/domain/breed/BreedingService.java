package ca.ulaval.glo4002.game.domain.breed;

import ca.ulaval.glo4002.game.application.breed.dtos.BreedingResponse;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

public interface BreedingService {
    BreedingResponse breed(Dinosaur father, Dinosaur mother);
}
