package ca.ulaval.glo4002.game.domain.dinosaur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DinosaurFactoryTest {
    private static final int WEIGHT_IN_KG = 7246;
    private String validName;
    private GenderInfo validGender;
    private Weight weight;
    private Weight offSpringWeight;
    private SpeciesInfo validSpecies;
    private DinosaurFactory dinosaurFactory;

    @BeforeEach
    public void setUp() {
        validName = "test";
        validSpecies = SpeciesInfo.ALLOSAURUS;
        weight = Weight.fromKg(WEIGHT_IN_KG);
        offSpringWeight = Weight.fromKg(1);
        validGender = GenderInfo.getGenderInfo("f");
        dinosaurFactory = new DinosaurFactory();
    }

    @Test
    public void givenValidCarnivoreRequest_whenCallingCreate_thenReturnAValidDinosaur() {
        SpeciesInfo carnivoreSpecies = SpeciesInfo.TYRANNOSAURUS_REX;

        Dinosaur testDino = dinosaurFactory.create(validName, weight, validGender, carnivoreSpecies);

        assertEquals(validName, testDino.getName());
        assertEquals(Weight.fromKg(WEIGHT_IN_KG), testDino.getWeight());
        assertEquals(validGender, testDino.getGender());
        assertEquals(carnivoreSpecies, testDino.getSpecies());
    }

    @Test
    public void givenAValidHerbivoreRequest_whenCallingCreate_thenReturnAValidDinosaur() {
        SpeciesInfo herbivoreSpecies = SpeciesInfo.ANKYLOSAURUS;

        Dinosaur herbivoreDinosaur = dinosaurFactory.create(validName, weight, validGender, herbivoreSpecies);

        assertEquals(validName, herbivoreDinosaur.getName());
        assertEquals(Weight.fromKg(WEIGHT_IN_KG), herbivoreDinosaur.getWeight());
        assertEquals(validGender, herbivoreDinosaur.getGender());
        assertEquals(herbivoreSpecies, herbivoreDinosaur.getSpecies());
    }

    @Test
    public void givenAValidOmnivoreRequest_whenCallingCreate_thenReturnAValidDinosaur() {
        SpeciesInfo omnivoreSpecies = SpeciesInfo.GIGANTORAPTOR;

        Dinosaur herbivoreDinosaur = dinosaurFactory.create(validName, weight, validGender, omnivoreSpecies);

        assertEquals(validName, herbivoreDinosaur.getName());
        assertEquals(Weight.fromKg(WEIGHT_IN_KG), herbivoreDinosaur.getWeight());
        assertEquals(validGender, herbivoreDinosaur.getGender());
        assertEquals(omnivoreSpecies, herbivoreDinosaur.getSpecies());
    }

    @Test
    public void givenValidOffspringCreationRequest_thenOffspringIsCreatedWithCorrectWeight() {
        Dinosaur testDino = dinosaurFactory.createOffspring(validName, validGender, validSpecies);

        assertEquals(validName, testDino.getName());
        assertEquals(offSpringWeight, testDino.getWeight());
        assertEquals(validGender, testDino.getGender());
        assertEquals(SpeciesInfo.ALLOSAURUS, testDino.getSpecies());
    }
}
