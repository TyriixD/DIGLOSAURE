package ca.ulaval.glo4002.game.infrastructure.external_service.breed.dtos;

public class BreedingRequest {
    public final String fatherSpecies;
    public final String motherSpecies;

    public BreedingRequest(String fatherSpecies, String motherSpecies) {
        this.fatherSpecies = fatherSpecies;
        this.motherSpecies = motherSpecies;
    }
}
