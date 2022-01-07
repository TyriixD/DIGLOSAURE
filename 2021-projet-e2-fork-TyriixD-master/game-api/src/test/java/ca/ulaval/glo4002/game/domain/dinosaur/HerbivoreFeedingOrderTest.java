package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.OmnivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.OmnivoreEatingBehaviour;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HerbivoreFeedingOrderTest {
    private final List<Dinosaur> dinosaurs = new ArrayList<>();
    private HerbivoreFeedingOrder herbivoreFeedingOrder;
    private List<Dinosaur> matchingDinosaurs = new ArrayList<>();
    private Dinosaur herbivoreDinosaur;
    private Dinosaur carnivoreDinosaur;
    private Dinosaur omnivoreDinosaur;

    @BeforeEach
    void setUp() {
        herbivoreFeedingOrder = new HerbivoreFeedingOrder();
        initializeDinosaurs();
    }

    @Test
    public void givenDifferentDinosaurSpecies_whenCallingOrderDinosaurs_thenReturnDinosaursWithoutCarnivoreDinosaur() {
        matchingDinosaurs = herbivoreFeedingOrder.orderDinosaurs(dinosaurs);

        assertFalse(matchingDinosaurs.contains(carnivoreDinosaur));
        assertTrue(matchingDinosaurs.contains(herbivoreDinosaur));
        assertTrue(matchingDinosaurs.contains(omnivoreDinosaur));
    }

    @Test
    public void givenDinosaursWithDifferentSize_whenCallingOrderDinosaurs_thenReturnSortedDinosaurs() {
        matchingDinosaurs = herbivoreFeedingOrder.orderDinosaurs(dinosaurs);

        assertEquals(matchingDinosaurs.get(0), omnivoreDinosaur);
        assertEquals(matchingDinosaurs.get(1), herbivoreDinosaur);
    }

    private void initializeDinosaurs() {
        herbivoreDinosaur = new Dinosaur("HerbiDinoTest", Weight.fromKg(10), GenderInfo.getGenderInfo("m"),
                                         SpeciesInfo.getSpeciesInfo("Ankylosaurus"), new HerbivoreEatingBehaviour(),
                                         new HerbivoreDrinkingBehaviour());

        carnivoreDinosaur = new Dinosaur("CarniDinoTest", Weight.fromKg(2), GenderInfo.getGenderInfo("m"),
                                         SpeciesInfo.getSpeciesInfo("Allosaurus"), new CarnivoreEatingBehaviour(),
                                         new CarnivoreDrinkingBehaviour());

        omnivoreDinosaur = new Dinosaur("OmniDinoTest", Weight.fromKg(1), GenderInfo.getGenderInfo("m"),
                                        SpeciesInfo.getSpeciesInfo("Gigantoraptor"), new OmnivoreEatingBehaviour(),
                                        new OmnivoreDrinkingBehaviour());

        dinosaurs.add(herbivoreDinosaur);
        dinosaurs.add(carnivoreDinosaur);
        dinosaurs.add(omnivoreDinosaur);
    }
}
