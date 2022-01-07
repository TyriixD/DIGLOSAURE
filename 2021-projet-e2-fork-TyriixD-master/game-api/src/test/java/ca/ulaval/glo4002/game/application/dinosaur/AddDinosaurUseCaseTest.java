package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.application.turn.GameMemento;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddDinosaurUseCaseTest {
    private GameRepository gameRepositoryMock;
    private GameMemento gameMementoMock;
    private DinosaurFactory dinosaurFactoryMock;
    private AddDinosaurUseCase addDinosaurUseCase;
    private Game gameMock;
    private DinosaurCreationDto dinosaurCreationDto;
    private Dinosaur dinosaurMock;

    @BeforeEach
    public void setup() {
        dinosaurCreationDto = new DinosaurCreationDto("name", Weight.fromKg(100), GenderInfo.FEMALE,
                                                      SpeciesInfo.ALLOSAURUS);
        gameMock = mock(Game.class);
        dinosaurFactoryMock = mock(DinosaurFactory.class);
        gameRepositoryMock = mock(GameRepository.class);
        addDinosaurUseCase = new AddDinosaurUseCase(gameRepositoryMock, dinosaurFactoryMock,gameMementoMock);
        dinosaurMock = mock(Dinosaur.class);
        when(gameRepositoryMock.find()).thenReturn(gameMock);
    }

    @Test
    public void whenAddDinosaur_thenDinosaurFactoryIsCalled() {
        addDinosaurUseCase.addDinosaur(dinosaurCreationDto);

        verify(dinosaurFactoryMock).create(dinosaurCreationDto.getName(), dinosaurCreationDto.getWeight(),
                                           dinosaurCreationDto.getGender(), dinosaurCreationDto.getSpecies());
    }

    @Test
    public void whenAddDinosaur_thenGameAddDinosaurIsCalled() {
        when(dinosaurFactoryMock.create(dinosaurCreationDto.getName(), dinosaurCreationDto.getWeight(),
                                        dinosaurCreationDto.getGender(), dinosaurCreationDto.getSpecies())).thenReturn(
            dinosaurMock);

        addDinosaurUseCase.addDinosaur(dinosaurCreationDto);

        verify(gameMock).addDinosaur(dinosaurMock);
    }

    @Test
    public void whenAddDinosaur_thenGameIsSaved() {
        addDinosaurUseCase.addDinosaur(dinosaurCreationDto);

        verify(gameRepositoryMock).save(gameMock);
    }

}
