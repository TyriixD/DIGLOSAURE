package ca.ulaval.glo4002.game.application.game;

import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

public class ResetGameUseCase {
    private final GameRepository gameRepository;

    public ResetGameUseCase(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void reset() {
        Game game = gameRepository.find();
        game.reset();
        gameRepository.reset();
        gameRepository.save(game);
    }
}
