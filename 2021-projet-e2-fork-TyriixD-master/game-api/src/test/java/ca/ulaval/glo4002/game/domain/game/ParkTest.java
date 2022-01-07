package ca.ulaval.glo4002.game.domain.game;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurHerd;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;
import ca.ulaval.glo4002.game.domain.sumo.SumoRing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ParkTest {
    private final String dinosaurName = "name";
    private Park park;
    private Pantry pantryMock;
    private DinosaurHerd dinosaurHerdMock;
    private SumoRing sumoRingMock;
    private Resources resourcesMock;
    private Dinosaur dinosaurMocK;
    private Dinosaur challengerMock;
    private Dinosaur challengeeMock;

    @BeforeEach
    void setUpPark() {
        resourcesMock = mock(Resources.class);
        pantryMock = mock(Pantry.class);
        dinosaurHerdMock = mock(DinosaurHerd.class);
        sumoRingMock = mock(SumoRing.class);
        park = new Park(pantryMock, dinosaurHerdMock, sumoRingMock);
        dinosaurMocK = mock(Dinosaur.class);
        challengerMock = mock(Dinosaur.class);
        challengeeMock = mock(Dinosaur.class);
    }

    @Test
    public void whenAddResources_thenPantryAddResourcesIsCalled() {
        park.addResources(resourcesMock);

        verify(pantryMock).addResources(resourcesMock);
    }

    @Test
    public void whenFindAllResources_thenPantryGetAllResourcesIsCalled() {
        park.findAllResources();

        verify(pantryMock).getAllResources();
    }

    @Test
    public void whenFindDinosaurByName_thenDinosaurHerdGetDinosaurByNameIsCalled() {
        park.findDinosaurByName(dinosaurName);

        verify(dinosaurHerdMock).getDinosaurByName(dinosaurName);
    }

    @Test
    public void whenCheckIfDinosaurExists_thenDinosaurHerdCheckIfDinosaurExistsIsCalled() {
        park.checkIfDinosaurExists(dinosaurName);

        verify(dinosaurHerdMock).checkIfDinosaurExists(dinosaurName);
    }

    @Test
    public void whenFindAllDinosaurs_thenDinosaurHerdGetAllDinosaursIsCalled() {
        park.findAllDinosaurs();

        verify(dinosaurHerdMock).getAllDinosaurs();
    }

    @Test
    public void whenAddDinosaur_thenDinosaurHerdAddDinosaurIsCalled() {
        park.addDinosaur(dinosaurMocK);

        verify(dinosaurHerdMock).addDinosaur(dinosaurMocK);
    }

    @Test
    public void whenFeedDinosaurs_thenDinosaurHerdAFeedDinosaursIsCalled() {
        LunchBox lunchBox = mock(LunchBox.class);
        ResourcesNeeds order = mock(ResourcesNeeds.class);
        when(dinosaurHerdMock.calculateResourcesNeeds()).thenReturn(order);
        when(pantryMock.prepareLunchBox(order)).thenReturn(lunchBox);

        park.feedDinosaurs();

        verify(dinosaurHerdMock).feedDinosaurs(lunchBox);
    }

    @Test
    public void whenPredictSumoFightOutcome_thenSumoRingPredictSumoFightOutcomeIsCalled() {
        park.predictSumoFightOutcome(challengeeMock, challengerMock);

        verify(sumoRingMock).predictSumoFightOutcome(challengeeMock, challengerMock);
    }

    @Test
    public void whenExecuteSumoFight_thenSumoRingExecuteFightIsCalled() {
        park.executeSumoFight(challengeeMock, challengerMock);

        verify(sumoRingMock).executeFight(challengeeMock, challengerMock);
    }

    @Test
    public void whenResettingThePark_thenPantrySumoRingDinosaurHerdResetIsCalled() {
        park.reset();

        verify(pantryMock).reset();
        verify(dinosaurHerdMock).reset();
        verify(sumoRingMock).reset();
    }

    @Test
    public void givenWeightAndDinosaurName_whenModifyDinosaurWeight_thenDinosaurHerdModifyDinosaurWeightIsCalled() {
        Weight weightDifference = Weight.fromKg(100);

        park.modifyDinosaurWeight(dinosaurMocK, weightDifference);

        verify(dinosaurHerdMock).modifyDinosaurWeight(dinosaurMocK, weightDifference);
    }

    @Test
    public void whenRemoveExpiredResources_thenPantryRemoveExpiredResourcesIsCalled() {
        park.removeExpiredResources();

        verify(pantryMock).removeExpiredResources();
    }

    @Test
    public void whenRemoveStarvedDinosaurs_thenDinosaurHerdRemoveStarvedDinosaursIsCalled() {
        park.removeStarvedDinosaurs();

        verify(dinosaurHerdMock).removeStarvedDinosaurs();
    }

    @Test
    public void whenRemoveSumoFightLosers_thenDinosaurHerdRemoveSumoFightLosersIsCalled() {
        park.removeSumoFightLosers();

        verify(dinosaurHerdMock).removeSumoFightLosers();
    }

    @Test
    public void whenRemoveOrphanDinosaurs_thenDinosaurHerdRemoveOrphanDinosaursIsCalled() {
        park.removeOrphanDinosaurs();

        verify(dinosaurHerdMock).removeOrphanDinosaurs();
    }
}
