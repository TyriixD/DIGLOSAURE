package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.sumo.exceptions.DinosaurAlreadyParticipatingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DinosaurAlreadyParticipatingExceptionMapper
    implements ExceptionMapper<DinosaurAlreadyParticipatingException> {
    @Override
    public Response toResponse(DinosaurAlreadyParticipatingException exception) {
        String error = "DINOSAUR_ALREADY_PARTICIPATING";
        String description = "Dinosaur is already participating.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
