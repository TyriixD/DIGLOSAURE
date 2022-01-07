package ca.ulaval.glo4002.game.domain.game.consequence;

import ca.ulaval.glo4002.game.domain.game.Park;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RemoveExpiredResourcesConsequenceTest {
    private RemoveExpiredResourcesConsequence removeExpiredResourcesCommand;
    private Park parkMock;

    @BeforeEach
    public void setUp() {
        parkMock = mock(Park.class);
        removeExpiredResourcesCommand = new RemoveExpiredResourcesConsequence(parkMock);
    }

    @Test
    public void givenAnOnGoingTurn_whenApplyIsCalled_thenParkShouldDecrementShelfLifeAndRemoveExpiredResources() {
        removeExpiredResourcesCommand.apply();

        verify(parkMock).removeExpiredResources();
    }
}
