package ca.ulaval.glo4002.game.domain.dinosaur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarnivoreFeedingOrder implements FeedingOrder, Serializable {
    private Comparator<Dinosaur> carnivoreComparator = Comparator.naturalOrder();

    @Override
    public List<Dinosaur> orderDinosaurs(List<Dinosaur> allDinosaurs) {
        List<Dinosaur> matchingDinosaurs = new ArrayList<>();

        for (Dinosaur dinosaur : allDinosaurs) {
            if (dinosaur.isCarnivore() || dinosaur.isOmnivore()) {
                matchingDinosaurs.add(dinosaur);
            }
        }

        matchingDinosaurs.sort(carnivoreComparator);
        return matchingDinosaurs;
    }
}
