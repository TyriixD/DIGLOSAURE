package ca.ulaval.glo4002.game.api.resources.dtos;

public class ResourcesResponse {
    public final int qtyBurger;
    public final int qtySalad;
    public final int qtyWater;

    public ResourcesResponse(int qtyBurger, int qtySalad, int qtyWater) {
        this.qtyBurger = qtyBurger;
        this.qtySalad = qtySalad;
        this.qtyWater = qtyWater;
    }
}
