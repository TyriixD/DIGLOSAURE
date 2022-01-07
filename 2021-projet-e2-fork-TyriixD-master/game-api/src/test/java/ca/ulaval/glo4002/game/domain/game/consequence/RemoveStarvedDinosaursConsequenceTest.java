package ca.ulaval.glo4002.game.domain.game.consequence;

import ca.ulaval.glo4002.game.domain.game.Park;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RemoveStarvedDinosaursConsequenceTest {
    private RemoveStarvedDinosaursConsequence removeStarvedDinosaursConsequence;
    private Park parkMock;

    @BeforeEach
    public void setUpConsequence() {
        parkMock = mock(Park.class);
        removeStarvedDinosaursConsequence = new RemoveStarvedDinosaursConsequence(parkMock);
    }

    @Test
    public void givenAnOnGoingTurn_whenApplyIsCalled_thenParkShouldRemoveDinosaur() {
        removeStarvedDinosaursConsequence.apply();

        verify(parkMock).removeStarvedDinosaurs();
    }
}
