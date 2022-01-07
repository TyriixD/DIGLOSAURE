package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidBabyWeightChangeException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightChangeException;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DinosaurTest {
    private Dinosaur dinosaurTest;
    private String testName = "SmolEliane";
    private Weight testWeight = Weight.fromKg(7244);
    private Weight babyTestWeight = Weight.fromKg(89);
    private Weight naturalWeightIncrement = Weight.fromKg(33);
    private GenderInfo testGender = GenderInfo.FEMALE;
    private SpeciesInfo testSpecies = SpeciesInfo.TYRANNOSAURUS_REX;
    private CarnivoreEatingBehaviour eatingBehaviour = new CarnivoreEatingBehaviour();
    private CarnivoreDrinkingBehaviour drinkingBehaviour = new CarnivoreDrinkingBehaviour();

    @BeforeEach
    public void setUpDinosaur() {
        dinosaurTest = new Dinosaur(testName, testWeight, testGender, testSpecies, eatingBehaviour, drinkingBehaviour);
    }

    @Test
    public void givenName_whenCreatingDinosaur_thenDinosaurCreatedWithName() {
        assertEquals(dinosaurTest.getName(), testName);
    }

    @Test
    public void givenWeight_whenCreatingDinosaur_thenDinosaurCreatedWithWeight() {
        assertEquals(dinosaurTest.getWeight(), testWeight);
    }

    @Test
    public void givenGender_whenCreatingDinosaur_thenDinosaurCreatedWithGender() {
        assertEquals(dinosaurTest.getGender(), testGender);
    }

    @Test
    public void givenCarnivoreDiet_whenCreatingDinosaur_thenDinosaurIsCarnivore() {
        assertTrue(dinosaurTest.isCarnivore());
    }

    @Test
    public void givenSpecies_whenCreatingDinosaur_thenDinosaurCreatedWithSpecies() {
        assertEquals(dinosaurTest.getSpecies(), testSpecies);
    }

    @Test
    public void givenDinosaurWithGreaterStrength_whenDinosaurHasLowerStrength_thenComparisonReturnPositiveInteger() {
        Dinosaur whenDinosaur = new Dinosaur("Test", testWeight, GenderInfo.FEMALE, testSpecies, eatingBehaviour,
                                             drinkingBehaviour);
        Dinosaur givenDinosaur = new Dinosaur("Tester", Weight.fromKg(10000), GenderInfo.FEMALE, testSpecies,
                                              eatingBehaviour, drinkingBehaviour);

        assertTrue(whenDinosaur.compareTo(givenDinosaur) > 0);
    }

    @Test
    public void givenDinosaurWithLowerStrength_whenDinosaurHasGreaterStrength_thenComparisonReturnNegativeInteger() {
        Dinosaur whenDinosaur = new Dinosaur("Test", Weight.fromKg(10000), GenderInfo.FEMALE, testSpecies,
                                             eatingBehaviour, drinkingBehaviour);
        Dinosaur givenDinosaur = new Dinosaur("Tester", testWeight, GenderInfo.FEMALE, testSpecies, eatingBehaviour,
                                              drinkingBehaviour);

        assertTrue(whenDinosaur.compareTo(givenDinosaur) < 0);
    }

    @Test
    public void givenDinosaurWithSameStrength_whenDinoNameIsAlphabeticallyLater_thenComparisonReturnPositiveInteger() {
        Dinosaur whenDinosaur = new Dinosaur("xyz", testWeight, GenderInfo.FEMALE, testSpecies, eatingBehaviour,
                                             drinkingBehaviour);
        Dinosaur givenDinosaur = new Dinosaur("abc", testWeight, GenderInfo.FEMALE, testSpecies, eatingBehaviour,
                                              drinkingBehaviour);

        assertTrue(whenDinosaur.compareTo(givenDinosaur) > 0);
    }

    @Test
    public void givenDinosaurWithSameStrength_whenDinoNameIsAlphabeticallyBefore_thenComparisonReturnNegativeInteger() {
        Dinosaur whenDinosaur = new Dinosaur("ijk", testWeight, GenderInfo.FEMALE, testSpecies, eatingBehaviour,
                                             drinkingBehaviour);
        Dinosaur givenDinosaur = new Dinosaur("lmn", testWeight, GenderInfo.FEMALE, testSpecies, eatingBehaviour,
                                              drinkingBehaviour);

        assertTrue(whenDinosaur.compareTo(givenDinosaur) < 0);
    }

    @Test
    public void givenCarnivoreFemaleWeight_whenCreatingDinosaur_thenDinosaurCreatedWithCorrectStrength() {
        dinosaurTest = new Dinosaur(testName, testWeight, GenderInfo.FEMALE, testSpecies, eatingBehaviour,
                                    drinkingBehaviour);

        assertEquals(new Strength(16299), dinosaurTest.getStrength());
    }

    @Test
    public void givenCarnivoreMaleWeight_whenCreatingDinosaur_thenDinosaurCreatedWithCorrectStrength() {
        dinosaurTest = new Dinosaur(testName, testWeight, GenderInfo.MALE, testSpecies, eatingBehaviour,
                                    drinkingBehaviour);

        assertEquals(new Strength(10866), dinosaurTest.getStrength());
    }

    @Test
    public void givenHerbivoreFemaleWeight_whenCreatingDinosaur_thenDinosaurCreatedWithCorrectStrength() {
        dinosaurTest = new Dinosaur(testName, testWeight, GenderInfo.FEMALE, SpeciesInfo.ANKYLOSAURUS, eatingBehaviour,
                                    drinkingBehaviour);

        assertEquals(new Strength(16299), dinosaurTest.getStrength());
    }

    @Test
    public void givenHerbivoreDinosaur_whenCreatingDinosaur_thenDinosaurIsCarnivore() {
        dinosaurTest = new Dinosaur(testName, testWeight, GenderInfo.FEMALE, SpeciesInfo.ANKYLOSAURUS, eatingBehaviour,
                                    drinkingBehaviour);

        assertTrue(dinosaurTest.isHerbivore());
    }

    @Test
    public void givenHerbivoreMaleWeight_whenCreatingDinosaur_thenDinosaurCreatedWithCorrectStrength() {
        dinosaurTest = new Dinosaur(testName, testWeight, GenderInfo.MALE, SpeciesInfo.ANKYLOSAURUS,
                                    new HerbivoreEatingBehaviour(), new HerbivoreDrinkingBehaviour());

        assertEquals(dinosaurTest.getStrength(), new Strength(7244));
    }

    @Test
    public void whenASingleParentDinosaurDies_thenChildrenIsNotOrphan() {
        Dinosaur mother = givenMotherDinosaurOf(dinosaurTest);

        mother.informChildrenThatAParentHasDied();

        assertFalse(dinosaurTest.isOrphan());
    }

    @Test
    public void givenBabyDinosaur_whenParentDinosaursDie_thenChildrenBecomesOrphan() {
        dinosaurTest = new Dinosaur(testName, Weight.fromKg(20), testGender, testSpecies,
                                    new CarnivoreEatingBehaviour(), new CarnivoreDrinkingBehaviour());
        Dinosaur mother = givenMotherDinosaurOf(dinosaurTest);
        Dinosaur father = givenFatherDinosaurOf(dinosaurTest);

        mother.informChildrenThatAParentHasDied();
        father.informChildrenThatAParentHasDied();

        assertTrue(dinosaurTest.isOrphan());
    }

    private Dinosaur givenMotherDinosaurOf(Dinosaur child) {
        String motherName = "AlexandraG";
        Dinosaur mother = new Dinosaur(motherName, testWeight, GenderInfo.FEMALE, testSpecies,
                                       new CarnivoreEatingBehaviour(), new CarnivoreDrinkingBehaviour());
        mother.addChild(child);

        return mother;
    }

    private Dinosaur givenFatherDinosaurOf(Dinosaur child) {
        String fatherName = "AlexandreG";
        Dinosaur father = new Dinosaur(fatherName, testWeight, GenderInfo.MALE, testSpecies,
                                       new CarnivoreEatingBehaviour(), new CarnivoreDrinkingBehaviour());
        father.addChild(child);

        return father;
    }

    @Test
    public void givenInsufficientResources_whenDinosaurDrinkAndEat_thenDinosaurIsStarving() {
        LunchBox lunchBoxMock = mock(LunchBox.class);
        when(lunchBoxMock.consumeBurgers(anyInt())).thenAnswer(i -> false);

        dinosaurTest.drinkAndEat("meat", lunchBoxMock);

        assertTrue(dinosaurTest.isStarving());
    }

    @Test
    public void givenStrongDinosaur_whenFightingWeakDinosaur_thenStrongDinosaurDoesNotLoseFight() {
        Dinosaur strongDinosaur = givenWellFedDinosaurOfGivenWeight(200);
        Dinosaur weakDinosaur = givenWellFedDinosaurOfGivenWeight(100);

        strongDinosaur.fight(weakDinosaur);

        assertFalse(strongDinosaur.isDefeatedInASumoFight());
    }

    @Test
    public void givenStrongDinosaur_whenFightingWeakDinosaur_thenIsHungryAndThirsty() {
        Dinosaur strongDinosaur = givenWellFedDinosaurOfGivenWeight(200);
        Dinosaur weakDinosaur = givenWellFedDinosaurOfGivenWeight(100);

        strongDinosaur.fight(weakDinosaur);

        assertTrue(strongDinosaur.isHungry());
        assertTrue(strongDinosaur.isThirsty());
    }

    @Test
    public void givenWeakDinosaur_whenFightingStrongDinosaur_thenWeakDinosaurLosesFight() {
        Dinosaur strongDinosaur = givenWellFedDinosaurOfGivenWeight(200);
        Dinosaur weakDinosaur = givenWellFedDinosaurOfGivenWeight(100);

        weakDinosaur.fight(strongDinosaur);

        assertTrue(weakDinosaur.isDefeatedInASumoFight());
    }

    @Test
    public void givenWeakDinosaur_whenFightingStrongDinosaur_thenWeakDinosaurIsNotHungryAndThirsty() {
        Dinosaur strongDinosaur = givenWellFedDinosaurOfGivenWeight(200);
        Dinosaur weakDinosaur = givenWellFedDinosaurOfGivenWeight(100);

        weakDinosaur.fight(strongDinosaur);

        assertFalse(weakDinosaur.isHungry());
        assertFalse(weakDinosaur.isThirsty());
    }

    @Test
    public void givenEquallyStrongDinosaurs_whenFighting_thenNeitherLoseFight() {
        Dinosaur strongDinosaur = givenWellFedDinosaurOfGivenWeight(200);
        Dinosaur equallyStrongDinosaur = givenWellFedDinosaurOfGivenWeight(200);

        strongDinosaur.fight(equallyStrongDinosaur);

        assertFalse(strongDinosaur.isDefeatedInASumoFight());
        assertFalse(equallyStrongDinosaur.isDefeatedInASumoFight());
    }

    @Test
    public void givenEquallyStrongDinosaurs_whenFighting_thenBothDinosaursAreHungryAndThirsty() {
        Dinosaur strongDinosaur = givenWellFedDinosaurOfGivenWeight(200);
        Dinosaur equallyStrongDinosaur = givenWellFedDinosaurOfGivenWeight(200);

        strongDinosaur.fight(equallyStrongDinosaur);

        assertTrue(strongDinosaur.isHungry());
        assertTrue(strongDinosaur.isThirsty());
        assertTrue(equallyStrongDinosaur.isHungry());
        assertTrue(equallyStrongDinosaur.isThirsty());
    }

    private Dinosaur givenWellFedDinosaurOfGivenWeight(int weightInKg) {
        Dinosaur dinosaur = new Dinosaur("Amandin Charpentier", Weight.fromKg(weightInKg), GenderInfo.MALE,
                                         SpeciesInfo.VELOCIRAPTOR, new CarnivoreEatingBehaviour(),
                                         new CarnivoreDrinkingBehaviour());

        LunchBox lunchBox = new LunchBox(10000, 10000, 10000, 10000);
        dinosaur.drinkAndEat("meat", lunchBox);

        return dinosaur;
    }

    @Test
    public void givenBabyDinosaur_whenValidateWeightModification_thenThrowInvalidBabyWeightChangeException() {
        Dinosaur dinosaurBaby = new Dinosaur("Yvan Desautels", babyTestWeight, GenderInfo.MALE,
                                             SpeciesInfo.VELOCIRAPTOR, new CarnivoreEatingBehaviour(),
                                             new CarnivoreDrinkingBehaviour());
        Weight weightDifference = Weight.fromKg(10);

        assertThrows(InvalidBabyWeightChangeException.class,
                     () -> dinosaurBaby.validateWeightModification(weightDifference));
    }

    @Test
    public void givenWeightDifference_whenModifyWeight_thenTheRightWeightIsReturned() {
        Weight weightDifference = Weight.fromKg(100);

        dinosaurTest.modifyWeight(weightDifference);

        assertEquals(testWeight.addWeight(weightDifference), dinosaurTest.getWeight());
    }

    @Test
    public void givenWeightSmallerThanMinimumAdultWeight_whenValidateWeightModification_thenInvalidWeightChangeExceptionIsThrown() {
        Weight weightDifference = Weight.fromKg(-5);
        Dinosaur dinosaur = new Dinosaur("Armand Pelletier", Weight.fromKg(100), GenderInfo.MALE,
                                         SpeciesInfo.VELOCIRAPTOR, new CarnivoreEatingBehaviour(),
                                         new CarnivoreDrinkingBehaviour());

        assertThrows(InvalidWeightChangeException.class, () -> dinosaur.validateWeightModification(weightDifference));
    }

    @Test
    public void givenBabyDinosaur_whenGrow_thenWeightIsIncremented() {
        Dinosaur dinosaurBaby = new Dinosaur("Jezabel Tetreault", babyTestWeight, GenderInfo.MALE,
                                             SpeciesInfo.VELOCIRAPTOR, new CarnivoreEatingBehaviour(),
                                             new CarnivoreDrinkingBehaviour());

        dinosaurBaby.grow();

        assertEquals(babyTestWeight.addWeight(naturalWeightIncrement), dinosaurBaby.getWeight());
    }

}
