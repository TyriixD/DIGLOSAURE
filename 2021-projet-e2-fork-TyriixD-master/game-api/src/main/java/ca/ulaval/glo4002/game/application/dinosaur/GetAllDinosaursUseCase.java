package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.assemblers.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

import java.util.List;

public class GetAllDinosaursUseCase {
    private final GameRepository gameRepository;
    private final DinosaurDtoAssembler dinosaurDtoAssembler;

    public GetAllDinosaursUseCase(GameRepository gameRepository, DinosaurDtoAssembler dinosaurDtoAssembler) {
        this.gameRepository = gameRepository;
        this.dinosaurDtoAssembler = dinosaurDtoAssembler;
    }

    public List<DinosaurDto> getAllDinosaurs() {
        Game game = gameRepository.find();
        return dinosaurDtoAssembler.toDto(game.findAllDinosaurs());
    }
}
