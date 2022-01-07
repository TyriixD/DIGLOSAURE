package ca.ulaval.glo4002.game.application.dinosaur.assemblers;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

import java.util.List;
import java.util.stream.Collectors;

public class DinosaurDtoAssembler {
    public List<DinosaurDto> toDto(List<Dinosaur> dinosaurs) {
        return dinosaurs.stream().map(this::toDto).collect(Collectors.toList());
    }

    public DinosaurDto toDto(Dinosaur dinosaur) {
        return new DinosaurDto(dinosaur.getName(), dinosaur.getWeight(), dinosaur.getGender(), dinosaur.getSpecies());
    }
}
