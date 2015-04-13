package api;

import api.channels.ChannelsResource;
import api.chat.ChatResource;
import api.teams.TeamsResource;

public class Twitch {

    public static final String BASE_URL = "https://api.twitch.tv/kraken";
    public static final String API_VERSION = "v3";

    private ChannelsResource channelsResource;
    private TeamsResource teamsResource;
    private ChatResource chatResource;

    public Twitch() {
        // Load resource connectors
        channelsResource = new ChannelsResource(BASE_URL, API_VERSION);
        teamsResource = new TeamsResource(BASE_URL, API_VERSION);
        chatResource = new ChatResource(BASE_URL, API_VERSION);
    }

    public ChannelsResource channels() {
        return channelsResource;
    }

    public TeamsResource teams() {
        return teamsResource;
    }

    public ChatResource chat() {
        return chatResource;
    }
}
