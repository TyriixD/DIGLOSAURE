package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.OmnivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.OmnivoreEatingBehaviour;

public class DinosaurFactory {
    public Dinosaur create(String name, Weight weight, GenderInfo gender, SpeciesInfo species) {
        switch (species.diet) {
            case HERBIVORE:
                return new Dinosaur(name, weight, gender, species, new HerbivoreEatingBehaviour(),
                                    new HerbivoreDrinkingBehaviour());
            case CARNIVORE:
                return new Dinosaur(name, weight, gender, species, new CarnivoreEatingBehaviour(),
                                    new CarnivoreDrinkingBehaviour());
            case OMNIVORE:
                return new Dinosaur(name, weight, gender, species, new OmnivoreEatingBehaviour(),
                                    new OmnivoreDrinkingBehaviour());
            default:
                throw new IllegalArgumentException();
        }
    }

    public Dinosaur createOffspring(String name, GenderInfo gender, SpeciesInfo species) {
        Weight startingWeight = Weight.fromKg(1);

        return create(name, startingWeight, gender, species);
    }
}
