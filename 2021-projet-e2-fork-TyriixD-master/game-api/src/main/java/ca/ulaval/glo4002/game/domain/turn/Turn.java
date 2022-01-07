package ca.ulaval.glo4002.game.domain.turn;

import ca.ulaval.glo4002.game.domain.game.Park;
import ca.ulaval.glo4002.game.domain.game.action.Action;
import ca.ulaval.glo4002.game.domain.game.consequence.AddTurnResourcesConsequence;
import ca.ulaval.glo4002.game.domain.game.consequence.Consequence;
import ca.ulaval.glo4002.game.domain.game.consequence.FeedDinosaursConsequence;
import ca.ulaval.glo4002.game.domain.game.consequence.GrowBabyDinosaursConsequence;
import ca.ulaval.glo4002.game.domain.game.consequence.RemoveExpiredResourcesConsequence;
import ca.ulaval.glo4002.game.domain.game.consequence.RemoveOrphanDinosaursConsequence;
import ca.ulaval.glo4002.game.domain.game.consequence.RemoveStarvedDinosaursConsequence;
import ca.ulaval.glo4002.game.domain.game.consequence.RemoveSumoFightLosersConsequence;

import java.util.ArrayList;
import java.util.List;

public class Turn implements Cloneable{
    private final List<Action> actions;
    private final List<Consequence> consequences;
    private int number;

    public Turn(Park park) {
        this.number = 1;
        this.actions = new ArrayList<>();
        this.consequences = new ArrayList<>();
        addTurnBasedConsequences(park);
    }

    public int getNumber() {
        return number;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    private void addTurnBasedConsequences(Park park) {
        consequences.add(new AddTurnResourcesConsequence(park));
        consequences.add(new RemoveExpiredResourcesConsequence(park));
        consequences.add(new FeedDinosaursConsequence(park));
        consequences.add(new RemoveSumoFightLosersConsequence(park));
        consequences.add(new RemoveStarvedDinosaursConsequence(park));
        consequences.add(new RemoveOrphanDinosaursConsequence(park));
        consequences.add(new GrowBabyDinosaursConsequence(park));
    }

    public void execute() {
        executeActions();
        executeConsequences();
        ++number;
    }
    public void unExecute(){
        --number;
    }

    public void executeActions() {
        for (Action action : actions) {
            action.execute();
        }
        actions.clear();
    }

    public void executeConsequences() {
        for (Consequence consequence : consequences) {
            consequence.apply();
        }
    }

    public boolean isCommandsEmpty() {
        return actions.isEmpty();
    }

    public void reset() {
        number = 1;
        actions.clear();
    }
    public void setNumber(int number) {
        this.number = number;
    }

}
