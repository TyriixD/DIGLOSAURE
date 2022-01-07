package ca.ulaval.glo4002.game.application.dinosaur.dtos;

import ca.ulaval.glo4002.game.domain.dinosaur.Weight;

public class DinosaurWeightModificationDto {
    private final String name;
    private final Weight weightDifference;

    public DinosaurWeightModificationDto(String name, Weight weightDifference) {
        this.name = name;
        this.weightDifference = weightDifference;
    }

    public String getName() {
        return name;
    }

    public Weight getWeightDifference() {
        return weightDifference;
    }
}
