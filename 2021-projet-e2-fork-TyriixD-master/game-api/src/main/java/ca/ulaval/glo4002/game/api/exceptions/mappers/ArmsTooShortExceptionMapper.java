package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.sumo.exceptions.ArmsTooShortException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ArmsTooShortExceptionMapper implements ExceptionMapper<ArmsTooShortException> {
    @Override
    public Response toResponse(ArmsTooShortException exception) {
        String error = "ARMS_TOO_SHORT";
        String description = "Tyrannosaurus Rex can't participate.";
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(error, description))
            .build();
    }
}
