package ca.ulaval.glo4002.game.application.game;

import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResetGameUseCaseTest {
    private Game gameMock;
    private GameRepository gameRepository;
    private ResetGameUseCase resetGameUseCase;

    @BeforeEach
    public void setUpGameUseCase() {
        gameMock = mock(Game.class);
        gameRepository = mock(GameRepository.class);
        resetGameUseCase = new ResetGameUseCase(gameRepository);
        when(gameRepository.find()).thenReturn(gameMock);
    }

    @Test
    public void whenResetIsCalled_thenGameResetIsCalled() {
        resetGameUseCase.reset();

        verify(gameMock).reset();
    }

    @Test
    public void whenResetIsCalled_thenGameRepositorySaveGameIsCalled() {
        resetGameUseCase.reset();

        verify(gameRepository).save(gameMock);
    }
    @Test
    public void whenResetIsCalled_thenRepositoryIsReset() {
        resetGameUseCase.reset();

        verify(gameRepository).reset();
    }
}
