package ca.ulaval.glo4002.game.api.turn;

import ca.ulaval.glo4002.game.api.turn.assemblers.TurnResponseAssembler;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.application.turn.dtos.TurnDto;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TurnApiEndPointTest {
    private TurnUseCase turnUseCaseMock;
    private TurnResponseAssembler turnResponseAssemblerMock;
    private TurnApiEndPoint turnApiEndPoint;

    @BeforeEach
    public void setUpTurnApiEndPoint() {
        turnUseCaseMock = mock(TurnUseCase.class);
        turnResponseAssemblerMock = mock(TurnResponseAssembler.class);
        turnApiEndPoint = new TurnApiEndPoint(turnUseCaseMock, turnResponseAssemblerMock);
    }

    @Test
    public void whenGameResourceExecuteTurn_thenGameUseCaseExecuteAndIncrementTurnIsCalled() throws IOException, ClassNotFoundException {
        turnApiEndPoint.executeTurn();

        verify(turnUseCaseMock).executeTurn();
    }

    @Test
    public void givenAGameDto_whenGameResourceExecuteTurn_thenDtoAssemblerToResponseIsCalled() throws IOException, ClassNotFoundException {
        TurnDto turnDto = new TurnDto(1);
        when(turnUseCaseMock.executeTurn()).thenReturn(turnDto);

        turnApiEndPoint.executeTurn();

        verify(turnResponseAssemblerMock).toResponse(turnDto);
    }

    @Test
    public void whenExecuteTurn_thenReturnStatusOK() throws IOException, ClassNotFoundException {
        Response responseFromApi = turnApiEndPoint.executeTurn();

        assertEquals(Response.Status.OK.getStatusCode(), responseFromApi.getStatus());
    }
}
