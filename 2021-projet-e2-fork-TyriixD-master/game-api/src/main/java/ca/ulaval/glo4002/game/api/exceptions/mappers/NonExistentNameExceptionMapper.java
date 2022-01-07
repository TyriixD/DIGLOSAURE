package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.NonExistentNameException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NonExistentNameExceptionMapper implements ExceptionMapper<NonExistentNameException> {
    @Override
    public Response toResponse(NonExistentNameException exception) {
        String error = "NON_EXISTENT_NAME";
        String description = "The specified name does not exist.";
        return Response.status(Response.Status.NOT_FOUND).entity(new ExceptionResponse(error, description))
            .build();
    }
}
