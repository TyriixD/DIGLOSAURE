package ca.ulaval.glo4002.game.api.resources.dtos;

public class ResourcesCreationRequest {
    public int qtyBurger;
    public int qtySalad;
    public int qtyWater;

    public int getQtyBurger() {
        return qtyBurger;
    }

    public int getQtySalad() {
        return qtySalad;
    }

    public int getQtyWater() {
        return qtyWater;
    }
}
