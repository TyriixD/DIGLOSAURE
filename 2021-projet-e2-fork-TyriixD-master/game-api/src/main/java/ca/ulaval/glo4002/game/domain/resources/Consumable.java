package ca.ulaval.glo4002.game.domain.resources;

import java.io.Serializable;

public interface Consumable extends Serializable {
    int consume(int quantity);

    boolean isEmpty();
}
