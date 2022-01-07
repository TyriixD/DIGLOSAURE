package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.assemblers.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllDinosaursUseCaseTest {
    private GetAllDinosaursUseCase getAllDinosaursUseCase;
    private Game gameMock;
    private GameRepository gameRepositoryMock;
    private DinosaurDtoAssembler dinosaurDtoAssemblerMock;

    @BeforeEach
    public void setup() {
        dinosaurDtoAssemblerMock = mock(DinosaurDtoAssembler.class);
        gameMock = mock(Game.class);
        gameRepositoryMock = mock(GameRepository.class);
        getAllDinosaursUseCase = new GetAllDinosaursUseCase(gameRepositoryMock, dinosaurDtoAssemblerMock);
    }

    @Test
    public void givenGame_whenGetAllDinosaurs_thenDinosaurDtoAssemblerToDtoIsCalled() {
        when(gameRepositoryMock.find()).thenReturn(gameMock);

        getAllDinosaursUseCase.getAllDinosaurs();

        verify(dinosaurDtoAssemblerMock).toDto(gameMock.findAllDinosaurs());
    }
}
