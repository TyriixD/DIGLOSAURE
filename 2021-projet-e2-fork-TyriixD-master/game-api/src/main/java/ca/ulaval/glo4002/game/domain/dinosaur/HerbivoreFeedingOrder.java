package ca.ulaval.glo4002.game.domain.dinosaur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HerbivoreFeedingOrder implements FeedingOrder, Serializable {
    private Comparator<Dinosaur> herbivoreComparator = Comparator.reverseOrder();

    @Override
    public List<Dinosaur> orderDinosaurs(List<Dinosaur> allDinosaurs) {
        List<Dinosaur> matchingDinosaurs = new ArrayList<>();

        for (Dinosaur dinosaur : allDinosaurs) {
            if (dinosaur.isHerbivore() || dinosaur.isOmnivore()) {
                matchingDinosaurs.add(dinosaur);
            }
        }

        matchingDinosaurs.sort(herbivoreComparator);
        return matchingDinosaurs;
    }
}
