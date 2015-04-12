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

    public TeamsResource() {
        super();
    }

    public TwitchResponse<List<Team>> getActiveTeams(int limit, int offset) {
        // Constrain limit
        limit = Math.max(limit, MIN_LIMIT);
        limit = Math.min(limit, MAX_LIMIT);

        String url = String.format("%s/teams/?limit=%s&offset=%s", BASE_URL, limit, offset);
        HttpResponse response = getRequest(url);

        List<Team> teams = null;

        int statusCode = response.getCode();
        TwitchResponse<List<Team>> twitchResponse = new TwitchResponse<>(response);

        if (statusCode == HttpResponse.HTTP_OK) {
            Teams teamsContainer = parseResponse(response.getContent(), Teams.class);
            teams = teamsContainer.getTeams();
        } else {
            ErrorResponse error = parseResponse(response.getContent(), ErrorResponse.class);
            twitchResponse.setErrorMessage(error.getMessage());
        }

        twitchResponse.setObject(teams);

        return twitchResponse;
    }

    public TwitchResponse<List<Team>> getActiveTeams(int limit) {
        return getActiveTeams(limit, 0);
    }

    public TwitchResponse<List<Team>> getActiveTeams() {
        return getActiveTeams(DEFAULT_LIMIT, 0);
    }

    public TwitchResponse<Team> getTeam(String name) {
        String url = String.format("%s/teams/%s", BASE_URL, name);
        HttpResponse response = getRequest(url);

        Team team = null;

        int statusCode = response.getCode();
        TwitchResponse<Team> twitchResponse = new TwitchResponse<>(response);

        if (statusCode == HttpResponse.HTTP_OK) {
            team = parseResponse(response.getContent(), Team.class);
        } else { // Not found
            ErrorResponse error = parseResponse(response.getContent(), ErrorResponse.class);
            twitchResponse.setErrorMessage(error.getMessage());
        }

        twitchResponse.setObject(team);

        return twitchResponse;
    }
}
