package ca.ulaval.glo4002.game.domain.sumo;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.sumo.exceptions.ArmsTooShortException;
import ca.ulaval.glo4002.game.domain.sumo.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.domain.sumo.exceptions.MaxCombatsReachedException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SumoRingTest {
    private SumoRing sumoRing;
    private String weakDinosaurName;
    private String strongDinosaurName;
    private Dinosaur weakDinosaur;
    private Dinosaur strongDinosaur;
    private int minimumWeight;

    @BeforeEach
    public void setUpDinosaurResource() {
        sumoRing = new SumoRing();

        String species = "Velociraptor";
        minimumWeight = 100;

        strongDinosaurName = "Valentine";
        strongDinosaur = new Dinosaur(strongDinosaurName, Weight.fromKg(2 * minimumWeight),
                                      GenderInfo.getGenderInfo("f"), SpeciesInfo.getSpeciesInfo(species),
                                      new CarnivoreEatingBehaviour(), new CarnivoreDrinkingBehaviour());

        weakDinosaurName = "Rejean";
        weakDinosaur = new Dinosaur(weakDinosaurName, Weight.fromKg(minimumWeight), GenderInfo.getGenderInfo("m"),
                                    SpeciesInfo.getSpeciesInfo(species), new CarnivoreEatingBehaviour(),
                                    new CarnivoreDrinkingBehaviour());
    }

    @Test
    public void givenStrongChallengerAndWeakChallengee_whenPredictSumoFightOutcome_thenChallengerIsPredictedToWin() {
        SumoFightPrediction prediction = sumoRing.predictSumoFightOutcome(strongDinosaur, weakDinosaur);

        assertEquals(strongDinosaurName, prediction.predictedWinnerName);
    }

    @Test
    public void givenStrongChallengeeAndWeakChallenger_whenPredictSumoFightOutcome_thenChallengeeIsPredictedToWin() {
        SumoFightPrediction prediction = sumoRing.predictSumoFightOutcome(weakDinosaur, strongDinosaur);

        assertEquals(strongDinosaurName, prediction.predictedWinnerName);
    }

    @Test
    public void givenEquallyStrongDinosaurs_whenPredictSumoFightOutcome_thenTieFightIsPredicted() {
        Dinosaur equallyStrongDinosaur = new Dinosaur(strongDinosaurName, Weight.fromKg(2 * minimumWeight),
                                                      GenderInfo.getGenderInfo("f"),
                                                      SpeciesInfo.getSpeciesInfo("Velociraptor"),
                                                      new CarnivoreEatingBehaviour(), new CarnivoreDrinkingBehaviour());

        SumoFightPrediction prediction = sumoRing.predictSumoFightOutcome(strongDinosaur, equallyStrongDinosaur);

        String tieWinner = "tie";
        assertEquals(tieWinner, prediction.getPredictedWinner());
    }

    @Test
    public void givenFightWithTyrannosaurusRex_whenPredictSumoFightOutcome_thenArmsTooShortExceptionIsThrown() {
        Dinosaur tyrannosaurusRex = new Dinosaur("Simon Asselin", Weight.fromKg(100), GenderInfo.getGenderInfo("m"),
                                                 SpeciesInfo.getSpeciesInfo("Tyrannosaurus Rex"),
                                                 new CarnivoreEatingBehaviour(), new CarnivoreDrinkingBehaviour());

        assertThrows(ArmsTooShortException.class,
                     () -> sumoRing.predictSumoFightOutcome(tyrannosaurusRex, givenRandomDinosaur()));
    }

    @Test
    public void givenDinosaurAlreadyFighting_whenPredictSumoFightOutcome_thenDinosaurAlreadyParticipatingExceptionIsThrown() {
        sumoRing.predictSumoFightOutcome(strongDinosaur, givenRandomDinosaur());

        assertThrows(DinosaurAlreadyParticipatingException.class,
                     () -> sumoRing.predictSumoFightOutcome(strongDinosaur, givenRandomDinosaur()));
    }

    @Test
    public void givenDinosaurAlreadyFighting_whenReset_thenDinosaurCanParticipateAgain() {
        sumoRing.predictSumoFightOutcome(strongDinosaur, weakDinosaur);

        sumoRing.reset();

        sumoRing.predictSumoFightOutcome(strongDinosaur, weakDinosaur);
    }

    @Test
    public void givenTwoPendingFights_whenPredictSumoFightOutcome_thenMaxCombatsReachedExceptionIsThrown() {
        sumoRing.predictSumoFightOutcome(givenRandomDinosaur(), givenRandomDinosaur());
        sumoRing.predictSumoFightOutcome(givenRandomDinosaur(), givenRandomDinosaur());

        assertThrows(MaxCombatsReachedException.class,
                     () -> sumoRing.predictSumoFightOutcome(givenRandomDinosaur(), givenRandomDinosaur()));
    }

    @Test
    public void givenMaxFightsReached_whenExecuteFight_thenNewFightCanBePredictedWithoutExceedingLimit() {
        Dinosaur firstFighter = givenRandomDinosaur();
        Dinosaur secondFighter = givenRandomDinosaur();
        sumoRing.predictSumoFightOutcome(firstFighter, secondFighter);
        sumoRing.predictSumoFightOutcome(givenRandomDinosaur(), givenRandomDinosaur());

        sumoRing.executeFight(firstFighter, secondFighter);

        sumoRing.predictSumoFightOutcome(givenRandomDinosaur(), givenRandomDinosaur());
    }

    @Test
    public void givenMaxFightsReached_whenReset_thenTwoNewFightsCanBePredictedWithoutExceedingLimit() {
        sumoRing.predictSumoFightOutcome(givenRandomDinosaur(), givenRandomDinosaur());
        sumoRing.predictSumoFightOutcome(givenRandomDinosaur(), givenRandomDinosaur());

        sumoRing.reset();

        sumoRing.predictSumoFightOutcome(givenRandomDinosaur(), givenRandomDinosaur());
        sumoRing.predictSumoFightOutcome(givenRandomDinosaur(), givenRandomDinosaur());
    }

    private Dinosaur givenRandomDinosaur() {
        return new Dinosaur("Jean-Pierre", Weight.fromKg(100), GenderInfo.getGenderInfo("m"),
                            SpeciesInfo.getSpeciesInfo("Velociraptor"), new CarnivoreEatingBehaviour(),
                            new CarnivoreDrinkingBehaviour());
    }
}
