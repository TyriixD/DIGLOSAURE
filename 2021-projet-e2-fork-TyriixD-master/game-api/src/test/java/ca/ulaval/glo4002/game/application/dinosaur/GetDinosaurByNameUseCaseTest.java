package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.assemblers.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetDinosaurByNameUseCaseTest {
    private final String name = "name";
    private GameRepository gameRepositoryMock;
    private Game gameMock;
    private GetDinosaurByNameUseCase getDinosaurByNameUseCase;
    private DinosaurDtoAssembler dinosaurDtoAssemblerMock;

    @BeforeEach
    public void setup() {
        gameRepositoryMock = mock(GameRepository.class);
        dinosaurDtoAssemblerMock = mock(DinosaurDtoAssembler.class);
        gameMock = mock(Game.class);
        getDinosaurByNameUseCase = new GetDinosaurByNameUseCase(gameRepositoryMock, dinosaurDtoAssemblerMock);
    }

    @Test
    public void givenGame_whenGetDinosaurByName_thenAssemblerToDtoIsCalled() {
        Dinosaur dinosaur = mock(Dinosaur.class);
        when(gameRepositoryMock.find()).thenReturn(gameMock);
        when(gameMock.findDinosaurByName(name)).thenReturn(dinosaur);

        getDinosaurByNameUseCase.getDinosaurByName(name);

        verify(dinosaurDtoAssemblerMock).toDto(dinosaur);
    }

    @Test
    public void givenGame_whenGetDinosaurByName_thenGameFindDinosaurByNameIsCalled() {
        when(gameRepositoryMock.find()).thenReturn(gameMock);

        getDinosaurByNameUseCase.getDinosaurByName(name);

        verify(gameMock).findDinosaurByName(name);
    }
}
