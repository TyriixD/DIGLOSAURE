package ca.ulaval.glo4002.game.domain.infrastructure;

import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurHerd;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidBabyWeightChangeException;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.game.Park;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.sumo.SumoRing;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.infrastructure.persistence.game.GameRepositoryInMemory;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class GameRepositoryTest {
    private GameRepository gameRepository;
    private Game game;
    private Park park;
    private Turn turnMock;
    private Pantry pantryMock;
    private DinosaurHerd dinosaurHerdMock;
    private SumoRing sumoRingMock;

    @BeforeEach
    public void setUpCreationCommand() {
        gameRepository = new GameRepositoryInMemory();
        pantryMock = mock(Pantry.class);
        dinosaurHerdMock = mock(DinosaurHerd.class);
        sumoRingMock = mock(SumoRing.class);
        park = new Park(pantryMock,dinosaurHerdMock,sumoRingMock);
        turnMock = mock(Turn.class);
        game = new Game(park,turnMock);
    }

    @Test
    public void givenAGameToSave_whenSaving_thenAGameIsSaved() {
        gameRepository.save(game);

        assertEquals(gameRepository.find(),game);
    }

    @Test
    public void givenAParkToSave_whenSavePark_thenParkIsAdded() {
        gameRepository.savePark(park);

        assertEquals(gameRepository.findPark(),park);
    }

    @Test
    public void whenResetingRepo_thenRepoIsCleared() {
        gameRepository.savePark(park);

        gameRepository.reset();

        assertThrows(EmptyStackException.class,
            () -> gameRepository.findPark());
    }

    @Test
    public void whenRollingBack_thenFirstParkIsReturned() {
        Park parkMock = mock(Park.class);
        gameRepository.savePark(park);
        gameRepository.savePark(parkMock);

        gameRepository.rollback();

        assertEquals(park,gameRepository.findPark());

    }

}
