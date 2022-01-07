package ca.ulaval.glo4002.game.domain.dinosaur;

import java.io.Serializable;

public class Weight implements Serializable {
    private final int valueInKg;

    private Weight(int valueInKg) {
        this.valueInKg = valueInKg;
    }

    public static Weight fromKg(int valueKg) {

        return new Weight(valueKg);
    }

    public int getWeightKg() {
        return valueInKg;
    }

    public boolean isSmallerThan(Weight weight) {
        return this.valueInKg < weight.valueInKg;
    }

    public Weight addWeight(Weight weight) {
        return Weight.fromKg(this.valueInKg + weight.valueInKg);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Weight weight = (Weight) object;
        return this.valueInKg == weight.valueInKg;
    }
}
