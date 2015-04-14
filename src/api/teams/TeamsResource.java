package api.teams;

import api.ErrorResponse;
import api.TwitchResource;
import api.TwitchResponse;
import api.teams.models.Team;
import api.teams.models.Teams;
import http.HttpResponse;
import java.util.List;

public class TeamsResource extends TwitchResource {

    private static final int DEFAULT_LIMIT = 25;
    private static final int MIN_LIMIT = 1;
    private static final int MAX_LIMIT = 100;

    public TeamsResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<List<Team>> get(int limit, int offset) {
        // Constrain limit
        limit = Math.max(limit, MIN_LIMIT);
        limit = Math.min(limit, MAX_LIMIT);

        String url = String.format("%s/teams/?limit=%s&offset=%s", getBaseUrl(), limit, offset);
        TwitchResponse<Teams> container = requestGet(url, HttpResponse.HTTP_OK, Teams.class);

        // Create object with list rather than the container class
        TwitchResponse<List<Team>> response = new TwitchResponse<List<Team>>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getTeams());
        }

        return response;
    }

    public TwitchResponse<List<Team>> get(int limit) {
        return get(limit, 0);
    }

    public TwitchResponse<List<Team>> get() {
        return get(DEFAULT_LIMIT, 0);
    }

    public TwitchResponse<Team> get(String teamName) {
        String url = String.format("%s/teams/%s", getBaseUrl(), teamName);
        return requestGet(url, HttpResponse.HTTP_OK, Team.class);
    }
}
