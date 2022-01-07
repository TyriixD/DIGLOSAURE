package ca.ulaval.glo4002.game.api.exceptions.mappers;

import ca.ulaval.glo4002.game.api.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.IncompatibleDinosaursForBreedingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class IncompatibleDinosaurForBreedingExceptionMapper implements ExceptionMapper<IncompatibleDinosaursForBreedingException> {

    @Override
    public Response toResponse(IncompatibleDinosaursForBreedingException e) {
        String description = "These two dinosaurs cannot have a baby dinosaur together";
        return Response.ok().build();
    }
}
