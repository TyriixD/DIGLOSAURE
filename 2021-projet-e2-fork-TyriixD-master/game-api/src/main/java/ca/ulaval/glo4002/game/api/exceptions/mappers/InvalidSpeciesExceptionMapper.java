package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidSpeciesException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidSpeciesExceptionMapper implements ExceptionMapper<InvalidSpeciesException> {
    @Override
    public Response toResponse(InvalidSpeciesException exception) {
        String error = "INVALID_SPECIES";
        String description = "The specified species is not supported.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
