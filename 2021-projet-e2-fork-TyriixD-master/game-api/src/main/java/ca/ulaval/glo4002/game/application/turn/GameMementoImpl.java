package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.dinosaur.exceptions.NoTurnToUnturnException;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.game.Park;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameMementoImpl implements GameMemento{
    GameRepository gameRepository;
    public GameMementoImpl(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }
    @Override
    public Park recoverMemento() {
        if(isFirstTurn()){
            throw new NoTurnToUnturnException();
        }
        return gameRepository.findPark();
    }

    private boolean isFirstTurn() {
        return gameRepository.find().getTurnNumber() == 1;
    }

    @Override
    public void undo() {
        if(gameRepository.find().getTurnNumber() == 1){
            throw new NoTurnToUnturnException();
        }
        gameRepository.rollback();

    }

    @Override
    public void createMemento(Park park) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(park);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);

        Park parkMemento =  (Park) in.readObject();
        gameRepository.savePark(parkMemento);
    }
}
