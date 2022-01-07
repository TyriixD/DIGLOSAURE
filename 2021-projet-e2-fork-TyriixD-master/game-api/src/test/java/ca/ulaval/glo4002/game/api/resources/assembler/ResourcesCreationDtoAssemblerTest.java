package ca.ulaval.glo4002.game.api.resources.assembler;

import ca.ulaval.glo4002.game.api.resources.assemblers.ResourcesCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.resources.dtos.ResourcesCreationRequest;
import ca.ulaval.glo4002.game.api.resources.exceptions.InvalidResourceQuantityException;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesCreationDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResourcesCreationDtoAssemblerTest {
    private ResourcesCreationDtoAssembler resourcesCreationDtoAssembler;
    private int validQuantity;
    private int invalidQuantity;

    @BeforeEach
    public void setupResourcesCreationDtoAssembler() {
        resourcesCreationDtoAssembler = new ResourcesCreationDtoAssembler();
        validQuantity = 50;
        invalidQuantity = -50;
    }

    @Test
    public void givenNegativeBurgerQuantity_whenToDto_thenThrowsInvalidResourceQuantityException() {
        ResourcesCreationRequest resourcesCreationRequest = createInvalidResourcesCreationRequest(invalidQuantity,
                                                                                                  validQuantity,
                                                                                                  validQuantity);

        assertThrows(InvalidResourceQuantityException.class,
                     () -> resourcesCreationDtoAssembler.toDto(resourcesCreationRequest));
    }

    @Test
    public void givenNegativeSaladQuantity_whenToDto_thenThrowsInvalidResourceQuantityException() {
        ResourcesCreationRequest resourcesCreationRequest = createInvalidResourcesCreationRequest(validQuantity,
                                                                                                  invalidQuantity,
                                                                                                  validQuantity);

        assertThrows(InvalidResourceQuantityException.class,
                     () -> resourcesCreationDtoAssembler.toDto(resourcesCreationRequest));
    }

    @Test
    public void givenNegativeWaterQuantity_whenToDto_thenThrowsInvalidResourceQuantityException() {
        ResourcesCreationRequest resourcesCreationRequest = createInvalidResourcesCreationRequest(validQuantity,
                                                                                                  validQuantity,
                                                                                                  invalidQuantity);

        assertThrows(InvalidResourceQuantityException.class,
                     () -> resourcesCreationDtoAssembler.toDto(resourcesCreationRequest));
    }

    private ResourcesCreationRequest createInvalidResourcesCreationRequest(int quantity1, int quantity2,
                                                                           int quantity3) {
        ResourcesCreationRequest resourcesCreationRequest = new ResourcesCreationRequest();
        resourcesCreationRequest.qtyBurger = quantity1;
        resourcesCreationRequest.qtySalad = quantity2;
        resourcesCreationRequest.qtyWater = quantity3;
        return resourcesCreationRequest;
    }

    @Test
    public void givenValidResourcesCreationRequest_whenToDto_thenSendCorrectAmountOfBurger() {
        ResourcesCreationRequest resourcesCreationRequest = createValidResourcesCreationRequest();

        ResourcesCreationDto resourcesCreationDto = resourcesCreationDtoAssembler.toDto(resourcesCreationRequest);

        assertEquals(validQuantity, resourcesCreationDto.getBurgerQuantity());
    }

    @Test
    public void givenValidResourcesCreationRequest_whenToDto_thenSendCorrectAmountOfSalad() {
        ResourcesCreationRequest resourcesCreationRequest = createValidResourcesCreationRequest();

        ResourcesCreationDto resourcesCreationDto = resourcesCreationDtoAssembler.toDto(resourcesCreationRequest);

        assertEquals(validQuantity, resourcesCreationDto.getBurgerQuantity());
    }

    @Test
    public void givenValidResourcesCreationRequest_whenToDto_thenSendCorrectAmountOfBurgers() {
        ResourcesCreationRequest resourcesCreationRequest = createValidResourcesCreationRequest();

        ResourcesCreationDto resourcesCreationDto = resourcesCreationDtoAssembler.toDto(resourcesCreationRequest);

        assertEquals(validQuantity, resourcesCreationDto.getBurgerQuantity());
    }

    private ResourcesCreationRequest createValidResourcesCreationRequest() {
        ResourcesCreationRequest resourcesCreationRequest = new ResourcesCreationRequest();
        resourcesCreationRequest.qtyBurger = validQuantity;
        resourcesCreationRequest.qtySalad = validQuantity;
        resourcesCreationRequest.qtyWater = validQuantity;
        return resourcesCreationRequest;
    }
}
