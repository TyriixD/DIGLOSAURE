package ca.ulaval.glo4002.game.domain.game.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.game.Park;

public class ModifyDinosaurWeightAction implements Action {
    private final Dinosaur dinosaur;
    private final Weight weightDifference;
    private final Park park;

    public ModifyDinosaurWeightAction(Dinosaur dinosaur, Weight weightDifference, Park park) {
        this.dinosaur = dinosaur;
        this.weightDifference = weightDifference;
        this.park = park;
    }

    @Override
    public void execute() {
        park.modifyDinosaurWeight(dinosaur, weightDifference);
    }
}
