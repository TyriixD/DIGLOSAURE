package ca.ulaval.glo4002.game.domain.resources;

import java.io.Serializable;

public enum ResourceInfo implements Serializable {
    WATER("water", 10), BURGER("burger", 4), SALAD("salad", 3);

    public final String type;
    public final int shelfLife;

    ResourceInfo(String type, int shelfLife) {
        this.type = type;
        this.shelfLife = shelfLife;
    }
}
