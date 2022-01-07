package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.application.resources.assemblers.CategorizedResourcesDtoAssembler;
import ca.ulaval.glo4002.game.application.resources.dtos.CategorizedResourcesDto;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

public class GetResourcesUseCase {
    private final GameRepository gameRepository;
    private final CategorizedResourcesDtoAssembler resourceAssembler;

    public GetResourcesUseCase(GameRepository gameRepository, CategorizedResourcesDtoAssembler resourceAssembler) {
        this.gameRepository = gameRepository;
        this.resourceAssembler = resourceAssembler;
    }

    public CategorizedResourcesDto getResources() {
        Game game = gameRepository.find();
        return resourceAssembler.toDto(game.findAllResources());
    }
}
