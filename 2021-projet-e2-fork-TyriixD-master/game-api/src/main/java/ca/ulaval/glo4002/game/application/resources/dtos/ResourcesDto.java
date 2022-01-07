package ca.ulaval.glo4002.game.application.resources.dtos;

public class ResourcesDto {
    public int burgerQuantity;
    public int saladQuantity;
    public int waterQuantity;

    public ResourcesDto(int burgerQuantity, int saladQuantity, int waterQuantity) {
        this.burgerQuantity = burgerQuantity;
        this.saladQuantity = saladQuantity;
        this.waterQuantity = waterQuantity;
    }

    public int getBurgerQuantity() {
        return burgerQuantity;
    }

    public int getSaladQuantity() {
        return saladQuantity;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }
}
