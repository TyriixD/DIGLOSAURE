package ca.ulaval.glo4002.game.application.breed.dtos;

import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;

public class BreedingResponse {
    private final SpeciesInfo species;
    private final GenderInfo gender;

    public BreedingResponse(SpeciesInfo species, GenderInfo gender) {
        this.species = species;
        this.gender = gender;
    }

    public SpeciesInfo getSpecies() {
        return species;
    }

    public GenderInfo getGender() {
        return gender;
    }
}
