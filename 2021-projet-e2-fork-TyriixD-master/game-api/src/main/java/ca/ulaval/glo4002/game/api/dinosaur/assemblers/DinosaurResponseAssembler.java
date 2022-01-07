package ca.ulaval.glo4002.game.api.dinosaur.assemblers;

import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurResponse;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;

import java.util.List;
import java.util.stream.Collectors;

public class DinosaurResponseAssembler {
    public List<DinosaurResponse> toResponse(List<DinosaurDto> dinosaurDtos) {
        return dinosaurDtos.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public DinosaurResponse toResponse(DinosaurDto dinosaurDto) {
        return new DinosaurResponse(dinosaurDto.getName(), dinosaurDto.getWeight().getWeightKg(),
                                    dinosaurDto.getGender().gender, dinosaurDto.getSpecies().name);
    }
}
