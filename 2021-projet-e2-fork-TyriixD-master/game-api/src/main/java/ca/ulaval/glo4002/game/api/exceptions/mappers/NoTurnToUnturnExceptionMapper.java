package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.NoTurnToUnturnException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoTurnToUnturnExceptionMapper implements ExceptionMapper<NoTurnToUnturnException> {

    @Override
    public Response toResponse(NoTurnToUnturnException exception) {
        String error = "NO_TURNS_TO_UNTURN";
        String description = "There are no turns to unturn.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
