package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurWeightModificationDto;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ModifyDinosaurWeightUseCaseTest {
    private ModifyDinosaurWeightUseCase modifyDinosaurWeightUseCase;
    private Game gameMock;
    private DinosaurWeightModificationDto weightModificationDto;
    private Dinosaur dinosaurMock;
    private GameRepository gameRepositoryMock;

    @BeforeEach
    public void setup() {
        String dinosaurName = "name";
        int weightDifference = 100;
        weightModificationDto = new DinosaurWeightModificationDto(dinosaurName, Weight.fromKg(weightDifference));
        gameRepositoryMock = mock(GameRepository.class);
        dinosaurMock = mock(Dinosaur.class);
        gameMock = mock(Game.class);
        modifyDinosaurWeightUseCase = new ModifyDinosaurWeightUseCase(gameRepositoryMock);
        when(gameRepositoryMock.find()).thenReturn(gameMock);
        when(gameMock.findDinosaurByName(weightModificationDto.getName())).thenReturn(dinosaurMock);
    }

    @Test
    public void whenModifyDinosaurWeight_thenGameModifyDinosaurWeightIsCalled() {
        modifyDinosaurWeightUseCase.modifyDinosaurWeight(weightModificationDto);

        verify(gameMock).validateAndApplyDinoForWeightChange(weightModificationDto.getName(),weightModificationDto.getWeightDifference());
    }

    @Test
    public void whenModifyDinosaurWeight_thenGameIsSaved() {
        modifyDinosaurWeightUseCase.modifyDinosaurWeight(weightModificationDto);

        verify(gameRepositoryMock).save(gameMock);
    }
}
