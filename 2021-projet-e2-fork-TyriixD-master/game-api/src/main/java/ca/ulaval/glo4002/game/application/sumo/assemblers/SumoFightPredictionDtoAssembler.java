package ca.ulaval.glo4002.game.application.sumo.assemblers;

import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightPredictionDto;
import ca.ulaval.glo4002.game.domain.sumo.SumoFightPrediction;

public class SumoFightPredictionDtoAssembler {
    public SumoFightPredictionDto toPredictionDto(SumoFightPrediction sumoFightPrediction) {
        return new SumoFightPredictionDto(sumoFightPrediction.getPredictedWinner());
    }
}
