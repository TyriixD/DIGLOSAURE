package ca.ulaval.glo4002.game.domain.game.consequence;

import ca.ulaval.glo4002.game.domain.game.Park;

public class GrowBabyDinosaursConsequence implements Consequence {
    private final Park park;

    public GrowBabyDinosaursConsequence(Park park) {
        this.park = park;
    }

    @Override
    public void apply() {
        park.growBabyDinosaurs();
    }
}
