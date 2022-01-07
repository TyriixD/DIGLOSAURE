package ca.ulaval.glo4002.game.api.sumo.assemblers;

import ca.ulaval.glo4002.game.api.sumo.dtos.SumoFightResponse;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightPredictionDto;

public class SumoFightResponseAssembler {
    public SumoFightResponse toResponse(SumoFightPredictionDto predictionDto) {
        return new SumoFightResponse(predictionDto.getPredictedWinner());
    }
}
