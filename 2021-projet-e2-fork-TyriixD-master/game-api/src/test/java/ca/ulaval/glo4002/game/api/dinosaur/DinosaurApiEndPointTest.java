package ca.ulaval.glo4002.game.api.dinosaur;

import ca.ulaval.glo4002.game.api.dinosaur.assemblers.DinosaurCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.dinosaur.assemblers.DinosaurResponseAssembler;
import ca.ulaval.glo4002.game.api.dinosaur.assemblers.DinosaurWeightModificationDtoAssembler;
import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurCreationRequest;
import ca.ulaval.glo4002.game.api.dinosaur.dtos.DinosaurWeightModificationRequest;
import ca.ulaval.glo4002.game.application.dinosaur.AddDinosaurUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.GetAllDinosaursUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.GetDinosaurByNameUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.ModifyDinosaurWeightUseCase;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DinosaurApiEndPointTest {
    private GetAllDinosaursUseCase getAllDinosaursUseCaseMock;
    private AddDinosaurUseCase addDinosaurUseCaseMock;
    private GetDinosaurByNameUseCase getDinosaurByNameUseCaseMock;
    private DinosaurResponseAssembler dinosaurResponseAssemblerMock;
    private DinosaurCreationDtoAssembler dinosaurCreationDtoAssemblerMock;
    private DinosaursApiEndPoint dinosaursApiEndPoint;
    private ModifyDinosaurWeightUseCase modifyDinosaurWeightUseCaseMock;
    private DinosaurWeightModificationDtoAssembler dinosaurWeightModificationDtoAssembler;

    @BeforeEach
    public void setUpDinosaurApiEndpoint() {
        addDinosaurUseCaseMock = mock(AddDinosaurUseCase.class);
        getDinosaurByNameUseCaseMock = mock(GetDinosaurByNameUseCase.class);
        getAllDinosaursUseCaseMock = mock(GetAllDinosaursUseCase.class);
        dinosaurResponseAssemblerMock = mock(DinosaurResponseAssembler.class);
        dinosaurCreationDtoAssemblerMock = mock(DinosaurCreationDtoAssembler.class);
        modifyDinosaurWeightUseCaseMock = mock(ModifyDinosaurWeightUseCase.class);
        dinosaurWeightModificationDtoAssembler = new DinosaurWeightModificationDtoAssembler();
        dinosaursApiEndPoint = new DinosaursApiEndPoint(addDinosaurUseCaseMock, getDinosaurByNameUseCaseMock,
                                                        getAllDinosaursUseCaseMock, dinosaurResponseAssemblerMock,
                                                        dinosaurCreationDtoAssemblerMock,
                                                        modifyDinosaurWeightUseCaseMock,
                                                        dinosaurWeightModificationDtoAssembler);
    }

    @Test
    public void givenADinosaurCreationRequest_whenAddDinosaur_thenDinosaurUseCaseAddDinosaurIsCalled() {
        DinosaurCreationRequest dinosaurCreationRequest = new DinosaurCreationRequest();
        dinosaurCreationRequest.name = "test";
        dinosaurCreationRequest.weight = 555;
        dinosaurCreationRequest.gender = GenderInfo.MALE.gender;
        dinosaurCreationRequest.species = SpeciesInfo.VELOCIRAPTOR.name;

        dinosaursApiEndPoint.addDinosaur(dinosaurCreationRequest);

        verify(addDinosaurUseCaseMock).addDinosaur(any());
    }

    @Test
    public void givenAGetDinosaursRequest_whenDinosaurResource_thenDinosaurUseCaseGetDinosaursIsCalled() {
        when(getAllDinosaursUseCaseMock.getAllDinosaurs()).thenReturn(new ArrayList<>());

        dinosaursApiEndPoint.getAllDinosaurs();

        verify(getAllDinosaursUseCaseMock).getAllDinosaurs();
    }

    @Test
    public void givenAResetRequest_whenGameResourceResetGame_thenGameUseCaseResetIsCalled() {
        String dinosaurName = "Simone Giertz";
        when(getDinosaurByNameUseCaseMock.getDinosaurByName(dinosaurName)).thenReturn(any());

        dinosaursApiEndPoint.getDinosaurByName(dinosaurName);

        verify(getDinosaurByNameUseCaseMock).getDinosaurByName(dinosaurName);
    }

    @Test
    public void whenGetAllDinosaurs_thenReturnStatus200() {
        Response responseFromApi = dinosaursApiEndPoint.getAllDinosaurs();

        assertEquals(Response.Status.OK.getStatusCode(), responseFromApi.getStatus());
    }

    @Test
    public void givenADinosaurInRepository_whenGetDinosaurByName_thenReturnStatus200() {
        String dinosaurName = "Jacques";

        Response responseFromApi = dinosaursApiEndPoint.getDinosaurByName(dinosaurName);

        assertEquals(Response.Status.OK.getStatusCode(), responseFromApi.getStatus());
    }

    @Test
    public void givenAValidDinosaurCreationRequest_whenAddDinosaur_thenReturnStatus200() {
        DinosaurCreationRequest dinosaurCreationRequest = new DinosaurCreationRequest();
        dinosaurCreationRequest.name = "test";
        dinosaurCreationRequest.weight = 555;
        dinosaurCreationRequest.gender = GenderInfo.MALE.gender;
        dinosaurCreationRequest.species = SpeciesInfo.VELOCIRAPTOR.name;

        Response responseFromApi = dinosaursApiEndPoint.addDinosaur(dinosaurCreationRequest);

        assertEquals(Response.Status.OK.getStatusCode(), responseFromApi.getStatus());
    }

    @Test
    public void givenADinosaurWeightModificationRequest_whenModifyDinosaurWeight_thenReturnStatus200() {
        String dinosaurName = "Jacques";
        DinosaurWeightModificationRequest dinosaurWeightModificationRequest = new DinosaurWeightModificationRequest();

        Response responseFromApi = dinosaursApiEndPoint.modifyDinosaurWeight(dinosaurName,
                                                                             dinosaurWeightModificationRequest);

        assertEquals(Response.Status.OK.getStatusCode(), responseFromApi.getStatus());
    }
}
