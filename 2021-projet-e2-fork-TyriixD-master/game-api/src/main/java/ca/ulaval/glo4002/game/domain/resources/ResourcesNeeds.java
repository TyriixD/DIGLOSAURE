package ca.ulaval.glo4002.game.domain.resources;

import java.io.Serializable;

public class ResourcesNeeds implements Serializable {
    public final int burgerQuantity;
    public final int saladQuantity;
    public final int waterForCarnivoresQuantity;
    public final int waterForHerbivoresQuantity;

    public ResourcesNeeds(int burgerQuantity, int saladQuantity, int waterForCarnivoresQuantity,
                          int waterForHerbivoresQuantity) {
        this.burgerQuantity = burgerQuantity;
        this.saladQuantity = saladQuantity;
        this.waterForCarnivoresQuantity = waterForCarnivoresQuantity;
        this.waterForHerbivoresQuantity = waterForHerbivoresQuantity;
    }
}
