package ca.ulaval.glo4002.game.domain.game;

import ca.ulaval.glo4002.game.domain.game.action.ResourceCreationAction;
import ca.ulaval.glo4002.game.domain.turn.Turn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TurnTest {
    private Turn turn;
    private Park parkMock;
    private ResourceCreationAction resourceCreationCommandMock;

    @BeforeEach
    public void setUpCreationCommand() {
        parkMock = mock(Park.class);
        turn = new Turn(parkMock);
        resourceCreationCommandMock = mock(ResourceCreationAction.class);
    }

    @Test
    public void givenANewTurn_whenAnActionIsAdded_thenActionIsAdded() {
        turn.addAction(resourceCreationCommandMock);

        assertFalse(turn.isCommandsEmpty());
    }

    @Test
    public void givenFirstTurn_whenTurnIsExecuted_thenTurnNumberIsIncremented() {
        turn.execute();

        int secondTurn = 2;
        assertEquals(secondTurn, turn.getNumber());
    }

    @Test
    public void givenATurnWithActions_whenExecutingTurn_thenNoActionsRemain() {
        turn.addAction(resourceCreationCommandMock);

        turn.execute();

        assertTrue(turn.isCommandsEmpty());
    }

    @Test
    public void givenAnOngoingSecondTurn_whenResettingTheTurn_thenTurnNumberIsOne() {
        turn.execute();

        turn.reset();

        int firstTurn = 1;
        assertEquals(firstTurn, turn.getNumber());
    }

    @Test
    public void givenAnOngoingGameWithOnePendingAction_whenResettingTheTurn_thenNoActionsRemain() {
        turn.addAction(resourceCreationCommandMock);

        turn.reset();

        assertTrue(turn.isCommandsEmpty());
    }

    @Test
    public void givenAnOnGoingTurnWithOneAction_whenExecuteActions_thenActionExecuteIsCalled() {
        turn.addAction(resourceCreationCommandMock);

        turn.executeActions();

        verify(resourceCreationCommandMock).execute();
    }

    @Test
    public void whenUnexecute_thenTurnNumberDecremented() {
        int decrementedTurn = 1;
        turn.execute();
        turn.unExecute();

        assertEquals(turn.getNumber(),decrementedTurn);
    }



}
