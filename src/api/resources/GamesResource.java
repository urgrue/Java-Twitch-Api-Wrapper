package api.resources;

import api.TwitchResponse;
import api.models.Games;
import http.HttpResponse;

public class GamesResource extends TwitchResource {

    public GamesResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<Games> get(int limit, int offset) {
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/games/top?limit=%s&offset=%s",
                getBaseUrl(), limit, offset);

        return requestGet(url, HttpResponse.HTTP_OK, Games.class);
    }

    public TwitchResponse<Games> get(int limit) {
        return get(limit, 0);
    }

    public TwitchResponse<Games> get() {
        return get(10, 0);
    }
}
