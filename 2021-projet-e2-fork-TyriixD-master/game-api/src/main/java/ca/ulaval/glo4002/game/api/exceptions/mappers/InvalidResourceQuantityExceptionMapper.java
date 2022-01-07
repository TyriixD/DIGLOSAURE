package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.api.resources.exceptions.InvalidResourceQuantityException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidResourceQuantityExceptionMapper implements ExceptionMapper<InvalidResourceQuantityException> {
    @Override
    public Response toResponse(InvalidResourceQuantityException exception) {
        String error = "INVALID_RESOURCE_QUANTITY";
        String description = "Resource quantities must be positive.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
