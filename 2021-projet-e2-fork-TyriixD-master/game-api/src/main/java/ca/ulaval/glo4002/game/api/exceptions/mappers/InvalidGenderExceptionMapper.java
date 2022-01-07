package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidGenderException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidGenderExceptionMapper implements ExceptionMapper<InvalidGenderException> {
    @Override
    public Response toResponse(InvalidGenderException exception) {
        String error = "INVALID_GENDER";
        String description = "The specified gender must be \"m\" or \"f\".";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
