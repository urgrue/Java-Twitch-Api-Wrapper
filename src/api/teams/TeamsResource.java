package api.teams;

import api.TwitchResource;
import http.HttpClient;
import http.HttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamsResource extends TwitchResource {

    private static final int DEFAULT_LIMIT = 25;

    public TeamsResource() {
        super();
    }

    public List<Team> getActiveTeams(int limit, int offset) {
        HttpClient request = new HttpClient();
        List<Team> teams = new ArrayList<>();

        try {
            String url = String.format("%s/teams/?limit=%s&offset=%s", BASE_URL, limit, offset);
            HttpResponse response = request.get(url, getHeaders());
            String content = response.getContent();

            if (content != null) {
                Teams teamsL = parseResponse(content, Teams.class);
                teams = teamsL.getTeams();
            }

        } catch (IOException e) {
            e.printStackTrace();
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
        HttpClient request = new HttpClient();
        Team team = new Team();

        try {
            String url = String.format("%s/teams/%s", BASE_URL, name);
            HttpResponse response = request.get(url, getHeaders());
            String content = response.getContent();

            if (content != null) {
                team = parseResponse(content, Team.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return team;
    }


}
