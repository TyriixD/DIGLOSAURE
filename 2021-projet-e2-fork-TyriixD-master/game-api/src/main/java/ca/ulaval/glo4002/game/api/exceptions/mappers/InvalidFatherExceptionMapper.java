package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.InvalidFatherException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidFatherExceptionMapper implements ExceptionMapper<InvalidFatherException> {
    @Override
    public Response toResponse(InvalidFatherException exception) {
        String error = "INVALID_FATHER";
        String description = "Father must be a male.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
