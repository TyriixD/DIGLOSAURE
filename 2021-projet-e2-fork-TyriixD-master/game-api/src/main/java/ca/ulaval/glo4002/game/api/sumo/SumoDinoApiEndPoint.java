package ca.ulaval.glo4002.game.api.sumo;

import ca.ulaval.glo4002.game.api.sumo.assemblers.SumoFightCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.sumo.assemblers.SumoFightResponseAssembler;
import ca.ulaval.glo4002.game.api.sumo.dtos.SumoFightRequest;
import ca.ulaval.glo4002.game.application.sumo.SumoFightDinosaurUseCase;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightCreationDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightPredictionDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sumodino")
public class SumoDinoApiEndPoint {
    private final SumoFightDinosaurUseCase sumoFightDinosaurUseCase;
    private final SumoFightCreationDtoAssembler sumoFightCreationDtoAssembler;
    private final SumoFightResponseAssembler sumoFightResponseAssembler;

    public SumoDinoApiEndPoint(SumoFightDinosaurUseCase sumoFightDinosaurUseCase,
                               SumoFightCreationDtoAssembler sumoFightCreationDtoAssembler,
                               SumoFightResponseAssembler sumoFightResponseAssembler) {
        this.sumoFightDinosaurUseCase = sumoFightDinosaurUseCase;
        this.sumoFightCreationDtoAssembler = sumoFightCreationDtoAssembler;
        this.sumoFightResponseAssembler = sumoFightResponseAssembler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sumoFight(SumoFightRequest request) {
        SumoFightCreationDto sumoDto = sumoFightCreationDtoAssembler.toCreationDto(request);
        SumoFightPredictionDto predictedOutcomeDto = sumoFightDinosaurUseCase.stageSumoFight(sumoDto);
        return Response.ok().entity(sumoFightResponseAssembler.toResponse(predictedOutcomeDto)).build();
    }
}
