package ca.ulaval.glo4002.game.application.resources.dtos;

public class ResourcesCreationDto {
    private final int burgerQuantity;
    private final int saladQuantity;
    private final int waterQuantity;

    public ResourcesCreationDto(int burgerQuantity, int saladQuantity, int waterQuantity) {
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
