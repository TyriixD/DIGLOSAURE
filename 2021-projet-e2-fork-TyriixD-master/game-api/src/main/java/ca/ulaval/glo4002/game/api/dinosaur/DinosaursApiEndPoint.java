package ca.ulaval.glo4002.game.api.dinosaur;

import ca.ulaval.glo4002.game.api.dinosaur.assemblers.DinosaurCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.dinosaur.assemblers.DinosaurResponseAssembler;
import ca.ulaval.glo4002.game.api.dinosaur.assemblers.DinosaurWeightModificationDtoAssembler;
import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurCreationRequest;
import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurResponse;
import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurWeightModificationRequest;
import ca.ulaval.glo4002.game.application.dinosaur.AddDinosaurUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.GetAllDinosaursUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.GetDinosaurByNameUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.ModifyDinosaurWeightUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurWeightModificationDto;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dinosaurs")
public class DinosaursApiEndPoint {
    private final AddDinosaurUseCase addDinosaurUseCase;
    private final GetDinosaurByNameUseCase getDinosaurByNameUseCase;
    private final GetAllDinosaursUseCase getAllDinosaursUseCase;
    private final DinosaurResponseAssembler dinosaurResponseAssembler;
    private final DinosaurCreationDtoAssembler dinosaurCreationDtoAssembler;
    private final ModifyDinosaurWeightUseCase dinosaurGrowthUseCase;
    private final DinosaurWeightModificationDtoAssembler dinosaurWeightModificationDtoAssembler;

    public DinosaursApiEndPoint(AddDinosaurUseCase addDinosaurUseCase,
                                GetDinosaurByNameUseCase getDinosaurByNameUseCase,
                                GetAllDinosaursUseCase getAllDinosaursUseCase,
                                DinosaurResponseAssembler dinosaurResponseAssembler,
                                DinosaurCreationDtoAssembler dinosaurCreationDtoAssembler,
                                ModifyDinosaurWeightUseCase dinosaurGrowthUseCase,
                                DinosaurWeightModificationDtoAssembler dinosaurWeightModificationDtoAssembler) {
        this.addDinosaurUseCase = addDinosaurUseCase;
        this.getAllDinosaursUseCase = getAllDinosaursUseCase;
        this.getDinosaurByNameUseCase = getDinosaurByNameUseCase;
        this.dinosaurResponseAssembler = dinosaurResponseAssembler;
        this.dinosaurCreationDtoAssembler = dinosaurCreationDtoAssembler;
        this.dinosaurGrowthUseCase = dinosaurGrowthUseCase;
        this.dinosaurWeightModificationDtoAssembler = dinosaurWeightModificationDtoAssembler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDinosaur(DinosaurCreationRequest request) {
        DinosaurCreationDto creationDto = dinosaurCreationDtoAssembler.toDto(request);
        addDinosaurUseCase.addDinosaur(creationDto);
        return Response.ok().build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDinosaurByName(@PathParam("name") String name) {
        DinosaurDto dinosaur = getDinosaurByNameUseCase.getDinosaurByName(name);
        DinosaurResponse response = dinosaurResponseAssembler.toResponse(dinosaur);
        return Response.ok().entity(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDinosaurs() {
        List<DinosaurDto> dinosaurs = getAllDinosaursUseCase.getAllDinosaurs();
        List<DinosaurResponse> response = dinosaurResponseAssembler.toResponse(dinosaurs);
        return Response.ok().entity(response).build();
    }

    @PATCH
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyDinosaurWeight(@PathParam("name") String dinosaurName,
                                         DinosaurWeightModificationRequest request) {
        DinosaurWeightModificationDto growthDto = dinosaurWeightModificationDtoAssembler.toDto(dinosaurName, request);
        dinosaurGrowthUseCase.modifyDinosaurWeight(growthDto);
        return Response.ok().build();
    }
}
