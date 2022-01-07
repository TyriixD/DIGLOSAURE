package ca.ulaval.glo4002.game.api.breed.assemblers;

import ca.ulaval.glo4002.game.api.breed.assemblers.DinosaurBreedingDtoAssembler;
import ca.ulaval.glo4002.game.api.breed.dtos.DinosaurBreedingRequest;
import ca.ulaval.glo4002.game.application.breed.dtos.DinosaurBreedingDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DinosaurBreedingDtoAssemblerTest {
    private DinosaurBreedingDtoAssembler dinosaurBreedingDtoAssembler;
    private DinosaurBreedingRequest dinosaurBreedingRequest;
    private String dinosaurName;
    private String fatherName;
    private String motherName;

    @BeforeEach
    public void setupDinosaurBreedingDtoAssembler() {
        dinosaurBreedingDtoAssembler = new DinosaurBreedingDtoAssembler();
        dinosaurBreedingRequest = new DinosaurBreedingRequest();
        dinosaurName = "Karlito";
        fatherName = "Karl";
        motherName = "Karline";
        buildDinosaurBreedingRequest();
    }

    private void buildDinosaurBreedingRequest() {
        dinosaurBreedingRequest.name = dinosaurName;
        dinosaurBreedingRequest.fatherName = fatherName;
        dinosaurBreedingRequest.motherName = motherName;
    }

    @Test
    public void whenToDto_thenSendNameToDinosaurBreedingDto() {
        DinosaurBreedingDto dinosaurBreedingDto = dinosaurBreedingDtoAssembler.toDto(dinosaurBreedingRequest);

        assertEquals(dinosaurName, dinosaurBreedingDto.getName());
    }

    @Test
    public void whenToDto_thenSendFatherNameToDinosaurBreedingDto() {
        DinosaurBreedingDto dinosaurBreedingDto = dinosaurBreedingDtoAssembler.toDto(dinosaurBreedingRequest);

        assertEquals(fatherName, dinosaurBreedingDto.getFatherName());
    }

    @Test
    public void whenToDto_thenSendMotherNameToDinosaurBreedingDto() {
        DinosaurBreedingDto dinosaurBreedingDto = dinosaurBreedingDtoAssembler.toDto(dinosaurBreedingRequest);

        assertEquals(motherName, dinosaurBreedingDto.getMotherName());
    }
}
