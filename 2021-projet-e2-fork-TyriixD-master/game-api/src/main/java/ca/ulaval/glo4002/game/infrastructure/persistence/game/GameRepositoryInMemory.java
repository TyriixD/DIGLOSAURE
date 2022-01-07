package ca.ulaval.glo4002.game.infrastructure.persistence.game;

import ca.ulaval.glo4002.game.application.dinosaur.exceptions.NoTurnToUnturnException;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.game.Park;
import java.util.Stack;

public class GameRepositoryInMemory implements GameRepository {
    private Stack<Park> parks = new Stack<>();
    private Game game;

    @Override
    public void save(Game game) {
        this.game = game;
    }

    @Override
    public void savePark(Park park) {
        parks.push(park);

    }

    @Override
    public Game find() {
        return game;
    }

    @Override
    public Park findPark() {
        return parks.peek();
    }

    @Override
    public void rollback() {
        parks.pop();
    }

    @Override
    public void reset() {
        parks.clear();

    }
}
