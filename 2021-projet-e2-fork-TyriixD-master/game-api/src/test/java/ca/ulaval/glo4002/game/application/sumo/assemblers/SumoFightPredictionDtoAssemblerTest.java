package ca.ulaval.glo4002.game.application.sumo.assemblers;

import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightPredictionDto;
import ca.ulaval.glo4002.game.domain.sumo.SumoFightPrediction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumoFightPredictionDtoAssemblerTest {
    private SumoFightPredictionDtoAssembler SumoFightPredictionDtoAssembler;
    private String winnerName;

    @BeforeEach
    public void setUpSumoPredictionDtoAssembler() {
        SumoFightPredictionDtoAssembler = new SumoFightPredictionDtoAssembler();
        winnerName = "Ludivine";
    }

    @Test
    public void givenATiedFightPrediction_whenToPredictionDto_thenDtoPredictedWinnerIsAnEmptyString() {
        SumoFightPrediction fightPrediction = givenTiedFightPrediction();

        SumoFightPredictionDto dto = SumoFightPredictionDtoAssembler.toPredictionDto(fightPrediction);

        String tieWinner = "tie";
        assertEquals(tieWinner, dto.getPredictedWinner());
    }

    @Test
    public void givenAWinningFightPrediction_whenToPredictionDto_thenDtoPredictedWinnerIsWinnerName() {
        SumoFightPrediction fightPrediction = givenWinningFightPrediction();

        SumoFightPredictionDto dto = SumoFightPredictionDtoAssembler.toPredictionDto(fightPrediction);

        assertEquals(winnerName, dto.getPredictedWinner());
    }

    private SumoFightPrediction givenTiedFightPrediction() {
        return new SumoFightPrediction("tie");
    }

    private SumoFightPrediction givenWinningFightPrediction() {
        return new SumoFightPrediction(winnerName);
    }
}
