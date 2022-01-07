package ca.ulaval.glo4002.game.application.sumo.dtos;

public class SumoFightCreationDto {
    private final String challengerName;
    private final String challengeeName;

    public SumoFightCreationDto(String challengerName, String challengeeName) {
        this.challengerName = challengerName;
        this.challengeeName = challengeeName;
    }

    public String getChallengerName() {
        return challengerName;
    }

    public String getChallengeeName() {
        return challengeeName;
    }
}
