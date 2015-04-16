package api.resources;

import api.TwitchResponse;
import api.models.Games;
import http.HttpResponse;

import java.io.IOException;

public class GamesResource extends AbstractResource {

    public GamesResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<Games> get(int limit, int offset) throws IOException {
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/games/top?limit=%s&offset=%s",
                getBaseUrl(), limit, offset);

        return requestGet(url, HttpResponse.HTTP_OK, Games.class);
    }

    public TwitchResponse<Games> get(int limit) throws IOException {
        return get(limit, 0);
    }

    public TwitchResponse<Games> get() throws IOException {
        return get(10, 0);
    }
}
