package ca.ulaval.glo4002.game.application.breed;

import ca.ulaval.glo4002.game.application.breed.dtos.BreedingResponse;
import ca.ulaval.glo4002.game.application.breed.dtos.DinosaurBreedingDto;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.InvalidMotherException;
import ca.ulaval.glo4002.game.domain.breed.BreedingService;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.IncompatibleDinosaursForBreedingException;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

public class BreedDinosaursUseCase {
    private final GameRepository gameRepository;
    private final DinosaurFactory dinosaurFactory;
    private final BreedingService breedingService;

    public BreedDinosaursUseCase(GameRepository gameRepository, DinosaurFactory dinosaurFactory,
                                 BreedingService breedingService) {
        this.gameRepository = gameRepository;
        this.dinosaurFactory = dinosaurFactory;
        this.breedingService = breedingService;
    }

    public void breedDinosaurs(DinosaurBreedingDto breedingDto) {
        Game game = gameRepository.find();

        validateNameAvailability(breedingDto.getName(), game);

        Dinosaur father = game.findDinosaurByName(breedingDto.getFatherName());
        Dinosaur mother = game.findDinosaurByName(breedingDto.getMotherName());
        validateParentsGender(father, mother);

        BreedingResponse response = breedingService.breed(father, mother);
        Dinosaur offspring = dinosaurFactory.createOffspring(breedingDto.getName(), response.getGender(),
                                                             response.getSpecies());

        father.addChild(offspring);
        mother.addChild(offspring);

        game.addDinosaur(offspring);

        gameRepository.save(game);

    }

    private void validateNameAvailability(String offspringName, Game game) {
        if (game.checkIfDinosaurExists(offspringName)) {
            throw new DuplicateNameException();
        }
    }

    private void validateParentsGender(Dinosaur father, Dinosaur mother) {
        if (!father.isMale()) {
            throw new InvalidFatherException();
        }

        if (!mother.isFemale()) {
            throw new InvalidMotherException();
        }
    }
}
