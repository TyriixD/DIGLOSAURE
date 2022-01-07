package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.application.resources.assemblers.CategorizedResourcesDtoAssembler;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetResourcesUseCaseTest {
    private GetResourcesUseCase getResourcesUseCase;
    private CategorizedResourcesDtoAssembler resourceAssemblerMock;
    private GameRepository gameRepositoryMock;
    private Game gameMock;

    @BeforeEach
    public void setUpResourceUseCase() {
        gameMock = mock(Game.class);
        gameRepositoryMock = mock(GameRepository.class);
        when(gameRepositoryMock.find()).thenReturn(gameMock);

        resourceAssemblerMock = mock(CategorizedResourcesDtoAssembler.class);
        getResourcesUseCase = new GetResourcesUseCase(gameRepositoryMock, resourceAssemblerMock);
    }

    @Test
    public void whenGetResources_thenFindOnRepositoryIsCalled() {
        getResourcesUseCase.getResources();

        verify(gameRepositoryMock).find();
    }

    @Test
    public void whenGetResources_thenGetResourcesFromGameIsCalled() {
        getResourcesUseCase.getResources();

        verify(gameMock).findAllResources();
    }

    @Test
    public void whenGetResources_thenToDtoFromAssemblerIsCalled() {
        List<Resources> resources = new ArrayList<>();
        when(gameMock.findAllResources()).thenReturn(resources);

        getResourcesUseCase.getResources();

        verify(resourceAssemblerMock).toDto(resources);
    }
}
