package ca.ulaval.glo4002.game.api.turn.assemblers;

import ca.ulaval.glo4002.game.api.turn.dtos.TurnResponse;
import ca.ulaval.glo4002.game.application.turn.dtos.TurnDto;

public class TurnResponseAssembler {
    public TurnResponse toResponse(TurnDto turnDto) {
        return new TurnResponse(turnDto.getTurnNumber());
    }
}
