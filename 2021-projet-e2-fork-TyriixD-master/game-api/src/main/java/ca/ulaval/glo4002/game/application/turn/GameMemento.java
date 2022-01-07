package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.Park;
import java.io.IOException;

public interface GameMemento {
    public Park recoverMemento();
    public void undo();
    public void createMemento(Park park) throws IOException, ClassNotFoundException;
}
