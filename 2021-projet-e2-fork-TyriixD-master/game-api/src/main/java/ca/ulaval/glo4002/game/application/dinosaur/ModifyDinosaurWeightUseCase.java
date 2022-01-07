package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurWeightModificationDto;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

public class ModifyDinosaurWeightUseCase {
    private final GameRepository gameRepository;

    public ModifyDinosaurWeightUseCase(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void modifyDinosaurWeight(DinosaurWeightModificationDto weightModificationDto) {
        Game game = gameRepository.find();
        game.validateAndApplyDinoForWeightChange(weightModificationDto.getName(),weightModificationDto.getWeightDifference());
        gameRepository.save(game);
    }
}
