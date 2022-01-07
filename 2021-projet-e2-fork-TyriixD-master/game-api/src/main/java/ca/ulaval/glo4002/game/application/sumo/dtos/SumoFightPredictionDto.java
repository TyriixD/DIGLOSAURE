package ca.ulaval.glo4002.game.application.sumo.dtos;

public class SumoFightPredictionDto {
    private final String predictedWinner;

    public SumoFightPredictionDto(String predictedWinner) {
        this.predictedWinner = predictedWinner;
    }

    public String getPredictedWinner() {
        return predictedWinner;
    }
}
