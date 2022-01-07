package ca.ulaval.glo4002.game.domain.game;

import ca.ulaval.glo4002.game.application.dinosaur.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurHerd;
import ca.ulaval.glo4002.game.domain.dinosaur.FeedingOrder;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidBabyWeightChangeException;
import ca.ulaval.glo4002.game.domain.game.action.DinosaurCreationAction;
import ca.ulaval.glo4002.game.domain.game.action.ModifyDinosaurWeightAction;
import ca.ulaval.glo4002.game.domain.game.action.ResourceCreationAction;
import ca.ulaval.glo4002.game.domain.game.action.SumoFightAction;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.sumo.SumoRing;
import ca.ulaval.glo4002.game.domain.turn.Turn;

import java.util.List;

import org.glassfish.jersey.internal.guava.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flextrade.jfixture.JFixture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {
    private Turn turnMock;
    private Park parkMock;
    private JFixture fixtureFactory;
    private List<Resources> resources;
    private Game game;
    private Dinosaur dinosaurTest;
    private String testName = "SmolEliane";
    private Weight testWeight = Weight.fromKg(7244);
    private Weight babyTestWeight = Weight.fromKg(89);
    private Weight naturalWeightIncrement = Weight.fromKg(33);
    private GenderInfo testGender = GenderInfo.FEMALE;
    private SpeciesInfo testSpecies = SpeciesInfo.TYRANNOSAURUS_REX;
    private CarnivoreEatingBehaviour eatingBehaviour = new CarnivoreEatingBehaviour();
    private CarnivoreDrinkingBehaviour drinkingBehaviour = new CarnivoreDrinkingBehaviour();

    @BeforeEach
    void setUpGame() {
        fixtureFactory = new JFixture();
        parkMock = mock(Park.class);
        turnMock = mock(Turn.class);
        resources = Lists.newArrayList(fixtureFactory.collections().createCollection(Resources.class));
        dinosaurTest = new Dinosaur(testName, testWeight, testGender, testSpecies, eatingBehaviour,
            drinkingBehaviour);
        game = new Game(parkMock, turnMock);
    }

    @Test
    public void givenAnEndedGame_whenResettingTheGame_thenTurnIsReset() {
        game.reset();

        verify(turnMock).reset();
    }

    @Test
    public void givenAnEndedGame_whenResettingTheGame_thenParkIsReset() {
        game.addDinosaur(dinosaurTest);

        game.reset();

        assertTrue(game.findAllDinosaurs().isEmpty());
    }

    @Test
    public void givenAnOnGoingGame_whenAddingResources_thenCurrentTurnAddResourceCommandIsCalled() {
        game.addResources(resources);

        verify(turnMock, times(resources.size())).addAction(any(ResourceCreationAction.class));
    }

    @Test
    public void whenGetTurnNumber_thenGetNumberFromCurrentTurnIsCalled() {
        game.getTurnNumber();

        verify(turnMock).getNumber();
    }

    @Test
    public void givenAValidDino_whenAddingADino_thenAddCommandFromCurrentTurnIsCalled() {
        Dinosaur dinosaurMock = mock(Dinosaur.class);

        game.addDinosaur(dinosaurMock);

        verify(turnMock).addAction(any(DinosaurCreationAction.class));
    }

    @Test
    public void givenValidChanges_whenModifyingDino_thenModifyCommandFromCurrentTurnIsCalled() {
        Dinosaur dinosaurMock = mock(Dinosaur.class);
        Weight weight = Weight.fromKg(100);

        game.modifyDinosaurWeight(dinosaurMock, weight);

        verify(turnMock).addAction(any(ModifyDinosaurWeightAction.class));
    }

    @Test
    public void givenAChallengerAndChallengee_whenStagingSumoFight_thenSumoFightIsStaged() {
        Dinosaur dinosaurChallenger = mock(Dinosaur.class);
        Dinosaur dinosaurChallengee = mock(Dinosaur.class);

        game.stageSumoFight(dinosaurChallenger, dinosaurChallengee);

        verify(turnMock).addAction(any(SumoFightAction.class));
    }

    @Test
    public void givenDinoAlreadyInHerd_whenAddingDino_thenThrowsException() {
        when(parkMock.checkIfDinosaurExists("SmolEliane")).thenReturn(true);

        assertThrows(DuplicateNameException.class,
            () -> game.addDinosaur(dinosaurTest));

    }

}
