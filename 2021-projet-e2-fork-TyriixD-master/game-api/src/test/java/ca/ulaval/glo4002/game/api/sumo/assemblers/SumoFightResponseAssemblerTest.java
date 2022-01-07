package ca.ulaval.glo4002.game.api.sumo.assemblers;

import ca.ulaval.glo4002.game.api.sumo.dtos.SumoFightResponse;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightPredictionDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumoFightResponseAssemblerTest {
    private SumoFightResponseAssembler sumoFightResponseAssembler;

    @BeforeEach
    public void setUpDinosaurResource() {
        sumoFightResponseAssembler = new SumoFightResponseAssembler();
    }

    @Test
    public void givenATiedFightPrediction_whenToResponse_thenResponsePredictedWinnerIsTie() {
        SumoFightPredictionDto predictionDto = new SumoFightPredictionDto("tie");

        SumoFightResponse response = sumoFightResponseAssembler.toResponse(predictionDto);

        String tiedGameWinner = "tie";
        assertEquals(tiedGameWinner, response.getPredictedWinner());
    }

    @Test
    public void givenWinningFightPrediction_whenToResponse_thenResponsePredictedWinnerIsWinner() {
        String winnerName = "Ludivine";
        SumoFightPredictionDto predictionDto = new SumoFightPredictionDto(winnerName);

        SumoFightResponse response = sumoFightResponseAssembler.toResponse(predictionDto);

        assertEquals(winnerName, response.getPredictedWinner());
    }
}
