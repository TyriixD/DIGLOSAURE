package ca.ulaval.glo4002.game.domain.resources;

import java.io.Serializable;

public class Resources implements Consumable, Serializable {
    private final ResourceInfo info;
    private ResourceState state;
    private int remainingShelfLife;
    private int quantity;

    public Resources(ResourceInfo info, ResourceState state, int quantity) {
        this.info = info;
        this.state = state;
        this.remainingShelfLife = info.shelfLife;
        this.quantity = quantity;
    }

    public int consume(int quantity) {
        if (quantity >= this.quantity) {
            int quantityConsumed = this.quantity;
            this.quantity = 0;
            return quantityConsumed;
        } else {
            this.quantity -= quantity;
            return quantity;
        }
    }

    public void decrementShelfLife() {
        --remainingShelfLife;

        if (remainingShelfLife < 0) {
            state = ResourceState.EXPIRED;
        }
    }

    public ResourceInfo getInfo() {
        return info;
    }

    public ResourceState getState() {
        return state;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isExpired() {
        return state.equals(ResourceState.EXPIRED);
    }

    public boolean isEmpty() {
        return quantity == 0;
    }


}
