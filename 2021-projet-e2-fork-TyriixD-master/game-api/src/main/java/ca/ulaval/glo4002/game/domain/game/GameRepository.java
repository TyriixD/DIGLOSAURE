package ca.ulaval.glo4002.game.domain.game;

public interface GameRepository {
    void save(Game game);

    void savePark(Park park);

    Game find();

    Park findPark();

    void rollback();

    void reset();
}
