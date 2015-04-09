package api.teams;

import api.TwitchResource;
import api.TwitchResponse;
import java.util.ArrayList;
import java.util.List;

public class TeamsResource extends TwitchResource {

    private static final int DEFAULT_LIMIT = 25;

    public TeamsResource() {
        super();
    }

    public List<Team> getActiveTeams(int limit, int offset) {
        String url = String.format("%s/teams/?limit=%s&offset=%s", BASE_URL, limit, offset);
        TwitchResponse response = getRequest(url);

        List<Team> teams = new ArrayList<>();
        if (response.getCode() == TwitchResponse.HTTP_OK) {
            Teams teamsContainer = parseResponse(response.getContent(), Teams.class);
            teams = teamsContainer.getTeams();
        }

        return teams;
    }

    public List<Team> getActiveTeams(int limit) {
        return getActiveTeams(limit, 0);
    }

    public List<Team> getActiveTeams() {
        return getActiveTeams(DEFAULT_LIMIT, 0);
    }

    public Team getTeam(String name) {
        String url = String.format("%s/teams/%s", BASE_URL, name);
        TwitchResponse response = getRequest(url);

        Team team = new Team();
        if (response.getCode() == TwitchResponse.HTTP_OK) {
            team = parseResponse(response.getContent(), Team.class);
        }

        return team;
    }


}
