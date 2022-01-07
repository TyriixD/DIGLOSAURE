package ca.ulaval.glo4002.game.domain.sumo;

public class SumoFightPrediction {
    public final String predictedWinnerName;

    public SumoFightPrediction(String predictedWinnerName) {
        this.predictedWinnerName = predictedWinnerName;
    }

    public String getPredictedWinner() {
        return predictedWinnerName;
    }
}
