package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.exceptions.NonExistentNameException;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.resources.LunchBox;
import ca.ulaval.glo4002.game.domain.resources.ResourcesNeeds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinosaurHerdTest {
    private static final int HUNGRY_MODIFIER = 2;
    private static final String DINOSAUR_NAME = "OmnivoreDino";
    private DinosaurHerd dinosaurHerd;
    private Dinosaur omnivoreDinosaur;
    private Dinosaur dadDinosaur;
    private Dinosaur momDinosaur;

    @BeforeEach
    void setUp() {
        initializeAnOmnivore();
        FeedingOrder carnivoreFeedingOrderer = new CarnivoreFeedingOrder();
        FeedingOrder herbivoreFeedingOrderer = new HerbivoreFeedingOrder();
        dinosaurHerd = new DinosaurHerd(carnivoreFeedingOrderer, herbivoreFeedingOrderer);
    }

    private void initializeAnOmnivore() {
        omnivoreDinosaur = new Dinosaur(DINOSAUR_NAME, Weight.fromKg(1), GenderInfo.getGenderInfo("m"),
                                        SpeciesInfo.getSpeciesInfo("Gigantoraptor"), new HerbivoreEatingBehaviour(),
                                        new HerbivoreDrinkingBehaviour());
    }

    private void addingParentsDinosaurs() {
        dadDinosaur = new Dinosaur("Dad", Weight.fromKg(10), GenderInfo.getGenderInfo("m"),
                                   SpeciesInfo.getSpeciesInfo("Gigantoraptor"), new HerbivoreEatingBehaviour(),
                                   new HerbivoreDrinkingBehaviour());

        dadDinosaur.addChild(omnivoreDinosaur);
        dinosaurHerd.addDinosaur(dadDinosaur);

        momDinosaur = new Dinosaur("Mom", Weight.fromKg(15), GenderInfo.getGenderInfo("f"),
                                   SpeciesInfo.getSpeciesInfo("Gigantoraptor"), new HerbivoreEatingBehaviour(),
                                   new HerbivoreDrinkingBehaviour());

        momDinosaur.addChild(omnivoreDinosaur);
        dinosaurHerd.addDinosaur(momDinosaur);
    }

    private LunchBox initializeLunchBoxForOmnivoreDinosaurOnly() {
        int expectedBurgersNeeds = omnivoreDinosaur.getRequiredFood().burgerQuantity * HUNGRY_MODIFIER;
        int expectedSaladsNeeds = omnivoreDinosaur.getRequiredFood().saladQuantity * HUNGRY_MODIFIER;
        int expectedWatersForHerbivoreNeeds =
            omnivoreDinosaur.getRequiredWater().waterForHerbivoresQuantity * HUNGRY_MODIFIER;
        int expectedWatersForCarnivoreNeeds =
            omnivoreDinosaur.getRequiredWater().waterForCarnivoresQuantity * HUNGRY_MODIFIER;

        return new LunchBox(expectedBurgersNeeds, expectedSaladsNeeds, expectedWatersForCarnivoreNeeds,
                            expectedWatersForHerbivoreNeeds);
    }

    @Test
    public void givenTwoDinosaurWithSameName_whenAddingDinosaur_thenOnlyFirstOneIsAdded() {
        dinosaurHerd.addDinosaur(omnivoreDinosaur);
        Dinosaur dinoWithSameName = new Dinosaur(DINOSAUR_NAME, Weight.fromKg(10), GenderInfo.getGenderInfo("m"),
                                                 SpeciesInfo.getSpeciesInfo("Ankylosaurus"),
                                                 new HerbivoreEatingBehaviour(), new HerbivoreDrinkingBehaviour());

        dinosaurHerd.addDinosaur(dinoWithSameName);

        assertEquals(1, dinosaurHerd.getAllDinosaurs().size());
    }

    @Test
    public void givenNonExistentNameDinosaur_whenGettingDinosaurByName_thenThrowNonExistentNameException() {
        assertThrows(NonExistentNameException.class, () -> dinosaurHerd.getDinosaurByName("NonExistentName"));
    }

    @Test
    public void givenADinosaurName_whenGettingDinosaurByName_thenReturnDinosaurWithTheSameName() {
        dinosaurHerd.addDinosaur(omnivoreDinosaur);

        assertEquals(omnivoreDinosaur, dinosaurHerd.getDinosaurByName(omnivoreDinosaur.getName()));
    }

    @Test
    public void givenAnHungryDinosaur_whenCalculatingRessourcesNeed_thenRessourcesNeedsIsCalculatedRight() {
        dinosaurHerd.addDinosaur(omnivoreDinosaur);
        int expectedBurgersNeeds = omnivoreDinosaur.getRequiredFood().burgerQuantity;
        int expectedSaladsNeeds = omnivoreDinosaur.getRequiredFood().saladQuantity;
        int expectedWatersForHerbivoreNeeds = omnivoreDinosaur.getRequiredWater().waterForHerbivoresQuantity;
        int expectedWatersForCarnivoreNeeds = omnivoreDinosaur.getRequiredWater().waterForCarnivoresQuantity;

        ResourcesNeeds resourcesNeeds = dinosaurHerd.calculateResourcesNeeds();

        assertEquals(expectedBurgersNeeds, resourcesNeeds.burgerQuantity);
        assertEquals(expectedSaladsNeeds, resourcesNeeds.saladQuantity);
        assertEquals(expectedWatersForHerbivoreNeeds, resourcesNeeds.waterForHerbivoresQuantity);
        assertEquals(expectedWatersForCarnivoreNeeds, resourcesNeeds.waterForCarnivoresQuantity);
    }

    @Test
    public void givenAnOmnivoreDinosaurAnSufficientResources_whenFeedingDinosaur_thenTheDinosaurIsNotStarvingOrThirsty() {
        dinosaurHerd.addDinosaur(omnivoreDinosaur);
        LunchBox lunchBox = initializeLunchBoxForOmnivoreDinosaurOnly();

        dinosaurHerd.feedDinosaurs(lunchBox);

        assertFalse(omnivoreDinosaur.isStarving());
        assertFalse(omnivoreDinosaur.isThirsty());
    }

    @Test
    public void givenAStarvedDinosaur_whenRemovingDeadDinosaur_thenTheDinosaurIsRemoved() {
        dinosaurHerd.addDinosaur(omnivoreDinosaur);
        LunchBox emptyLunchBox = new LunchBox(0, 0, 0, 0);

        dinosaurHerd.feedDinosaurs(emptyLunchBox);
        dinosaurHerd.removeStarvedDinosaurs();

        assertTrue(dinosaurHerd.getAllDinosaurs().isEmpty());
    }

    @Test
    public void givenBothParentsDying_whenRemovingDeadDinosaur_thenChildIsNowOrphan() {
        addingParentsDinosaurs();
        LunchBox emptyLunchBox = new LunchBox(0, 0, 0, 0);

        dinosaurHerd.feedDinosaurs(emptyLunchBox);
        dinosaurHerd.removeStarvedDinosaurs();

        assertTrue(omnivoreDinosaur.isOrphan());
    }

    @Test
    public void givenAnOrphanDinosaur_whenRemovingOrphan_thenOrphanIsRemoved() {
        addingParentsDinosaurs();
        dinosaurHerd.addDinosaur(omnivoreDinosaur);
        dadDinosaur.informChildrenThatAParentHasDied();
        momDinosaur.informChildrenThatAParentHasDied();

        dinosaurHerd.removeOrphanDinosaurs();

        assertFalse(dinosaurHerd.checkIfDinosaurExists(omnivoreDinosaur.getName()));
    }

    @Test
    public void givenASumoFightLoser_whenRemovingLoser_thenLoserIsRemoved() {
        addingParentsDinosaurs();
        dadDinosaur.fight(momDinosaur);

        dinosaurHerd.removeSumoFightLosers();

        assertFalse(dinosaurHerd.checkIfDinosaurExists(dadDinosaur.getName()));
    }

    @Test
    public void whenResetTheHerd_thenDinosaursIsEmpty() {
        addingParentsDinosaurs();

        dinosaurHerd.reset();

        assertTrue(dinosaurHerd.getAllDinosaurs().isEmpty());
    }

    @Test
    public void givenADinosaur_whenModifyWeight_thenDinosaurWeightIsUpdated() {
        int addedWeight = 10;
        int expectedWeight = omnivoreDinosaur.getWeight().getWeightKg() + addedWeight;

        dinosaurHerd.modifyDinosaurWeight(omnivoreDinosaur, Weight.fromKg(addedWeight));

        assertEquals(expectedWeight, omnivoreDinosaur.getWeight().getWeightKg());
    }

    @Test
    public void givenABabyDinosaur_whenCallingGrowBabyDinosaur_thenBabyGrow() {
        Weight initialWeight = omnivoreDinosaur.getWeight();
        dinosaurHerd.addDinosaur(omnivoreDinosaur);

        dinosaurHerd.growBabyDinosaurs();

        assertTrue(initialWeight.isSmallerThan(omnivoreDinosaur.getWeight()));
    }

}
