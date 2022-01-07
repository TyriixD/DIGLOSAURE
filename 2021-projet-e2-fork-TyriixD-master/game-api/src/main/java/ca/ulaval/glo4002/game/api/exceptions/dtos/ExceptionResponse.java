package ca.ulaval.glo4002.game.api.exceptions.dtos;

public class ExceptionResponse {
    public final String error;
    public final String description;

    public ExceptionResponse(String error, String description) {
        this.error = error;
        this.description = description;
    }
}
