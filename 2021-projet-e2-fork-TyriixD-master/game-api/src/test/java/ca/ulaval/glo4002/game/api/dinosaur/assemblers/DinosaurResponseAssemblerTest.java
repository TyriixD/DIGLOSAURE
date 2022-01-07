package ca.ulaval.glo4002.game.api.dinosaur.assemblers;

import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurResponse;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DinosaurResponseAssemblerTest {
    private DinosaurResponseAssembler dinosaurResponseAssembler;
    private String dinosaurName;
    private Weight dinosaurWeight;
    private GenderInfo dinosaurGenderInfo;
    private SpeciesInfo dinosaurSpeciesInfo;
    private DinosaurDto dinosaurDto;

    @BeforeEach
    public void setDinosaurResponseAssembler() {
        dinosaurResponseAssembler = new DinosaurResponseAssembler();
        dinosaurName = "Bob";
        dinosaurWeight = Weight.fromKg(50);
        dinosaurGenderInfo = GenderInfo.MALE;
        dinosaurSpeciesInfo = SpeciesInfo.VELOCIRAPTOR;
        dinosaurDto = new DinosaurDto(dinosaurName, dinosaurWeight, dinosaurGenderInfo, dinosaurSpeciesInfo);
    }

    @Test
    public void givenListContainingDinosaurDtos_whenToResponse_thenConvertIntoDinosaurResponseList() {
        List<DinosaurDto> dinosaurResponseList = new ArrayList<>();
        dinosaurResponseList.add(dinosaurDto);

        List<DinosaurResponse> dinosaurResponses = dinosaurResponseAssembler.toResponse(dinosaurResponseList);

        assertEquals(1, dinosaurResponses.size());
    }

    @Test
    public void whenToResponse_thenSendNameToDinosaurResponse() {
        DinosaurResponse dinosaurResponse = dinosaurResponseAssembler.toResponse(dinosaurDto);

        assertEquals(dinosaurName, dinosaurResponse.name);
    }

    @Test
    public void whenToResponse_thenSendWeightToDinosaurResponse() {
        DinosaurResponse dinosaurResponse = dinosaurResponseAssembler.toResponse(dinosaurDto);

        assertEquals(dinosaurWeight.getWeightKg(), dinosaurResponse.weight);
    }

    @Test
    public void whenToResponse_thenSendGenderInfoToDinosaurResponse() {
        DinosaurResponse dinosaurResponse = dinosaurResponseAssembler.toResponse(dinosaurDto);

        assertEquals(dinosaurGenderInfo.gender, dinosaurResponse.gender);
    }

    @Test
    public void whenToResponse_thenSendSpeciesInfoToDinosaurResponse() {
        DinosaurResponse dinosaurResponse = dinosaurResponseAssembler.toResponse(dinosaurDto);

        assertEquals(dinosaurSpeciesInfo.name, dinosaurResponse.species);
    }
}
