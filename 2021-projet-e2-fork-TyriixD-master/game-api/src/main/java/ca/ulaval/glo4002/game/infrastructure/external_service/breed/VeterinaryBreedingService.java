package ca.ulaval.glo4002.game.infrastructure.external_service.breed;

import ca.ulaval.glo4002.game.application.breed.dtos.BreedingResponse;
import ca.ulaval.glo4002.game.domain.breed.BreedingService;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.IncompatibleDinosaursForBreedingException;
import ca.ulaval.glo4002.game.infrastructure.external_service.breed.assemblers.BreedingRequestAssembler;
import ca.ulaval.glo4002.game.infrastructure.external_service.breed.assemblers.BreedingResponseAssembler;
import ca.ulaval.glo4002.game.infrastructure.external_service.breed.dtos.BreedingRequest;
import ca.ulaval.glo4002.game.infrastructure.external_service.breed.dtos.BreedingResult;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class VeterinaryBreedingService implements BreedingService {
    private final BreedingResponseAssembler breedingResponseAssembler;
    private final BreedingRequestAssembler breedingRequestAssembler;

    public VeterinaryBreedingService(BreedingRequestAssembler breedingRequestAssembler,
                                     BreedingResponseAssembler breedingResponseAssembler) {
        this.breedingResponseAssembler = breedingResponseAssembler;
        this.breedingRequestAssembler = breedingRequestAssembler;
    }

    @Override
    public BreedingResponse breed(Dinosaur father, Dinosaur mother) {
        BreedingRequest request = breedingRequestAssembler.toBreedingRequest(father, mother);
        BreedingResult breedingResult = attemptBreeding(request);
        return breedingResponseAssembler.toBreedingResponse(breedingResult);
    }

    private BreedingResult attemptBreeding(BreedingRequest request) {
        String vetServiceURI = "http://localhost:8080/breed";

        try {
            WebTarget webTarget = ClientBuilder.newClient().target(new URI(vetServiceURI));
            Response response = webTarget.request().post(Entity.entity(request, MediaType.APPLICATION_JSON));

            if (response.getStatusInfo().equals(Response.Status.BAD_REQUEST)) {
                throw new IncompatibleDinosaursForBreedingException();
            }

            return response.readEntity(BreedingResult.class);
        } catch (URISyntaxException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
