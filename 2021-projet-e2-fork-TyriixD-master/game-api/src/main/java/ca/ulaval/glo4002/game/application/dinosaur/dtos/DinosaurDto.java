package ca.ulaval.glo4002.game.application.dinosaur.dtos;

import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;

public class DinosaurDto {
    private final String name;
    private final Weight weight;
    private final GenderInfo gender;
    private final SpeciesInfo species;

    public DinosaurDto(String name, Weight weight, GenderInfo gender, SpeciesInfo species) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public Weight getWeight() {
        return weight;
    }

    public GenderInfo getGender() {
        return gender;
    }

    public SpeciesInfo getSpecies() {
        return species;
    }
}
