package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesCreationDto;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.resources.Resources;

import java.util.List;

public class AddResourcesUseCase {
    private final GameRepository gameRepository;
    private final ResourcesFactory resourcesFactory;

    public AddResourcesUseCase(GameRepository gameRepository, ResourcesFactory resourcesFactory) {
        this.gameRepository = gameRepository;
        this.resourcesFactory = resourcesFactory;
    }

    public void addResources(ResourcesCreationDto creationDto) {
        Game game = gameRepository.find();

        List<Resources> resources = resourcesFactory.create(creationDto.getBurgerQuantity(),
                                                            creationDto.getSaladQuantity(),
                                                            creationDto.getWaterQuantity());
        game.addResources(resources);

        gameRepository.save(game);
    }
}
