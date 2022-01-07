package ca.ulaval.glo4002.game.application.resources.dtos;

public class CategorizedResourcesDto {
    private ResourcesDto fresh;
    private ResourcesDto expired;
    private ResourcesDto consumed;

    public CategorizedResourcesDto(ResourcesDto fresh, ResourcesDto expired, ResourcesDto consumed) {
        this.fresh = fresh;
        this.expired = expired;
        this.consumed = consumed;
    }

    public ResourcesDto getFresh() {
        return fresh;
    }

    public ResourcesDto getExpired() {
        return expired;
    }

    public ResourcesDto getConsumed() {
        return consumed;
    }
}
