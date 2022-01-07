package ca.ulaval.glo4002.game.application.dinosaur.assemblers;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.DrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.EatingBehaviour;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class DinosaurDtoAssemblerTest {
    private final String name = "Bruce";
    private final Weight weight = Weight.fromKg(1000);
    private final GenderInfo gender = GenderInfo.MALE;
    private final SpeciesInfo species = SpeciesInfo.ALLOSAURUS;
    private final EatingBehaviour eatingBehaviour = mock(EatingBehaviour.class);
    private final DrinkingBehaviour drinkingBehaviour = mock(DrinkingBehaviour.class);
    private final DinosaurDtoAssembler dinosaurDtoAssembler = new DinosaurDtoAssembler();

    private Dinosaur givenADinosaur() {
        return new Dinosaur(name, weight, gender, species, eatingBehaviour, drinkingBehaviour);
    }

    private List<Dinosaur> givenManyDinosaurs() {
        List<Dinosaur> dinosaurs = new ArrayList<>();
        dinosaurs.add(givenADinosaur());
        dinosaurs.add(givenADinosaur());
        return dinosaurs;
    }

    @Test
    void givenADinosaur_whenToDto_thenReturnADinosaurDtoWithSameName() {
        Dinosaur aDinosaur = givenADinosaur();

        DinosaurDto dinosaurDto = dinosaurDtoAssembler.toDto(aDinosaur);

        assertEquals(name, dinosaurDto.getName());
    }

    @Test
    void givenADinosaur_whenToDto_thenReturnADinosaurDtoWithSameWeight() {
        Dinosaur aDinosaur = givenADinosaur();

        DinosaurDto dinosaurDto = dinosaurDtoAssembler.toDto(aDinosaur);

        assertEquals(weight, dinosaurDto.getWeight());
    }

    @Test
    void givenADinosaur_whenToDto_thenReturnADinosaurDtoWithSameGenderInfo() {
        Dinosaur aDinosaur = givenADinosaur();

        DinosaurDto dinosaurDto = dinosaurDtoAssembler.toDto(aDinosaur);

        assertEquals(gender, dinosaurDto.getGender());
    }

    @Test
    void givenADinosaur_whenToDto_thenReturnadinosaurDtoWithSameSpeciesInfo() {
        Dinosaur aDinosaur = givenADinosaur();

        DinosaurDto dinosaurDto = dinosaurDtoAssembler.toDto(aDinosaur);

        assertEquals(species, dinosaurDto.getSpecies());
    }

    @Test
    void givenManyDinosaurs_whenToDto_thenReturnManyDinosaurDtosWithSameName() {
        List<Dinosaur> dinosaurs = givenManyDinosaurs();

        List<DinosaurDto> dinosaurDtos = dinosaurDtoAssembler.toDto(dinosaurs);

        DinosaurDto firstDinosaurDto = dinosaurDtos.get(0);
        DinosaurDto secondDinosaurDto = dinosaurDtos.get(1);
        assertEquals(name, firstDinosaurDto.getName());
        assertEquals(name, secondDinosaurDto.getName());
    }

    @Test
    void givenManyDinosaurs_whenToDto_thenReturnManyDinosaurDtosWithSameWeight() {
        List<Dinosaur> dinosaurs = givenManyDinosaurs();

        List<DinosaurDto> dinosaurDtos = dinosaurDtoAssembler.toDto(dinosaurs);

        DinosaurDto firstDinosaurDto = dinosaurDtos.get(0);
        DinosaurDto secondDinosaurDto = dinosaurDtos.get(1);
        assertEquals(weight, firstDinosaurDto.getWeight());
        assertEquals(weight, secondDinosaurDto.getWeight());
    }

    @Test
    void givenManyDinosaurs_whenToDto_thenReturnManyDinosaurDtosWithSameGenderInfo() {
        List<Dinosaur> dinosaurs = givenManyDinosaurs();

        List<DinosaurDto> dinosaurDtos = dinosaurDtoAssembler.toDto(dinosaurs);

        DinosaurDto firstDinosaurDto = dinosaurDtos.get(0);
        DinosaurDto secondDinosaurDto = dinosaurDtos.get(1);
        assertEquals(gender, firstDinosaurDto.getGender());
        assertEquals(gender, secondDinosaurDto.getGender());
    }

    @Test
    void givenManyDinosaurs_whenToDto_thenReturnManyDinosaurDtosWithSameSpeciesInfo() {
        List<Dinosaur> dinosaurs = givenManyDinosaurs();

        List<DinosaurDto> dinosaurDtos = dinosaurDtoAssembler.toDto(dinosaurs);

        DinosaurDto firstDinosaurDto = dinosaurDtos.get(0);
        DinosaurDto secondDinosaurDto = dinosaurDtos.get(1);
        assertEquals(species, firstDinosaurDto.getSpecies());
        assertEquals(species, secondDinosaurDto.getSpecies());
    }
}
