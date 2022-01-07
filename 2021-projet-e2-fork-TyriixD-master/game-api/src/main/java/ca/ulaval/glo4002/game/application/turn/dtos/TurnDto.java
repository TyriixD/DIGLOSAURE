package ca.ulaval.glo4002.game.application.turn.dtos;

public class TurnDto {
    private final int turnNumber;

    public TurnDto(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
