package ca.ulaval.glo4002.game.api.sumo.assemblers;

import ca.ulaval.glo4002.game.api.sumo.dtos.SumoFightRequest;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoFightCreationDto;

public class SumoFightCreationDtoAssembler {
    public SumoFightCreationDto toCreationDto(SumoFightRequest request) {
        return new SumoFightCreationDto(request.getChallengerName(), request.getChallengeeName());
    }
}
