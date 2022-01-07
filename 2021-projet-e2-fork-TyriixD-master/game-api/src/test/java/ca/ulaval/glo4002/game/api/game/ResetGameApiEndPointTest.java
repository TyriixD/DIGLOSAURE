package ca.ulaval.glo4002.game.api.game;

import ca.ulaval.glo4002.game.application.game.ResetGameUseCase;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ResetGameApiEndPointTest {
    private ResetGameUseCase resetGameUseCaseMock;
    private ResetGameApiEndPoint resetGameApiEndPoint;

    @BeforeEach
    public void setUpGameApiEndPoint() {
        resetGameUseCaseMock = mock(ResetGameUseCase.class);
        resetGameApiEndPoint = new ResetGameApiEndPoint(resetGameUseCaseMock);
    }

    @Test
    public void whenGameResourceResetGame_thenResetGameUseCaseResetIsCalled() {
        resetGameApiEndPoint.resetGame();

        verify(resetGameUseCaseMock).reset();
    }

    @Test
    public void whenResetGame_thenReturnStatusOK() {
        Response responseFromApi = resetGameApiEndPoint.resetGame();

        assertEquals(Response.Status.OK.getStatusCode(), responseFromApi.getStatus());
    }
}
