package ca.ulaval.glo4002.game.api.dinosaur.dtos;

public class DinosaurCreationRequest {
    public String name;
    public int weight;
    public String gender;
    public String species;

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }

    public String getSpecies() {
        return species;
    }
}
