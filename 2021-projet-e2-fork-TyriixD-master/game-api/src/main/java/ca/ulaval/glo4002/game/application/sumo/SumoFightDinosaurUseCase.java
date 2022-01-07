package ca.ulaval.glo4002.game.application.sumo;

import ca.ulaval.glo4002.game.application.sumo.assemblers.SumoFightPredictionDtoAssembler;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightCreationDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightPredictionDto;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.sumo.SumoFightPrediction;

public class SumoFightDinosaurUseCase {
    private final GameRepository gameRepository;
    private final SumoFightPredictionDtoAssembler sumoFightPredictionDtoAssembler;

    public SumoFightDinosaurUseCase(GameRepository gameRepository,
                                    SumoFightPredictionDtoAssembler sumoFightPredictionDtoAssembler) {
        this.gameRepository = gameRepository;
        this.sumoFightPredictionDtoAssembler = sumoFightPredictionDtoAssembler;
    }

    public SumoFightPredictionDto stageSumoFight(SumoFightCreationDto sumoDto) {
        Game game = gameRepository.find();

        SumoFightPrediction sumoFightPrediction = game.predictSumoFightOutcome(sumoDto.getChallengerName(), sumoDto.getChallengeeName());

        gameRepository.save(game);

        return sumoFightPredictionDtoAssembler.toPredictionDto(sumoFightPrediction);
    }
}
