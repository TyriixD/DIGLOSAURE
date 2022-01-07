package ca.ulaval.glo4002.game.api.resources;

import ca.ulaval.glo4002.game.api.resources.assemblers.ResourcesCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.resources.assemblers.ResourcesResponseAssembler;
import ca.ulaval.glo4002.game.api.resources.dtos.ResourcesCreationRequest;
import ca.ulaval.glo4002.game.application.resources.AddResourcesUseCase;
import ca.ulaval.glo4002.game.application.resources.GetResourcesUseCase;
import ca.ulaval.glo4002.game.application.resources.dtos.CategorizedResourcesDto;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesCreationDto;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResourcesApiEndPointTest {
    private GetResourcesUseCase getResourcesUseCaseMock;
    private ResourcesResponseAssembler resourcesResponseAssemblerMock;
    private ResourcesCreationDtoAssembler resourcesCreationDtoAssemblerMock;
    private ResourcesApiEndPoint resourcesApiEndPoint;
    private ResourcesCreationRequest resourcesCreationRequest;
    private AddResourcesUseCase addResourcesUseCaseMock;

    @BeforeEach
    public void setUpResourcesApiEndPoint() {
        resourcesCreationRequest = new ResourcesCreationRequest();

        getResourcesUseCaseMock = mock(GetResourcesUseCase.class);
        addResourcesUseCaseMock = mock(AddResourcesUseCase.class);
        resourcesCreationDtoAssemblerMock = mock(ResourcesCreationDtoAssembler.class);
        resourcesResponseAssemblerMock = mock(ResourcesResponseAssembler.class);
        resourcesApiEndPoint = new ResourcesApiEndPoint(getResourcesUseCaseMock, addResourcesUseCaseMock,
                                                        resourcesResponseAssemblerMock,
                                                        resourcesCreationDtoAssemblerMock);
    }

    @Test
    public void givenResourceCreationRequest_whenAddResources_thenResourceUseCaseAddResourcesIsCalled() {
        ResourcesCreationDto resourcesCreationDto = new ResourcesCreationDto(0, 0, 0);

        when(resourcesCreationDtoAssemblerMock.toDto(resourcesCreationRequest)).thenReturn(resourcesCreationDto);
        resourcesApiEndPoint.addResources(resourcesCreationRequest);

        verify(addResourcesUseCaseMock).addResources(resourcesCreationDto);
    }

    @Test
    public void givenResourceCreationRequest_whenAddResources_thenResourceDtoAssemblerToDtoIsCalled() {
        resourcesApiEndPoint.addResources(resourcesCreationRequest);

        verify(resourcesCreationDtoAssemblerMock).toDto(resourcesCreationRequest);
    }

    @Test
    public void whenGetResources_thenResourceUseCaseGetResourcesIsCalled() {
        resourcesApiEndPoint.getResources();

        verify(getResourcesUseCaseMock).getResources();
    }

    @Test
    public void givenCategorizedResourcesDto_whenGetResources_thenToResponseIsCalled() {
        CategorizedResourcesDto categorizedResourcesDto = new CategorizedResourcesDto(new ResourcesDto(0, 0, 0),
                                                                                      new ResourcesDto(0, 0, 0),
                                                                                      new ResourcesDto(0, 0, 0));

        when(getResourcesUseCaseMock.getResources()).thenReturn(categorizedResourcesDto);
        resourcesApiEndPoint.getResources();

        verify(resourcesResponseAssemblerMock).toCategorizedResponse(categorizedResourcesDto);
    }

    @Test
    public void whenAddResources_thenReturnStatusOK() {
        Response responseFromApi = resourcesApiEndPoint.addResources(resourcesCreationRequest);

        assertEquals(Response.Status.OK.getStatusCode(), responseFromApi.getStatus());
    }

    @Test
    public void whenGetResources_thenReturnStatusOK() {
        Response responseFromApi = resourcesApiEndPoint.getResources();

        assertEquals(Response.Status.OK.getStatusCode(), responseFromApi.getStatus());
    }
}
