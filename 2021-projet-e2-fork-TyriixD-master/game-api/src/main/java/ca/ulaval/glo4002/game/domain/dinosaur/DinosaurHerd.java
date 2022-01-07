package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.exceptions.NonExistentNameException;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DinosaurHerd implements Serializable {
    private  Map<String, Dinosaur> dinosaurs = new TreeMap<>();
    private final FeedingOrder carnivoreFeedingOrderer;
    private final FeedingOrder herbivoreFeedingOrderer;

    public DinosaurHerd(FeedingOrder carnivoreFeedingOrderer, FeedingOrder herbivoreFeedingOrderer) {
        this.carnivoreFeedingOrderer = carnivoreFeedingOrderer;
        this.herbivoreFeedingOrderer = herbivoreFeedingOrderer;
    }

    public void addDinosaur(Dinosaur dinosaur) {
        if (!dinosaurs.containsKey(dinosaur.getName())) {
            dinosaurs.put(dinosaur.getName(), dinosaur);
        }
    }

    public List<Dinosaur> getAllDinosaurs() {
        return new ArrayList<>(dinosaurs.values());
    }

    public Dinosaur getDinosaurByName(String name) {
        if (!dinosaurs.containsKey(name)) {
            throw new NonExistentNameException();
        }

        return dinosaurs.get(name);
    }

    public boolean checkIfDinosaurExists(String name) {
        return dinosaurs.containsKey(name);
    }

    public ResourcesNeeds calculateResourcesNeeds() {
        int requiredBurgerAmount = 0;
        int requiredSaladAmount = 0;
        int requiredWaterAmountForCarnivores = 0;
        int requiredWaterAmountForHerbivores = 0;

        for (Dinosaur dinosaur : dinosaurs.values()) {
            ResourcesNeeds foodNeeds = dinosaur.getRequiredFood();
            ResourcesNeeds waterNeeds = dinosaur.getRequiredWater();

            requiredBurgerAmount += foodNeeds.burgerQuantity;
            requiredSaladAmount += foodNeeds.saladQuantity;
            requiredWaterAmountForCarnivores += waterNeeds.waterForCarnivoresQuantity;
            requiredWaterAmountForHerbivores += waterNeeds.waterForHerbivoresQuantity;
        }

        return new ResourcesNeeds(requiredBurgerAmount, requiredSaladAmount, requiredWaterAmountForCarnivores,
                                  requiredWaterAmountForHerbivores);
    }

    public void feedDinosaurs(LunchBox lunchBox) {
        feedDinosaurs(carnivoreFeedingOrderer.orderDinosaurs(getAllDinosaurs()), "meat", lunchBox);
        feedDinosaurs(herbivoreFeedingOrderer.orderDinosaurs(getAllDinosaurs()), "plant", lunchBox);
    }

    private void feedDinosaurs(List<Dinosaur> dinosaurs, String resourceType, LunchBox lunchBox) {
        dinosaurs.forEach(dinosaur -> dinosaur.drinkAndEat(resourceType, lunchBox));
    }

    public void removeStarvedDinosaurs() {
        List<String> deadDinosaurNames = new ArrayList<>();

        for (Dinosaur dinosaur : dinosaurs.values()) {
            if (dinosaur.isStarving()) {
                dinosaur.informChildrenThatAParentHasDied();
                deadDinosaurNames.add(dinosaur.getName());
            }
        }

        deleteDeadDinosaurs(deadDinosaurNames);
    }

    public void removeOrphanDinosaurs() {
        List<String> deadDinosaurNames = new ArrayList<>();

        for (Dinosaur dinosaur : dinosaurs.values()) {
            if (dinosaur.isOrphan()) {
                dinosaur.informChildrenThatAParentHasDied();
                deadDinosaurNames.add(dinosaur.getName());
            }
        }

        deleteDeadDinosaurs(deadDinosaurNames);
    }

    public void removeSumoFightLosers() {
        List<String> deadDinosaurNames = new ArrayList<>();

        for (Dinosaur dinosaur : dinosaurs.values()) {
            if (dinosaur.isDefeatedInASumoFight()) {
                dinosaur.informChildrenThatAParentHasDied();
                deadDinosaurNames.add(dinosaur.getName());
            }
        }

        deleteDeadDinosaurs(deadDinosaurNames);
    }

    private void deleteDeadDinosaurs(List<String> deadDinosaurNames) {
        for (String deadDinosaurName : deadDinosaurNames) {
            dinosaurs.remove(deadDinosaurName);
        }
    }

    public void reset() {
        dinosaurs.clear();
    }

    public void modifyDinosaurWeight(Dinosaur dinosaur, Weight weightDifference) {
        dinosaur.modifyWeight(weightDifference);
    }

    public void growBabyDinosaurs() {
        for (Dinosaur dinosaur : dinosaurs.values()) {
            dinosaur.grow();
        }
    }

}
