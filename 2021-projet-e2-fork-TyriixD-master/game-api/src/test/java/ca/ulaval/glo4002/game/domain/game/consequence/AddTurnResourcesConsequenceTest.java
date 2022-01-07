package ca.ulaval.glo4002.game.domain.game.consequence;

import ca.ulaval.glo4002.game.domain.game.Park;
import ca.ulaval.glo4002.game.domain.resources.Resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddTurnResourcesConsequenceTest {
    private AddTurnResourcesConsequence addTurnResourcesConsequence;
    private Park parkMock;

    @BeforeEach
    public void setUp() {
        parkMock = mock(Park.class);
        addTurnResourcesConsequence = new AddTurnResourcesConsequence(parkMock);
    }

    @Test
    public void givenAnOnGoingTurn_whenApplyIsCalled_thenParkShouldAddResources() {
        addTurnResourcesConsequence.apply();

        verify(parkMock, times(3)).addResources(any(Resources.class));
    }
}
