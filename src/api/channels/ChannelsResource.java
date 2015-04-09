package api.channels;

import api.TwitchResource;
import api.TwitchResponse;

public class ChannelsResource extends TwitchResource {

    public ChannelsResource() {
        super();
    }

    public Channel getChannel(String name) {
        String url = String.format("%s/channels/%s", BASE_URL, name);
        TwitchResponse response = getRequest(url);

        Channel channel = new Channel();
        if (response.getCode() == TwitchResponse.HTTP_OK) {
            channel = parseResponse(response.getContent(), Channel.class);
        }

        return channel;
    }
}
