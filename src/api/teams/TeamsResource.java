package api.teams;

import api.TwitchResource;
import http.HttpClient;
import http.HttpResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamsResource extends TwitchResource {

    public TeamsResource() {
        super();
    }

    public List<Team> teams(int limit, int offset) {
        HttpClient request = new HttpClient();
        List<Team> teams = new ArrayList<>();

        try {
            HttpResponse response = request.get(BASE_URL + "/teams/", getHeaders());
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

    public List<Team> teams(int limit) {
        throw new NotImplementedException();
    }

    public List<Team> teams() {
        throw new NotImplementedException();
    }

    public Team teams(String name) {
        throw new NotImplementedException();
    }


}
