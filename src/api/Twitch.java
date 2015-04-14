package api;

import api.channels.ChannelsResource;
import api.chat.ChatResource;
import api.teams.TeamsResource;
import auth.Authenticator;

public class Twitch {

    public static final String BASE_URL = "https://api.twitch.tv/kraken";
    public static final String API_VERSION = "v3";

    private String clientId; // User's app client Id

    private Authenticator authenticator;
    private ChannelsResource channelsResource;
    private TeamsResource teamsResource;
    private ChatResource chatResource;

    public Twitch() {
        // Load resource connectors
        authenticator = new Authenticator(BASE_URL);
        channelsResource = new ChannelsResource(BASE_URL, API_VERSION);
        teamsResource = new TeamsResource(BASE_URL, API_VERSION);
        chatResource = new ChatResource(BASE_URL, API_VERSION);
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
        // Update client Id in all resources
        channelsResource.setClientId(clientId);
        teamsResource.setClientId(clientId);
        chatResource.setClientId(clientId);
    }

    public String getClientId() {
        return clientId;
    }

    public Authenticator auth() {
        return authenticator;
    }

    public ChannelsResource channels() {
        channelsResource.setAuthAccessToken(authenticator.getAccessToken());
        return channelsResource;
    }

    public TeamsResource teams() {
        teamsResource.setAuthAccessToken(authenticator.getAccessToken());
        return teamsResource;
    }

    public ChatResource chat() {
        chatResource.setAuthAccessToken(authenticator.getAccessToken());
        return chatResource;
    }
}
