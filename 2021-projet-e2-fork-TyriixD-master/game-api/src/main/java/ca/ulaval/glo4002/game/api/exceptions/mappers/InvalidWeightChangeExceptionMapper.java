package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightChangeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidWeightChangeExceptionMapper implements ExceptionMapper<InvalidWeightChangeException> {
    @Override
    public Response toResponse(InvalidWeightChangeException exception) {
        String error = "INVALID_WEIGHT_CHANGE";
        String description = "The specified weight loss must not make the dinosaur weight less than 100 kg.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
