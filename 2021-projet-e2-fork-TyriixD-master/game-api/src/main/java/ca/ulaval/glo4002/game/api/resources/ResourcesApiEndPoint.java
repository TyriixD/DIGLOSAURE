package ca.ulaval.glo4002.game.api.resources;

import ca.ulaval.glo4002.game.api.resources.assemblers.ResourcesCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.resources.assemblers.ResourcesResponseAssembler;
import ca.ulaval.glo4002.game.api.resources.dtos.CategorizedResourcesResponse;
import ca.ulaval.glo4002.game.api.resources.dtos.ResourcesCreationRequest;
import ca.ulaval.glo4002.game.application.resources.AddResourcesUseCase;
import ca.ulaval.glo4002.game.application.resources.GetResourcesUseCase;
import ca.ulaval.glo4002.game.application.resources.dtos.CategorizedResourcesDto;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesCreationDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/resources")
@Produces(MediaType.APPLICATION_JSON)
public class ResourcesApiEndPoint {
    private final GetResourcesUseCase getResourcesUseCase;
    private final AddResourcesUseCase addResourcesUseCase;
    private final ResourcesResponseAssembler resourcesResponseAssembler;
    private final ResourcesCreationDtoAssembler resourcesCreationDtoAssembler;

    public ResourcesApiEndPoint(GetResourcesUseCase getResourcesUseCase, AddResourcesUseCase addResourcesUseCase,
                                ResourcesResponseAssembler resourcesResponseAssembler,
                                ResourcesCreationDtoAssembler resourcesCreationDtoAssembler) {
        this.getResourcesUseCase = getResourcesUseCase;
        this.addResourcesUseCase = addResourcesUseCase;
        this.resourcesResponseAssembler = resourcesResponseAssembler;
        this.resourcesCreationDtoAssembler = resourcesCreationDtoAssembler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addResources(ResourcesCreationRequest request) {
        ResourcesCreationDto creationRequest = resourcesCreationDtoAssembler.toDto(request);
        addResourcesUseCase.addResources(creationRequest);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResources() {
        CategorizedResourcesDto dto = getResourcesUseCase.getResources();
        CategorizedResourcesResponse response = resourcesResponseAssembler.toCategorizedResponse(dto);
        return Response.ok().entity(response).build();
    }
}
