package api;

import api.auth.Authenticator;
import api.resources.*;

import java.util.HashMap;
import java.util.Map;

public class Twitch {

    public static final String BASE_URL = "https://api.twitch.tv/kraken";
    public static final String API_VERSION = "v3";
    Map<String, AbstractResource> res = new HashMap<String, AbstractResource>();
    private String clientId; // User's app client Id
    private Authenticator authenticator;

    public Twitch() {
        authenticator = new Authenticator(BASE_URL);
        // Instantiate resource connectors
        res.put("channels", new ChannelsResource(BASE_URL, API_VERSION));
        res.put("chat", new ChatResource(BASE_URL, API_VERSION));
        res.put("games", new GamesResource(BASE_URL, API_VERSION));
        res.put("ingests", new IngestsResource(BASE_URL, API_VERSION));
        res.put("root", new RootResource(BASE_URL, API_VERSION));
        res.put("search", new SearchResource(BASE_URL, API_VERSION));
        res.put("streams", new StreamsResource(BASE_URL, API_VERSION));
        res.put("teams", new TeamsResource(BASE_URL, API_VERSION));
        res.put("users", new UsersResource(BASE_URL, API_VERSION));
        res.put("videos", new VideosResource(BASE_URL, API_VERSION));
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
        // Update client id in all resources
        for (AbstractResource r : res.values()) {
            r.setClientId(clientId);
        }
    }

    public AbstractResource getResource(String key) {
        AbstractResource r = res.get(key);
        r.setAuthAccessToken(authenticator.getAccessToken());
        return r;
    }

    public Authenticator auth() {
        return authenticator;
    }

    public ChannelsResource channels() {
        return (ChannelsResource) getResource("channels");
    }

    public ChatResource chat() {
        return (ChatResource) getResource("chat");
    }

    public GamesResource games() {
        return (GamesResource) getResource("games");
    }

    public IngestsResource ingests() {
        return (IngestsResource) getResource("ingests");
    }

    public RootResource root() {
        return (RootResource) getResource("root");
    }

    public SearchResource search() {
        return (SearchResource) getResource("search");
    }

    public StreamsResource streams() {
        return (StreamsResource) getResource("streams");
    }

    public TeamsResource teams() {
        return (TeamsResource) getResource("teams");
    }

    public UsersResource users() {
        return (UsersResource) getResource("users");
    }

    public VideosResource videos() {
        return (VideosResource) getResource("videos");
    }
}
