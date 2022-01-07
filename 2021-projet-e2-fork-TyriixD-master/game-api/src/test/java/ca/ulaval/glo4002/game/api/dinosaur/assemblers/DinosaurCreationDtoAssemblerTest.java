package ca.ulaval.glo4002.game.api.dinosaur.assemblers;

import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurCreationRequest;
import ca.ulaval.glo4002.game.api.dinosaur.exceptions.InvalidWeightException;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DinosaurCreationDtoAssemblerTest {
    private DinosaurCreationDtoAssembler dinosaurCreationDtoAssembler;
    private int validWeight;
    private int invalidWeight;
    private String name;
    private String gender;
    private String species;
    private DinosaurCreationRequest dinosaurCreationRequest;

    @BeforeEach
    public void setDinosaurCreationRequest() {
        dinosaurCreationDtoAssembler = new DinosaurCreationDtoAssembler();
        validWeight = 100;
        invalidWeight = 99;
        name = "Bob";
        gender = GenderInfo.FEMALE.gender;
        species = SpeciesInfo.VELOCIRAPTOR.name;
        dinosaurCreationRequest = new DinosaurCreationRequest();
        buildDinosaurCreationRequest();
    }

    private void buildDinosaurCreationRequest() {
        dinosaurCreationRequest.name = name;
        dinosaurCreationRequest.weight = validWeight;
        dinosaurCreationRequest.gender = gender;
        dinosaurCreationRequest.species = species;
    }

    @Test
    public void givenWeightLowerThanMaximumWeight_whenToDto_thenThrowInvalidWeightException() {
        DinosaurCreationRequest dinosaurCreationRequestWithInvalidWeight = buildDinosaurCreationRequestWithInvalidWeight();

        assertThrows(InvalidWeightException.class, ()->dinosaurCreationDtoAssembler.toDto(dinosaurCreationRequestWithInvalidWeight));
    }

    private DinosaurCreationRequest buildDinosaurCreationRequestWithInvalidWeight() {
        DinosaurCreationRequest dinosaurCreationRequest = new DinosaurCreationRequest();
        dinosaurCreationRequest.name = name;
        dinosaurCreationRequest.weight = invalidWeight;
        dinosaurCreationRequest.gender = gender;
        dinosaurCreationRequest.species = species;
        return dinosaurCreationRequest;
    }

    @Test
    public void whenToDto_thenSendNameToDinosaurCreationDto() {
        DinosaurCreationDto dinosaurCreationDto = dinosaurCreationDtoAssembler.toDto(dinosaurCreationRequest);

        assertEquals(name, dinosaurCreationDto.getName());
    }

    @Test
    public void whenToDto_thenSendWeightToDinosaurCreationDto() {
        DinosaurCreationDto dinosaurCreationDto = dinosaurCreationDtoAssembler.toDto(dinosaurCreationRequest);

        assertEquals(validWeight, dinosaurCreationDto.getWeight().getWeightKg());
    }

    @Test
    public void whenToDto_thenSendGenderInfoToDinosaurCreationDto() {
        DinosaurCreationDto dinosaurCreationDto = dinosaurCreationDtoAssembler.toDto(dinosaurCreationRequest);

        assertEquals(gender, dinosaurCreationDto.getGender().gender);
    }

    @Test
    public void whenToDto_thenSendSpeciesInfoToDinosaurCreationDto() {
        DinosaurCreationDto dinosaurCreationDto = dinosaurCreationDtoAssembler.toDto(dinosaurCreationRequest);

        assertEquals(species, dinosaurCreationDto.getSpecies().name);
    }
}
