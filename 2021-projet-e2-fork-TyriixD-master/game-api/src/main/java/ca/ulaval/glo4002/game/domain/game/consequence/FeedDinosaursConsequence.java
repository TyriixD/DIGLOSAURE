package ca.ulaval.glo4002.game.domain.game.consequence;

import ca.ulaval.glo4002.game.domain.game.Park;

public class FeedDinosaursConsequence implements Consequence {
    private final Park park;

    public FeedDinosaursConsequence(Park park) {
        this.park = park;
    }

    @Override
    public void apply() {
        park.feedDinosaurs();
    }
}
