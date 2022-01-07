package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.dinosaur.exceptions.InvalidWeightException;
import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidWeightExceptionMapper implements ExceptionMapper<InvalidWeightException> {
    @Override
    public Response toResponse(InvalidWeightException exception) {
        String error = "INVALID_WEIGHT";
        String description = "The specified weight must be equal or greater than 100 kg.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
