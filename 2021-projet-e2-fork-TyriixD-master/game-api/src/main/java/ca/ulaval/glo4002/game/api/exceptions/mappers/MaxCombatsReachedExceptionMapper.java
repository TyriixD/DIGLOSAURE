package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.sumo.exceptions.MaxCombatsReachedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MaxCombatsReachedExceptionMapper implements ExceptionMapper<MaxCombatsReachedException> {
    @Override
    public Response toResponse(MaxCombatsReachedException exception) {
        String error = "MAX_COMBATS_REACHED";
        String description = "Max number of combats has been reached.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
