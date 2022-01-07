package ca.ulaval.glo4002.game.domain.game;

import ca.ulaval.glo4002.game.application.dinosaur.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.application.turn.GameMemento;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.game.action.DinosaurCreationAction;
import ca.ulaval.glo4002.game.domain.game.action.ModifyDinosaurWeightAction;
import ca.ulaval.glo4002.game.domain.game.action.ResourceCreationAction;
import ca.ulaval.glo4002.game.domain.game.action.SumoFightAction;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.sumo.SumoFightPrediction;
import ca.ulaval.glo4002.game.domain.turn.Turn;

import java.util.List;

public class Game {
    private Park park;
    private Turn turn;

    public Game(Park park, Turn turn) {
        this.park = park;
        this.turn = turn;
    }

    public void executeTurn() {
        turn.execute();
    }
    public void unExecuteTurn(){
        turn.unExecute();
    }

    public void reset() {
        turn.reset();
        park.reset();
    }

    public void addResources(List<Resources> resources) {
        for (Resources resource : resources) {
            turn.addAction(new ResourceCreationAction(resource, park));
        }
    }

    public List<Resources> findAllResources() {
        return park.findAllResources();
    }

    public void addDinosaur(Dinosaur dinosaur) {
        if (checkIfDinosaurExists(dinosaur.getName())) {
            throw new DuplicateNameException();
        }
        turn.addAction(new DinosaurCreationAction(dinosaur, park));
    }

    public boolean checkIfDinosaurExists(String name) {
        return park.checkIfDinosaurExists(name);
    }

    public Dinosaur findDinosaurByName(String name) {
        return park.findDinosaurByName(name);
    }

    public List<Dinosaur> findAllDinosaurs() {
        return park.findAllDinosaurs();
    }

    public int getTurnNumber() {
        return turn.getNumber();
    }

    public SumoFightPrediction predictSumoFightOutcome(String challengerName, String challengeeName) {
        Dinosaur challenger = findDinosaurByName(challengerName);
        Dinosaur challengee = findDinosaurByName(challengeeName);
        stageSumoFight(challenger, challengee);
        return park.predictSumoFightOutcome(challenger, challengee);
    }

    public void stageSumoFight(Dinosaur challenger, Dinosaur challengee) {
        turn.addAction(new SumoFightAction(challenger, challengee, park));
    }

    public void validateAndApplyDinoForWeightChange(String dinoName,Weight weightDifference) {
        Dinosaur dinosaur = findDinosaurByName(dinoName);
        dinosaur.validateWeightModification(weightDifference);
        modifyDinosaurWeight(dinosaur,weightDifference);
    }

    public void modifyDinosaurWeight(Dinosaur dinosaur, Weight weightDifference) {
        turn.addAction(new ModifyDinosaurWeightAction(dinosaur, weightDifference, park));
    }
    public void setTurn(Park park) {
        int previousNumber = turn.getNumber();
        this.turn=new Turn(park);
        this.turn.setNumber(previousNumber);
    }

    public void recoverState(Park park) {
        this.park = park;
        setTurn(park);

    }
    public Park getPark(){
        return park;
    }

}
