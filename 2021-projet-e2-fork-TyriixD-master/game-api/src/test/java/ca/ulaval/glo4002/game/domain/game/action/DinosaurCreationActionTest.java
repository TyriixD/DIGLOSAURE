package ca.ulaval.glo4002.game.domain.game.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.GenderInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.SpeciesInfo;
import ca.ulaval.glo4002.game.domain.dinosaur.Weight;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreDrinkingBehaviour;
import ca.ulaval.glo4002.game.domain.dinosaur.behaviours.CarnivoreEatingBehaviour;
import ca.ulaval.glo4002.game.domain.game.Park;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DinosaurCreationActionTest {
    private DinosaurCreationAction dinosaurCreationCommand;
    private Park parkMock;
    private Dinosaur dinosaur;

    @BeforeEach
    public void setUpDinoCreationCommand() {
        parkMock = mock(Park.class);

        dinosaur = new Dinosaur("Jean-Luc", Weight.fromKg(100), GenderInfo.getGenderInfo("m"),
                                SpeciesInfo.getSpeciesInfo("Velociraptor"), new CarnivoreEatingBehaviour(),
                                new CarnivoreDrinkingBehaviour());
        dinosaurCreationCommand = new DinosaurCreationAction(dinosaur, parkMock);
    }

    @Test
    public void givenAnOnGoingTurn_whenExecuteIsCalled_thenParkAddDinosaur() {
        dinosaurCreationCommand.execute();

        verify(parkMock).addDinosaur(dinosaur);
    }
}
