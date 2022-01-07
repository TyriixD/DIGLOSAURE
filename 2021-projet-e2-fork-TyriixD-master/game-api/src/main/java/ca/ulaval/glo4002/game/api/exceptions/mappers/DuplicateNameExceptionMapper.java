package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.DuplicateNameException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DuplicateNameExceptionMapper implements ExceptionMapper<DuplicateNameException> {
    @Override
    public Response toResponse(DuplicateNameException exception) {
        String error = "DUPLICATE_NAME";
        String description = "The specified name already exists and must be unique.";
        Response response = Response.status(Response.Status.BAD_REQUEST)
            .entity(new ExceptionResponse(error, description)).build();
        return response;
    }
}
