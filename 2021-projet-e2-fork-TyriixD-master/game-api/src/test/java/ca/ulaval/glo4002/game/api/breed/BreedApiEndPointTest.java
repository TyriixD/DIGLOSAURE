package ca.ulaval.glo4002.game.api.breed;

import ca.ulaval.glo4002.game.api.breed.assemblers.DinosaurBreedingDtoAssembler;
import ca.ulaval.glo4002.game.api.breed.dtos.DinosaurBreedingRequest;
import ca.ulaval.glo4002.game.application.breed.BreedDinosaursUseCase;
import ca.ulaval.glo4002.game.application.breed.dtos.DinosaurBreedingDto;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BreedApiEndPointTest {
    private DinosaurBreedingDtoAssembler dinosaurBreedingDtoAssemblerMock;
    private BreedDinosaursUseCase breedDinosaursUseCaseMock;
    private BreedApiEndPoint breedApiEndPoint;
    private DinosaurBreedingRequest breedingRequestMock;
    private DinosaurBreedingDto breedingDto;

    @BeforeEach
    public void setUpBreedApiEndpoint() {
        dinosaurBreedingDtoAssemblerMock = mock(DinosaurBreedingDtoAssembler.class);
        breedDinosaursUseCaseMock = mock(BreedDinosaursUseCase.class);
        breedApiEndPoint = new BreedApiEndPoint(breedDinosaursUseCaseMock, dinosaurBreedingDtoAssemblerMock);

        breedingRequestMock = mock(DinosaurBreedingRequest.class);
        breedingDto = new DinosaurBreedingDto("Olivier", "Martin", "Pauline");
    }

    @Test
    public void whenBreedDinosaurs_thenAssemblerToDtoIsCalled() {
        breedApiEndPoint.breedDinosaurs(breedingRequestMock);

        verify(dinosaurBreedingDtoAssemblerMock).toDto(breedingRequestMock);
    }

    @Test
    public void whenBreedDinosaurs_thenUseCaseBreedDinosaursIsCalled() {
        when(dinosaurBreedingDtoAssemblerMock.toDto(any())).thenReturn(breedingDto);

        breedApiEndPoint.breedDinosaurs(breedingRequestMock);

        verify(breedDinosaursUseCaseMock).breedDinosaurs(breedingDto);
    }

    @Test
    public void givenValidBreedingRequest_whenBreedDinosaurs_thenResponseStatusCodeIsOk() {
        when(dinosaurBreedingDtoAssemblerMock.toDto(any())).thenReturn(breedingDto);

        Response response = breedApiEndPoint.breedDinosaurs(breedingRequestMock);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
