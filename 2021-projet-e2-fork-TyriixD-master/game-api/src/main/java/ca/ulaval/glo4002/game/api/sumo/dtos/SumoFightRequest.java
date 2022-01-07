package ca.ulaval.glo4002.game.api.sumo.dtos;

public class SumoFightRequest {
    public String challenger;
    public String challengee;

    public String getChallengerName() {
        return challenger;
    }

    public String getChallengeeName() {
        return challengee;
    }
}
