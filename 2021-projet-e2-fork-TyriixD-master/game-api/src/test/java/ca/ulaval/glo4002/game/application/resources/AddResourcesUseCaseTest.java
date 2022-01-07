package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesCreationDto;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddResourcesUseCaseTest {
    private final int burgerQuantity = 25244;
    private final int saladQuantity = 25244;
    private final int waterQuantity = 25244;
    private AddResourcesUseCase addResourcesUseCase;
    private ResourcesFactory resourcesFactoryMock;
    private ResourcesCreationDto resourcesCreationDto;
    private GameRepository gameRepositoryMock;
    private Game gameMock;

    @BeforeEach
    public void setUpResourceUseCase() {
        resourcesCreationDto = new ResourcesCreationDto(burgerQuantity, saladQuantity, waterQuantity);

        gameMock = mock(Game.class);
        gameRepositoryMock = mock(GameRepository.class);
        when(gameRepositoryMock.find()).thenReturn(gameMock);

        resourcesFactoryMock = mock(ResourcesFactory.class);
        addResourcesUseCase = new AddResourcesUseCase(gameRepositoryMock, resourcesFactoryMock);
    }

    @Test
    public void givenResourceCreationRequest_whenAddResources_thenFactoryCreateIsCalled() {
        addResourcesUseCase.addResources(resourcesCreationDto);

        verify(resourcesFactoryMock).create(burgerQuantity, saladQuantity, waterQuantity);
    }

    @Test
    public void givenAddResource_thenGameAddResourcesIsCalled() {
        List<Resources> resources = new ArrayList<>();
        when(resourcesFactoryMock.create(burgerQuantity, saladQuantity, waterQuantity)).thenReturn(resources);

        addResourcesUseCase.addResources(resourcesCreationDto);

        verify(gameMock).addResources(resources);
    }

    @Test
    public void whenAddResources_thenSaveOnRepositoryIsCalled() {
        addResourcesUseCase.addResources(resourcesCreationDto);

        verify(gameRepositoryMock).save(gameMock);
    }
}
