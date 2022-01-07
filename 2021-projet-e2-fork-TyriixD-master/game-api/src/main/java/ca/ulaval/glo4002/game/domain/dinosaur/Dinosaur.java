package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.DrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.EatingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidBabyWeightChangeException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightChangeException;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dinosaur implements Comparable<Dinosaur>, Serializable {
    private final SpeciesInfo species;
    private final String name;
    private final EatingBehaviour eatingBehaviour;
    private final DrinkingBehaviour drinkingBehaviour;
    private final GenderInfo gender;
    private List<Dinosaur> children = new ArrayList<>();
    private Weight weight;
    private boolean isFed = true;
    private boolean isHydrated = true;
    private boolean isDefeatedInASumoFight = false;
    private boolean isMotherDead = false;
    private boolean isFatherDead = false;

    public Dinosaur(String name, Weight weight, GenderInfo genderInfo, SpeciesInfo species,
                    EatingBehaviour eatingBehaviour, DrinkingBehaviour drinkingBehaviour) {
        this.name = name;
        this.weight = weight;
        this.gender = genderInfo;
        this.species = species;
        this.eatingBehaviour = eatingBehaviour;
        this.drinkingBehaviour = drinkingBehaviour;
    }

    public String getName() {
        return name;
    }

    public SpeciesInfo getSpecies() {
        return species;
    }

    public Weight getWeight() {
        return weight;
    }

    public GenderInfo getGender() {
        return gender;
    }

    public ResourcesNeeds getRequiredFood() {
        return eatingBehaviour.calculateRequiredResourcesNeeds(weight);
    }

    public ResourcesNeeds getRequiredWater() {
        return drinkingBehaviour.calculateRequiredResourcesNeeds(weight);
    }

    public Strength getStrength() {
        return new Strength(
            calculateStrength(weight, this.eatingBehaviour.getStrengthMultiplier(), this.gender.strengthMultiplier));
    }

    private int calculateStrength(Weight weight, double dietMultiplier, double genderMultiplier) {
        return (int) Math.ceil(weight.getWeightKg() * dietMultiplier * genderMultiplier);
    }

    public void drinkAndEat(String resourceType, LunchBox lunchBox) {
        eat(resourceType, lunchBox);
        drink(resourceType, lunchBox);
    }

    private void eat(String resourceType, LunchBox lunchBox) {
        isFed = eatingBehaviour.eat(resourceType, weight, lunchBox) && isFed;
    }

    private void drink(String resourceType, LunchBox lunchBox) {
        isHydrated = drinkingBehaviour.drink(resourceType, weight, lunchBox) && isHydrated;
    }

    public boolean isMale() {
        return gender.equals(GenderInfo.MALE);
    }

    public boolean isFemale() {
        return gender.equals(GenderInfo.FEMALE);
    }

    public boolean isCarnivore() {
        return species.diet.equals(DietInfo.CARNIVORE);
    }

    public boolean isHerbivore() {
        return species.diet.equals(DietInfo.HERBIVORE);
    }

    public boolean isOmnivore() {
        return species.diet.equals(DietInfo.OMNIVORE);
    }

    public boolean isBaby() {
        Weight adultWeight = Weight.fromKg(100);
        return weight.isSmallerThan(adultWeight);
    }

    public boolean isStarving() {
        return !(isFed && isHydrated);
    }

    public boolean isOrphan() {
        return isBaby() && isFatherDead && isMotherDead;
    }

    public boolean isDefeatedInASumoFight() {
        return isDefeatedInASumoFight;
    }

    public void informChildrenThatAParentHasDied() {
        if (isFemale()) {
            for (Dinosaur child : children) {
                child.isMotherDead = true;
            }
        } else if (isMale()) {
            for (Dinosaur child : children) {
                child.isFatherDead = true;
            }
        }
    }

    public void addChild(Dinosaur child) {
        children.add(child);
    }

    @Override
    public int compareTo(Dinosaur comparedTo) {
        if (isEquallyStrongTo(comparedTo)) {
            return name.compareToIgnoreCase(comparedTo.name) > 0 ? 1 : -1;
        }

        return isWeakerThan(comparedTo) ? 1 : -1;
    }

    public void fight(Dinosaur other) {
        if (this.isWeakerThan(other)) {
            other.markAsHungryAndThirsty();
            this.isDefeatedInASumoFight = true;
        } else if (this.isStrongerThan(other)) {
            this.markAsHungryAndThirsty();
            other.isDefeatedInASumoFight = true;
        } else {
            this.markAsHungryAndThirsty();
            other.markAsHungryAndThirsty();
        }
    }

    public boolean isEquallyStrongTo(Dinosaur other) {
        return this.getStrength().equals(other.getStrength());
    }

    public boolean isWeakerThan(Dinosaur other) {
        return this.getStrength().isSmallerThan(other.getStrength());
    }

    public boolean isStrongerThan(Dinosaur other) {
        return this.getStrength().isGreaterThan(other.getStrength());
    }

    private void markAsHungryAndThirsty() {
        eatingBehaviour.markAsHungry();
        drinkingBehaviour.markAsThirsty();
    }

    public void validateWeightModification(Weight weightDifference) {
        if (isBaby()) {
            throw new InvalidBabyWeightChangeException();
        }

        Weight minimumAdultWeight = Weight.fromKg(100);
        Weight newWeight = this.weight.addWeight(weightDifference);

        if (newWeight.isSmallerThan(minimumAdultWeight)) {
            throw new InvalidWeightChangeException();
        }
    }

    public void grow() {
        if (isBaby()) {
            Weight naturalWeightIncrement = Weight.fromKg(33);
            modifyWeight(naturalWeightIncrement);
        }
    }

    public void modifyWeight(Weight weightDifference) {
        this.weight = this.weight.addWeight(weightDifference);
    }

    public boolean isHungry() {
        return eatingBehaviour.isHungry();
    }

    public boolean isThirsty() {
        return drinkingBehaviour.isThirsty();
    }

}
