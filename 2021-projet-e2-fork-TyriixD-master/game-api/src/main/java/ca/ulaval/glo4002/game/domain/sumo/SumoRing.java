package ca.ulaval.glo4002.game.domain.sumo;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.sumo.exceptions.ArmsTooShortException;
import ca.ulaval.glo4002.game.domain.sumo.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.domain.sumo.exceptions.MaxCombatsReachedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SumoRing implements Serializable {
    private static final int MAXIMUM_FIGHTS_PER_TURN = 2;
    private final List<Dinosaur> participatingDinosaurs = new ArrayList<>();
    private int fightCounter = 0;

    public SumoFightPrediction predictSumoFightOutcome(Dinosaur challenger, Dinosaur challengee) {
        validateDinosaursAreAllowedToFight(challenger, challengee);
        registerFighters(challenger, challengee);

        return predictWinner(challenger, challengee);
    }

    private void validateDinosaursAreAllowedToFight(Dinosaur challenger, Dinosaur challengee) {
        validateDinosaurIsPhysicallyAbleToFight(challenger);
        validateDinosaurIsPhysicallyAbleToFight(challengee);

        if (participatingDinosaurs.contains(challenger) || participatingDinosaurs.contains(challengee)) {
            throw new DinosaurAlreadyParticipatingException();
        }

        if (fightCounter >= MAXIMUM_FIGHTS_PER_TURN) {
            throw new MaxCombatsReachedException();
        }
    }

    private void validateDinosaurIsPhysicallyAbleToFight(Dinosaur fighter) {
        if (fighter.getSpecies().equals(SpeciesInfo.TYRANNOSAURUS_REX)) {
            throw new ArmsTooShortException();
        }
    }

    private void registerFighters(Dinosaur challenger, Dinosaur challengee) {
        participatingDinosaurs.add(challenger);
        participatingDinosaurs.add(challengee);
        ++fightCounter;
    }

    private SumoFightPrediction predictWinner(Dinosaur challenger, Dinosaur challengee) {
        if (challenger.isStrongerThan(challengee)) {
            return new SumoFightPrediction(challenger.getName());
        } else if (challenger.isWeakerThan(challengee)) {
            return new SumoFightPrediction(challengee.getName());
        }

        return new SumoFightPrediction("tie");
    }

    public void executeFight(Dinosaur challenger, Dinosaur challengee) {
        participatingDinosaurs.remove(challenger);
        participatingDinosaurs.remove(challengee);
        --fightCounter;

        challenger.fight(challengee);
    }

    public void reset() {
        participatingDinosaurs.clear();
        fightCounter = 0;
    }
}
