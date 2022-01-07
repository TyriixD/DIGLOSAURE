package ca.ulaval.glo4002.game.api.turn;

import ca.ulaval.glo4002.game.api.turn.assemblers.TurnResponseAssembler;
import ca.ulaval.glo4002.game.api.turn.dtos.TurnResponse;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.application.turn.dtos.TurnDto;

import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/turn")
public class TurnApiEndPoint {
    private final TurnUseCase turnUseCase;
    private final TurnResponseAssembler turnResponseAssembler;

    public TurnApiEndPoint(TurnUseCase turnUseCase, TurnResponseAssembler turnResponseAssembler) {
        this.turnUseCase = turnUseCase;
        this.turnResponseAssembler = turnResponseAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response executeTurn() throws IOException, ClassNotFoundException {
        TurnDto turnDto = turnUseCase.executeTurn();
        TurnResponse response = turnResponseAssembler.toResponse(turnDto);
        return Response.ok().entity(response).build();
    }
}
