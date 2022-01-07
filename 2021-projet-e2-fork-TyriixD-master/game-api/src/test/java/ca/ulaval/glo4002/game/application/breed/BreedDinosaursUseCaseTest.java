package ca.ulaval.glo4002.game.application.breed;

import ca.ulaval.glo4002.game.application.breed.dtos.BreedingResponse;
import ca.ulaval.glo4002.game.application.breed.dtos.DinosaurBreedingDto;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.application.dinosaur.exceptions.InvalidMotherException;
import ca.ulaval.glo4002.game.domain.breed.BreedingService;
import ca.ulaval.glo4002.game.domain.dinosaur.*;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.HerbivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BreedDinosaursUseCaseTest {
    private BreedDinosaursUseCase breedDinosaursUseCase;
    private GameRepository gameRepositoryMock;
    private DinosaurFactory dinosaurFactoryMock;
    private BreedingService breedingServiceMock;
    private Game gameMock;
    private DinosaurBreedingDto breedingDto;
    private final Dinosaur father = createFather();
    private final Dinosaur mother = createMother();
    private BreedingResponse response;

    @BeforeEach
    public void setupBreedDinosaursUseCase() {
        breedingDto = new DinosaurBreedingDto("name", father.getName(), mother.getName());
        gameRepositoryMock = mock(GameRepository.class);
        breedingServiceMock = mock(BreedingService.class);
        dinosaurFactoryMock = mock(DinosaurFactory.class);
        breedDinosaursUseCase = new BreedDinosaursUseCase(gameRepositoryMock, dinosaurFactoryMock, breedingServiceMock);
        gameMock = mock(Game.class);
        when(gameRepositoryMock.find()).thenReturn(gameMock);
        response = mock(BreedingResponse.class);
    }

    @Test
    public void whenBreedDinosaurs_thenAddDinosaurIsCalled() {
        Dinosaur offspring = mock(Dinosaur.class);
        when(gameMock.findDinosaurByName(breedingDto.getFatherName())).thenReturn(father);
        when(gameMock.findDinosaurByName(breedingDto.getMotherName())).thenReturn(mother);
        when(breedingServiceMock.breed(father, mother)).thenReturn(response);
        when(dinosaurFactoryMock.createOffspring(breedingDto.getName(), response.getGender(), response.getSpecies())).thenReturn(offspring);

        breedDinosaursUseCase.breedDinosaurs(breedingDto);

        verify(gameMock).addDinosaur(offspring);
    }

    @Test
    public void whenBreedDinosaurs_thenCreateOffspringIsCalled() {
        BreedingResponse response = new BreedingResponse(SpeciesInfo.VELOCIRAPTOR, GenderInfo.FEMALE);
        when(gameMock.findDinosaurByName(breedingDto.getFatherName())).thenReturn(father);
        when(gameMock.findDinosaurByName(breedingDto.getMotherName())).thenReturn(mother);
        when(breedingServiceMock.breed(father, mother)).thenReturn(response);

        breedDinosaursUseCase.breedDinosaurs(breedingDto);

        verify(dinosaurFactoryMock).createOffspring(breedingDto.getName(), response.getGender(), response.getSpecies());
    }

    @Test
    public void givenInvalidFatherGender_whenBreedDinosaurs_thenThrowInvalidFatherException() {
        Dinosaur father2 = new Dinosaur("father", Weight.fromKg(100), GenderInfo.FEMALE, SpeciesInfo.ALLOSAURUS, new HerbivoreEatingBehaviour(), new HerbivoreDrinkingBehaviour());
        when(gameRepositoryMock.find()).thenReturn(gameMock);
        when(gameMock.findDinosaurByName(breedingDto.getFatherName())).thenReturn(father2);
        when(gameMock.findDinosaurByName(breedingDto.getMotherName())).thenReturn(mother);

        assertThrows(InvalidFatherException.class, () -> breedDinosaursUseCase.breedDinosaurs(breedingDto));
    }

    @Test
    public void givenInvalidMotherGender_whenBreedDinosaurs_thenThrowInvalidMotherException() {
        Dinosaur motherWrongGender = new Dinosaur("mother", Weight.fromKg(100), GenderInfo.MALE, SpeciesInfo.ALLOSAURUS, new HerbivoreEatingBehaviour(), new HerbivoreDrinkingBehaviour());
        when(gameMock.findDinosaurByName(breedingDto.getFatherName())).thenReturn(father);
        when(gameMock.findDinosaurByName(breedingDto.getMotherName())).thenReturn(motherWrongGender);

        assertThrows(InvalidMotherException.class, () -> breedDinosaursUseCase.breedDinosaurs(breedingDto));
    }

    private Dinosaur createFather() {
        return new Dinosaur("father", Weight.fromKg(100), GenderInfo.MALE, SpeciesInfo.ALLOSAURUS, new HerbivoreEatingBehaviour(), new HerbivoreDrinkingBehaviour());
    }

    private Dinosaur createMother() {
        return new Dinosaur("mother", Weight.fromKg(100), GenderInfo.FEMALE, SpeciesInfo.ALLOSAURUS, new HerbivoreEatingBehaviour(), new HerbivoreDrinkingBehaviour());
    }
}
