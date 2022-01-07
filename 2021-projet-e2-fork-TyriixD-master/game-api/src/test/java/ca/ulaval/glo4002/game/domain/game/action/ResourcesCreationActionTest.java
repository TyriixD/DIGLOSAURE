package ca.ulaval.glo4002.game.domain.game.action;

import ca.ulaval.glo4002.game.domain.game.Park;
import ca.ulaval.glo4002.game.domain.resources.ResourceInfo;
import ca.ulaval.glo4002.game.domain.resources.ResourceState;
import ca.ulaval.glo4002.game.domain.resources.Resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ResourcesCreationActionTest {
    private ResourceCreationAction resourceCreationCommand;
    private Park parkMock;
    private Resources resources;

    @BeforeEach
    public void setUpResourceCreationCommand() {
        parkMock = mock(Park.class);
        resources = new Resources(ResourceInfo.SALAD, ResourceState.FRESH, 10);
        resourceCreationCommand = new ResourceCreationAction(resources, parkMock);
    }

    @Test
    public void givenResources_whenExecuteIsCalled_thenParkShouldAppendResources() {
        resourceCreationCommand.execute();

        verify(parkMock).addResources(resources);
    }
}
