package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.List;

public interface FeedingOrder {
    List<Dinosaur> orderDinosaurs(List<Dinosaur> allDinosaurs);
}
