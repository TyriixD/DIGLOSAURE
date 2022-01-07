package ca.ulaval.glo4002.game.application.sumo;

import ca.ulaval.glo4002.game.application.sumo.assemblers.SumoFightPredictionDtoAssembler;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightCreationDto;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.sumo.SumoFightPrediction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SumoFightDinosaurUseCaseTest {
    private GameRepository gameRepositoryMock;
    private SumoFightPredictionDtoAssembler sumoFightPredictionDtoAssemblerMock;
    private SumoFightDinosaurUseCase sumoFightDinosaurUseCase;
    private SumoFightCreationDto fightCreationDto;
    private Game gameMock;
    private String weakerDinosaurName;
    private String strongerDinosaurName;
    private Dinosaur weakerDinosaur;
    private Dinosaur strongerDinosaur;

    @BeforeEach
    public void setUpSumoUseCase() {
        gameRepositoryMock = mock(GameRepository.class);
        sumoFightPredictionDtoAssemblerMock = mock(SumoFightPredictionDtoAssembler.class);
        sumoFightDinosaurUseCase = new SumoFightDinosaurUseCase(gameRepositoryMock,
                                                                sumoFightPredictionDtoAssemblerMock);
        gameMock = mock(Game.class);

        int minimumWeight = 100;
        weakerDinosaurName = "Rejean";
        strongerDinosaurName = "Valentine";
        weakerDinosaur = givenDinosaur(weakerDinosaurName, minimumWeight);
        strongerDinosaur = givenDinosaur(strongerDinosaurName, 2 * minimumWeight);

        fightCreationDto = new SumoFightCreationDto(strongerDinosaurName, weakerDinosaurName);

        when(gameMock.findDinosaurByName(strongerDinosaurName)).thenReturn(strongerDinosaur);
        when(gameMock.findDinosaurByName(weakerDinosaurName)).thenReturn(weakerDinosaur);
        when(gameRepositoryMock.find()).thenReturn(gameMock);

        when(gameMock.predictSumoFightOutcome(strongerDinosaur.getName(), weakerDinosaur.getName())).thenReturn(
            new SumoFightPrediction(strongerDinosaurName));
    }

    private Dinosaur givenDinosaur(String name, int weightInKg) {
        return new Dinosaur(name, Weight.fromKg(weightInKg), GenderInfo.getGenderInfo("m"),
                            SpeciesInfo.getSpeciesInfo("Velociraptor"), new CarnivoreEatingBehaviour(),
                            new CarnivoreDrinkingBehaviour());
    }

    @Test
    public void whenStageSumoFight_thenGameRepositoryFindIsCalled() {
        sumoFightDinosaurUseCase.stageSumoFight(fightCreationDto);

        verify(gameRepositoryMock).find();
    }

    @Test
    public void whenStageSumoFight_thenGameRepositorySaveIsCalled() {
        sumoFightDinosaurUseCase.stageSumoFight(fightCreationDto);

        verify(gameRepositoryMock).save(gameMock);
    }

    @Test
    public void whenStageSumoFight_thenGamePredictSumoFightOutcomeIsCalled() {
        sumoFightDinosaurUseCase.stageSumoFight(fightCreationDto);

        verify(gameMock).predictSumoFightOutcome(strongerDinosaur.getName(), weakerDinosaur.getName());
    }

    @Test
    public void whenStageSumoFight_thenSumoPredictionAssemblerIsCalledIsCalled() {
        sumoFightDinosaurUseCase.stageSumoFight(fightCreationDto);

        verify(sumoFightPredictionDtoAssemblerMock).toPredictionDto(any(SumoFightPrediction.class));
    }
}
