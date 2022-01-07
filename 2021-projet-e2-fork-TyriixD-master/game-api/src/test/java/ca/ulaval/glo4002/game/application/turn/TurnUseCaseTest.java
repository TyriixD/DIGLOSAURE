package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.turn.assemblers.TurnDtoAssembler;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.game.Park;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TurnUseCaseTest {
    private GameRepository gameRepositoryMock;
    private GameMemento gameMementoMock;
    private TurnDtoAssembler turnDtoAssemblerMock;
    private TurnUseCase turnUseCase;
    private Game gameMock;

    @BeforeEach
    public void setUp() {
        gameMock = mock(Game.class);
        gameMementoMock = mock(GameMemento.class);
        gameRepositoryMock = mock(GameRepository.class);
        turnDtoAssemblerMock = mock(TurnDtoAssembler.class);
        turnUseCase = new TurnUseCase(gameRepositoryMock, turnDtoAssemblerMock,gameMementoMock);
        when(gameRepositoryMock.find()).thenReturn(gameMock);
    }

    @Test
    public void whenExecuteTurn_thenGameExecuteTurnIsCalled() throws IOException, ClassNotFoundException {
        turnUseCase.executeTurn();

        verify(gameMock).executeTurn();
    }

    @Test
    public void whenExecuteTurn_thenGameAssemblerToDtoIsCalled() throws IOException, ClassNotFoundException {
        turnUseCase.executeTurn();

        verify(turnDtoAssemblerMock).toDto(gameMock.getTurnNumber());
    }

    @Test
    public void whenExecuteTurn_thenGameIsSaved() throws IOException, ClassNotFoundException {
        turnUseCase.executeTurn();

        verify(gameRepositoryMock).save(gameMock);
    }

    @Test
    public void whenExecuteTurn_thenAMementoIsCreated() throws IOException, ClassNotFoundException {
        turnUseCase.executeTurn();

        verify(gameMementoMock).createMemento(gameMock.getPark());
    }

    @Test
    public void whenUnexecuteTurn_thenUndoIsCalled() {
        turnUseCase.unexecuteTurn();

        verify(gameMementoMock).undo();
    }
    @Test
    public void whenUnexecuteTurn_thenGameIsUnexecuted() {
        turnUseCase.unexecuteTurn();

        verify(gameMock).unExecuteTurn();
    }
    @Test
    public void whenUnExecuteTurn_thenGameIsSaved() {
        turnUseCase.unexecuteTurn();

        verify(gameRepositoryMock).save(gameMock);
    }

    @Test
    public void whenUnExecuteTurn_thenGameStateIsRecovered() {
        Park recoveredState = mock(Park.class);
        when(gameMementoMock.recoverMemento()).thenReturn(recoveredState);

        turnUseCase.unexecuteTurn();

        verify(gameMock).recoverState(recoveredState);
    }

}
