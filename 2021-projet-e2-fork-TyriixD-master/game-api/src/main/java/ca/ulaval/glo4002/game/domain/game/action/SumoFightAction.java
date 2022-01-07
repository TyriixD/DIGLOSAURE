package ca.ulaval.glo4002.game.domain.game.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.game.Park;

public class SumoFightAction implements Action {
    private final Dinosaur challenger;
    private final Dinosaur challengee;
    private final Park park;

    public SumoFightAction(Dinosaur challenger, Dinosaur challengee, Park park) {
        this.challenger = challenger;
        this.challengee = challengee;
        this.park = park;
    }

    @Override
    public void execute() {
        park.executeSumoFight(challenger, challengee);
    }
}
