package ca.ulaval.glo4002.game.domain.resources;

import java.io.Serializable;

public class LunchBox implements Serializable {
    private int burgerQuantity;
    private int saladQuantity;
    private int waterForCarnivoresQuantity;
    private int waterForHerbivoresQuantity;

    public LunchBox(int burgerQuantity, int saladQuantity, int waterForCarnivoresQuantity,
                    int waterForHerbivoresQuantity) {
        this.burgerQuantity = burgerQuantity;
        this.saladQuantity = saladQuantity;
        this.waterForCarnivoresQuantity = waterForCarnivoresQuantity;
        this.waterForHerbivoresQuantity = waterForHerbivoresQuantity;
    }

    public boolean consumeBurgers(int quantity) {
        burgerQuantity -= quantity;
        return burgerQuantity >= 0;
    }

    public boolean consumeSalads(int quantity) {
        saladQuantity -= quantity;
        return saladQuantity >= 0;
    }

    public boolean consumeWaterForCarnivores(int quantity) {
        waterForCarnivoresQuantity -= quantity;
        return waterForCarnivoresQuantity >= 0;
    }

    public boolean consumeWaterForHerbivores(int quantity) {
        waterForHerbivoresQuantity -= quantity;
        return waterForHerbivoresQuantity >= 0;
    }
}
