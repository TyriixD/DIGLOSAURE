package ca.ulaval.glo4002.game.infrastructure.external_service.breed.dtos;

public class BreedingResult {
    private String offspring;
    private String gender;

    public BreedingResult() {
    }

    public BreedingResult(String offspring, String gender) {
        this.offspring = offspring;
        this.gender = gender;
    }

    public String getOffspring() {
        return offspring;
    }

    public String getGender() {
        return gender;
    }
}
