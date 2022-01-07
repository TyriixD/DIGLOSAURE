package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.InvalidMotherException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidMotherExceptionMapper implements ExceptionMapper<InvalidMotherException> {
    @Override
    public Response toResponse(InvalidMotherException exception) {
        String error = "INVALID_MOTHER";
        String description = "Mother must be a female.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
