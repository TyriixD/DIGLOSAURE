package ca.ulaval.glo4002.game.api.turn.assemblers;

import ca.ulaval.glo4002.game.api.turn.dtos.TurnResponse;
import ca.ulaval.glo4002.game.application.turn.dtos.TurnDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnResponseAssemblerTest {
    private final int turnNumber = 1;
    private TurnResponseAssembler turnResponseAssembler;

    @BeforeEach
    public void setUp() {
        turnResponseAssembler = new TurnResponseAssembler();
    }

    @Test
    public void givenATurnNumber_whenToResponse_thenResponseTurnNumberIsCorrect() {
        TurnDto turnDto = new TurnDto(turnNumber);

        TurnResponse response = turnResponseAssembler.toResponse(turnDto);

        assertEquals(turnNumber, response.getTurnNumber());
    }
}
