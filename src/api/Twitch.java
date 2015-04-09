package api;

import api.channels.ChannelsResource;
import api.chat.ChatResource;
import api.teams.TeamsResource;

public class Twitch {

    private ChannelsResource channelsResource;
    private TeamsResource teamsResource;
    private ChatResource chatResource;

    public Twitch() {
        // Load resource connectors
        channelsResource = new ChannelsResource();
        teamsResource = new TeamsResource();
        chatResource = new ChatResource();
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
