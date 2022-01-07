package ca.ulaval.glo4002.game.api.sumo.dtos;

public class SumoFightResponse {
    private final String predictedWinner;

    public SumoFightResponse(String predictedWinner) {
        this.predictedWinner = predictedWinner;
    }

    public String getPredictedWinner() {
        return predictedWinner;
    }
}
