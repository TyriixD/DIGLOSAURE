package ca.ulaval.glo4002.game.api.sumo;

import ca.ulaval.glo4002.game.api.sumo.assemblers.SumoFightCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.sumo.assemblers.SumoFightResponseAssembler;
import ca.ulaval.glo4002.game.api.sumo.dtos.SumoFightRequest;
import ca.ulaval.glo4002.game.application.sumo.SumoFightDinosaurUseCase;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightPredictionDto;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SumoDinoApiEndPointTest {
    private SumoFightDinosaurUseCase sumoFightDinosaurUseCaseMock;
    private SumoDinoApiEndPoint sumoDinoApiEndPoint;
    private SumoFightRequest sumoFightRequest;
    private SumoFightPredictionDto predictionDto;

    @BeforeEach
    public void setUpSumoApiEndpoint() {
        SumoFightResponseAssembler sumoFightResponseAssembler = new SumoFightResponseAssembler();
        SumoFightCreationDtoAssembler sumoFightCreationDtoAssembler = new SumoFightCreationDtoAssembler();
        sumoFightDinosaurUseCaseMock = mock(SumoFightDinosaurUseCase.class);

        sumoDinoApiEndPoint = new SumoDinoApiEndPoint(sumoFightDinosaurUseCaseMock, sumoFightCreationDtoAssembler,
                                                      sumoFightResponseAssembler);

        sumoFightRequest = new SumoFightRequest();
        sumoFightRequest.challenger = "Gisele";
        sumoFightRequest.challengee = "Gerard";

        predictionDto = new SumoFightPredictionDto("Arnold");
    }

    @Test
    public void whenSumoFight_thenUseCaseStageSumoFightIsCalled() {
        when(sumoFightDinosaurUseCaseMock.stageSumoFight(any())).thenReturn(predictionDto);

        sumoDinoApiEndPoint.sumoFight(sumoFightRequest);

        verify(sumoFightDinosaurUseCaseMock).stageSumoFight(any());
    }

    @Test
    public void givenValidFightRequest_whenSumoFight_thenResponseStatusCodeIsOk() {
        when(sumoFightDinosaurUseCaseMock.stageSumoFight(any())).thenReturn(predictionDto);

        Response response = sumoDinoApiEndPoint.sumoFight(sumoFightRequest);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
