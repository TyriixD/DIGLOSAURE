package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidBabyWeightChangeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidBabyWeightChangeExceptionMapper implements ExceptionMapper<InvalidBabyWeightChangeException> {
    @Override
    public Response toResponse(InvalidBabyWeightChangeException exception) {
        String error = "INVALID_BABY_WEIGHT_CHANGE";
        String description ="The weight of a baby dinosaur can not be changed.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
