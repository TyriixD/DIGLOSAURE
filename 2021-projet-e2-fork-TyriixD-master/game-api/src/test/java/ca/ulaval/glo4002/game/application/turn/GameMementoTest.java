package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.dinosaur.exceptions.NoTurnToUnturnException;
import ca.ulaval.glo4002.game.application.turn.assemblers.TurnDtoAssembler;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightChangeException;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.game.Park;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GameMementoTest {
    private GameRepository gameRepository;
    private GameMemento gameMemento;
    private Game gameMock;

    @BeforeEach
    public void setUp() {
        gameRepository = mock(GameRepository.class);
        gameMemento = new GameMementoImpl(gameRepository);
        gameMock = mock(Game.class);
    }

    @Test
    public void whenRecoveringMementoWithTurnOne_thenNoTurnToUnturnExeptionIsThrown() {
        when(gameRepository.find()).thenReturn(gameMock);
        when(gameMock.getTurnNumber()).thenReturn(1);

        assertThrows(NoTurnToUnturnException.class, () -> gameMemento.recoverMemento());
    }

    @Test
    public void whenUndoWithTurnOne_thenNoTurnToUnturnExeptionIsThrown() {
        when(gameRepository.find()).thenReturn(gameMock);
        when(gameMock.getTurnNumber()).thenReturn(1);

        assertThrows(NoTurnToUnturnException.class, () -> gameMemento.undo());
    }

}
