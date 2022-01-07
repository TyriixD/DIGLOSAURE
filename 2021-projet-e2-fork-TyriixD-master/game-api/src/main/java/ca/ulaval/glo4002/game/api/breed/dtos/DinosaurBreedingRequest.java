package ca.ulaval.glo4002.game.api.breed.dtos;

public class DinosaurBreedingRequest {
    public String name;
    public String fatherName;
    public String motherName;

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }
}
