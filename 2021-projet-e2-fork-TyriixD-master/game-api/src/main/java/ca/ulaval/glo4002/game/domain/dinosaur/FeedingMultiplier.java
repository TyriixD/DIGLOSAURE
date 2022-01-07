package ca.ulaval.glo4002.game.domain.dinosaur;

import java.io.Serializable;

public enum FeedingMultiplier implements Serializable {
    CARNIVORE(1.5, 0.2), HERBIVORE(1.0, 0.5);

    public double strengthMultiplier;
    public double eatingMultiplier;
    public int divider;

    FeedingMultiplier(double strengthMultiplier, double eatingMultiplier) {
        this.strengthMultiplier = strengthMultiplier;
        this.eatingMultiplier = eatingMultiplier;
        this.divider = 200;
    }
}
