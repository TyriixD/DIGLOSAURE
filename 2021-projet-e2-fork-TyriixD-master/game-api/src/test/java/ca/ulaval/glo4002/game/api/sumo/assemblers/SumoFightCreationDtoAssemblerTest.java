package ca.ulaval.glo4002.game.api.sumo.assemblers;

import ca.ulaval.glo4002.game.api.sumo.dtos.SumoFightRequest;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightCreationDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumoFightCreationDtoAssemblerTest {
    private SumoFightCreationDtoAssembler sumoFightCreationDtoAssembler;

    @BeforeEach
    public void setUpDinosaurResource() {
        sumoFightCreationDtoAssembler = new SumoFightCreationDtoAssembler();
    }

    @Test
    public void givenValidFightRequest_whenToCreationDto_thenCreationDtoDinosaurNamesAreCorrect() {
        String challengerName = "Sophie";
        String challengeeName = "Marilyne";
        SumoFightRequest fightRequest = new SumoFightRequest();
        fightRequest.challenger = challengerName;
        fightRequest.challengee = challengeeName;

        SumoFightCreationDto creationDto = sumoFightCreationDtoAssembler.toCreationDto(fightRequest);

        assertEquals(challengerName, creationDto.getChallengerName());
        assertEquals(challengeeName, creationDto.getChallengeeName());
    }
}
