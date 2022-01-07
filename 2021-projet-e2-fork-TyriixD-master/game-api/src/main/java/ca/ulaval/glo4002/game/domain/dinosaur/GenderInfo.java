package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidGenderException;
import java.io.Serializable;

public enum GenderInfo implements Serializable {
    MALE("m", 1.0), FEMALE("f", 1.5);

    public final String gender;
    public final double strengthMultiplier;

    GenderInfo(String gender, double strengthMultiplier) {
        this.gender = gender;
        this.strengthMultiplier = strengthMultiplier;
    }

    public static GenderInfo getGenderInfo(String gender) {
        for (GenderInfo genderInfo : values()) {
            if (genderInfo.gender.equals(gender)) {
                return genderInfo;
            }
        }
        throw new InvalidGenderException();
    }
}
