import api.channels.ChannelsResource;
import api.teams.TeamsResource;

public class Twitch {

    private ChannelsResource channelsResource;
    private TeamsResource teamsResource;

    public Twitch() {
        // Load resource connectors
        channelsResource = new ChannelsResource();
        teamsResource = new TeamsResource();
    }

    public ChannelsResource channels() {
        return channelsResource;
    }

    public TeamsResource teams() {
        return teamsResource;
    }
}
