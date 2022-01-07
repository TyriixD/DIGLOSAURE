package ca.ulaval.glo4002.game.domain.resources;

import java.io.Serializable;

public enum ResourceState implements Serializable {
    FRESH, EXPIRED, CONSUMED;
}
