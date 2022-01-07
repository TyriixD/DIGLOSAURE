package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.turn.assemblers.TurnDtoAssembler;
import ca.ulaval.glo4002.game.application.turn.dtos.TurnDto;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.game.Park;
import java.io.IOException;

public class TurnUseCase {
    private final GameRepository gameRepository;
    private final TurnDtoAssembler turnDtoAssembler;
    private final GameMemento gameMemento;

    public TurnUseCase(GameRepository gameRepository, TurnDtoAssembler turnDtoAssembler, GameMemento gameMemento) {
        this.gameRepository = gameRepository;
        this.turnDtoAssembler = turnDtoAssembler;
        this.gameMemento = gameMemento;
    }

    public TurnDto executeTurn() throws IOException, ClassNotFoundException {
        Game game = gameRepository.find();

        Park memento = game.getPark();
        gameMemento.createMemento(memento);

        TurnDto dto = turnDtoAssembler.toDto(game.getTurnNumber());
        game.executeTurn();
        gameRepository.save(game);

        return dto;
    }

    public TurnDto unexecuteTurn() {
        Game game = gameRepository.find();
        Park recoveredState = gameMemento.recoverMemento();
        gameMemento.undo();
        game.recoverState(recoveredState);
        game.unExecuteTurn();
        gameRepository.save(game);

        return turnDtoAssembler.toDto(game.getTurnNumber());
    }
}
