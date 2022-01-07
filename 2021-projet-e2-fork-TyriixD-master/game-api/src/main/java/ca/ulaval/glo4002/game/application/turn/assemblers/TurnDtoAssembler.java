package ca.ulaval.glo4002.game.application.turn.assemblers;

import ca.ulaval.glo4002.game.application.turn.dtos.TurnDto;

public class TurnDtoAssembler {
    public TurnDto toDto(int turnNumber) {
        return new TurnDto(turnNumber);
    }
}
