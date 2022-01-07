package ca.ulaval.glo4002.game.domain.resources;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class EqualSplitRationStrategyTest {
    private LunchBox lunchBoxMock;
    private EqualSplitRationStrategy equalSplitRationStrategy;
    private List<Resources> freshResources;
    private List<Resources> consumedResources;

    @BeforeEach
    public void setup() {
        lunchBoxMock = mock(LunchBox.class);
        equalSplitRationStrategy = new EqualSplitRationStrategy();
    }
}
