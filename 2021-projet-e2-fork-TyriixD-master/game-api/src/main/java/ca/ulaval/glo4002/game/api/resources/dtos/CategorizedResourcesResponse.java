package ca.ulaval.glo4002.game.api.resources.dtos;

public class CategorizedResourcesResponse {
    public final ResourcesResponse fresh;
    public final ResourcesResponse expired;
    public final ResourcesResponse consumed;

    public CategorizedResourcesResponse(ResourcesResponse fresh, ResourcesResponse expired,
                                        ResourcesResponse consumed) {
        this.fresh = fresh;
        this.expired = expired;
        this.consumed = consumed;
    }
}
