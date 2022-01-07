package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.application.turn.GameMemento;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

public class AddDinosaurUseCase {
    private final GameRepository gameRepository;
    private final DinosaurFactory dinosaurFactory;
    private final GameMemento gameMemento;

    public AddDinosaurUseCase(GameRepository gameRepository, DinosaurFactory dinosaurFactory,GameMemento gameMemento) {
        this.gameRepository = gameRepository;
        this.dinosaurFactory = dinosaurFactory;
        this.gameMemento = gameMemento;
    }

    public void addDinosaur(DinosaurCreationDto creationDto) {
        Game game = gameRepository.find();

        Dinosaur dinosaur = dinosaurFactory.create(creationDto.getName(), creationDto.getWeight(),
                                                   creationDto.getGender(), creationDto.getSpecies());
        game.addDinosaur(dinosaur);

        gameRepository.save(game);
    }
}
