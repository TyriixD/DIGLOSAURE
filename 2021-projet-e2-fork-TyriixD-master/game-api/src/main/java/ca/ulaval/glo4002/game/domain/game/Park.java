package ca.ulaval.glo4002.game.domain.game;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurHerd;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;
import ca.ulaval.glo4002.game.domain.sumo.SumoFightPrediction;
import ca.ulaval.glo4002.game.domain.sumo.SumoRing;

import java.io.Serializable;
import java.util.List;

public class Park implements Serializable {
    private final Pantry pantry;
    private final DinosaurHerd dinosaurHerd;
    private final SumoRing sumoRing;

    public Park(Pantry pantry, DinosaurHerd dinosaurHerd, SumoRing sumoRing) {
        this.pantry = pantry;
        this.dinosaurHerd = dinosaurHerd;
        this.sumoRing = sumoRing;
    }

    public void addResources(Resources resources) {
        pantry.addResources(resources);
    }

    public List<Resources> findAllResources() {
        return pantry.getAllResources();
    }

    public void addDinosaur(Dinosaur dinosaur) {
        dinosaurHerd.addDinosaur(dinosaur);
    }

    public boolean checkIfDinosaurExists(String name) {
        return dinosaurHerd.checkIfDinosaurExists(name);
    }

    public Dinosaur findDinosaurByName(String name) {
        return dinosaurHerd.getDinosaurByName(name);
    }

    public List<Dinosaur> findAllDinosaurs() {
        return dinosaurHerd.getAllDinosaurs();
    }

    public void feedDinosaurs() {
        ResourcesNeeds order = dinosaurHerd.calculateResourcesNeeds();
        LunchBox lunchBox = pantry.prepareLunchBox(order);
        dinosaurHerd.feedDinosaurs(lunchBox);
    }

    public SumoFightPrediction predictSumoFightOutcome(Dinosaur challenger, Dinosaur challengee) {
        return sumoRing.predictSumoFightOutcome(challenger, challengee);
    }

    public void executeSumoFight(Dinosaur challenger, Dinosaur challengee) {
        sumoRing.executeFight(challenger, challengee);
    }

    public void removeExpiredResources() {
        pantry.removeExpiredResources();
    }

    public void removeStarvedDinosaurs() {
        dinosaurHerd.removeStarvedDinosaurs();
    }

    public void removeSumoFightLosers() {
        dinosaurHerd.removeSumoFightLosers();
    }

    public void removeOrphanDinosaurs() {
        dinosaurHerd.removeOrphanDinosaurs();
    }

    public void reset() {
        pantry.reset();
        dinosaurHerd.reset();
        sumoRing.reset();
    }

    public void modifyDinosaurWeight(Dinosaur dinosaur, Weight weightDifference) {
        dinosaurHerd.modifyDinosaurWeight(dinosaur, weightDifference);
    }

    public void growBabyDinosaurs() {
        dinosaurHerd.growBabyDinosaurs();
    }

}
