package ca.ulaval.glo4002.game.api.breed;

import ca.ulaval.glo4002.game.api.breed.assemblers.DinosaurBreedingDtoAssembler;
import ca.ulaval.glo4002.game.api.breed.dtos.DinosaurBreedingRequest;
import ca.ulaval.glo4002.game.application.breed.BreedDinosaursUseCase;
import ca.ulaval.glo4002.game.application.breed.dtos.DinosaurBreedingDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/breed")
public class BreedApiEndPoint {
    private final BreedDinosaursUseCase dinosaurBreedingUseCase;
    private final DinosaurBreedingDtoAssembler dinosaurBreedingDtoAssembler;

    public BreedApiEndPoint(BreedDinosaursUseCase breedDinosaursUseCase,
                            DinosaurBreedingDtoAssembler dinosaurBreedingDtoAssembler) {
        this.dinosaurBreedingUseCase = breedDinosaursUseCase;
        this.dinosaurBreedingDtoAssembler = dinosaurBreedingDtoAssembler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response breedDinosaurs(DinosaurBreedingRequest request) {
        DinosaurBreedingDto breedingDto = dinosaurBreedingDtoAssembler.toDto(request);
        dinosaurBreedingUseCase.breedDinosaurs(breedingDto);
        return Response.ok().build();
    }
}
