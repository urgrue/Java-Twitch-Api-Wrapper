package api;

import api.blocks.BlocksResource;
import api.channels.ChannelsResource;
import api.chat.ChatResource;
import api.games.GamesResource;
import api.teams.TeamsResource;
import auth.Authenticator;

import java.util.HashMap;
import java.util.Map;

public class Twitch {

    public static final String BASE_URL = "https://api.twitch.tv/kraken";
    public static final String API_VERSION = "v3";

    private String clientId; // User's app client Id

    private Authenticator authenticator;
    Map<String, TwitchResource> res = new HashMap<String, TwitchResource>();

    public Twitch() {
        authenticator = new Authenticator(BASE_URL);
        // Init resource connectors
        res.put("blocks", new BlocksResource(BASE_URL, API_VERSION));
        res.put("channels", new ChannelsResource(BASE_URL, API_VERSION));
        res.put("chat", new ChatResource(BASE_URL, API_VERSION));
        res.put("games", new GamesResource(BASE_URL, API_VERSION));
        res.put("teams", new TeamsResource(BASE_URL, API_VERSION));
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
        // Update client id in all resources
        for (TwitchResource r : res.values()) {
            r.setClientId(clientId);
        }
    }

    public String getClientId() {
        return clientId;
    }

    public TwitchResource getResource(String key) {
        TwitchResource r = res.get(key);
        r.setAuthAccessToken(authenticator.getAccessToken());
        return r;
    }

    public Authenticator auth() {
        return authenticator;
    }

    public BlocksResource blocks() {
        return (BlocksResource) getResource("blocks");
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

    public TeamsResource teams() {
        return (TeamsResource) getResource("teams");
    }
}
