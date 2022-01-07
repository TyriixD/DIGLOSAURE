package ca.ulaval.glo4002.game.api.game;

import ca.ulaval.glo4002.game.application.game.ResetGameUseCase;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/reset")
public class ResetGameApiEndPoint {
    private final ResetGameUseCase resetGameUseCase;

    public ResetGameApiEndPoint(ResetGameUseCase resetGameUseCase) {
        this.resetGameUseCase = resetGameUseCase;
    }

    @POST
    public Response resetGame() {
        resetGameUseCase.reset();
        return Response.ok().build();
    }
}
