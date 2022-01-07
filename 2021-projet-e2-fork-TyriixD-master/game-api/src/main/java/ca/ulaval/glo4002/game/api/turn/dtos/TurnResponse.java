package ca.ulaval.glo4002.game.api.turn.dtos;

public class TurnResponse {
    public final int turnNumber;

    public TurnResponse(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
