package ca.ulaval.glo4002.game.domain.game.consequence;

import ca.ulaval.glo4002.game.domain.game.Park;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FeedDinosaurConsequenceTest {
    private FeedDinosaursConsequence feedDinosaursConsequence;
    private Park parkMock;

    @BeforeEach
    public void setUpFeedDinoConsequence() {
        parkMock = mock(Park.class);
        feedDinosaursConsequence = new FeedDinosaursConsequence(parkMock);
    }

    @Test
    public void givenAnOnGoingTurn_whenApplyIsCalled_thenParkShouldAddResources() {
        feedDinosaursConsequence.apply();

        verify(parkMock).feedDinosaurs();
    }
}
