package ca.ulaval.glo4002.game.application.turn.assemblers;

import ca.ulaval.glo4002.game.application.turn.dtos.TurnDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnDtoAssemblerTest {
    private TurnDtoAssembler turnDtoAssembler;
    private int turnNumber;

    @BeforeEach
    public void setUpGameAssemblerTest() {
        turnDtoAssembler = new TurnDtoAssembler();
        turnNumber = 1;
    }

    @Test
    public void givenATurnNumber_whenGameAssemblerToDto_thenGameAssemblerTurnNumberIsCorrect() {
        TurnDto turnDto = turnDtoAssembler.toDto(turnNumber);

        assertEquals(turnNumber, turnDto.getTurnNumber());
    }
}
