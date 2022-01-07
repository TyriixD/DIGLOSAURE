package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidSpeciesException;
import java.io.Serializable;

public enum SpeciesInfo implements Serializable {
    ANKYLOSAURUS("Ankylosaurus", DietInfo.HERBIVORE), BRACHIOSAURUS("Brachiosaurus", DietInfo.HERBIVORE),
    DIPLODOCUS("Diplodocus", DietInfo.HERBIVORE), STEGOSAURUS("Stegosaurus", DietInfo.HERBIVORE),
    TRICERATOPS("Triceratops", DietInfo.HERBIVORE), ALLOSAURUS("Allosaurus", DietInfo.CARNIVORE),
    MEGALOSAURUS("Megalosaurus", DietInfo.CARNIVORE), SPINOSAURUS("Spinosaurus", DietInfo.CARNIVORE),
    TYRANNOSAURUS_REX("Tyrannosaurus Rex", DietInfo.CARNIVORE), VELOCIRAPTOR("Velociraptor", DietInfo.CARNIVORE),
    EORAPTOR("Eoraptor", DietInfo.OMNIVORE), GIGANTORAPTOR("Gigantoraptor", DietInfo.OMNIVORE),
    HETERODONTOSAURUS("Heterodontosaurus", DietInfo.OMNIVORE), ORNITHOMIMUS("Ornithomimus", DietInfo.OMNIVORE),
    STRUTHIOMIMUS("Struthiomimus", DietInfo.OMNIVORE);

    public final String name;
    public final DietInfo diet;

    SpeciesInfo(String name, DietInfo diet) {
        this.name = name;
        this.diet = diet;
    }

    public static SpeciesInfo getSpeciesInfo(String species) {
        for (SpeciesInfo speciesInfo : values()) {
            if (speciesInfo.name.equals(species)) {
                return speciesInfo;
            }
        }
        throw new InvalidSpeciesException();
    }
}
