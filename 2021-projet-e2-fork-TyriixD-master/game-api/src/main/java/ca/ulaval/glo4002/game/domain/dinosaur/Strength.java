package ca.ulaval.glo4002.game.domain.dinosaur;

import java.io.Serializable;

public class Strength implements Serializable {
    private final int value;

    public Strength(int value) {
        this.value = value;
    }

    public boolean isSmallerThan(Strength strength) {
        return this.value < strength.value;
    }

    public boolean isGreaterThan(Strength strength) {
        return this.value > strength.value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Strength strength = (Strength) object;
        return this.value == strength.value;
    }
}
