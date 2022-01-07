package ca.ulaval.glo4002.game.domain.game.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.game.Park;

public class DinosaurCreationAction implements Action {
    private final Dinosaur dinosaur;
    private final Park park;

    public DinosaurCreationAction(Dinosaur dinosaur, Park park) {
        this.dinosaur = dinosaur;
        this.park = park;
    }

    @Override
    public void execute() {
        park.addDinosaur(dinosaur);
    }
}
