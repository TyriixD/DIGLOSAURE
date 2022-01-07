package ca.ulaval.glo4002.game.api.turn;

import ca.ulaval.glo4002.game.api.turn.assemblers.TurnResponseAssembler;
import ca.ulaval.glo4002.game.api.turn.dtos.TurnResponse;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.application.turn.dtos.TurnDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/unturn")
public class UnturnApiEndPoint {
    private final TurnUseCase turnUseCase;
    private final TurnResponseAssembler turnResponseAssembler;

    public UnturnApiEndPoint(TurnUseCase turnUseCase, TurnResponseAssembler turnResponseAssembler){
        this.turnUseCase = turnUseCase;
        this.turnResponseAssembler = turnResponseAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response unExecuteTurn() {
        TurnDto turnDto = turnUseCase.unexecuteTurn();
        TurnResponse response = turnResponseAssembler.toResponse(turnDto);
        return Response.ok().entity(response).build();
    }


}
