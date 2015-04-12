package api.channels;

import api.ErrorResponse;
import api.TwitchResource;
import api.TwitchResponse;
import api.channels.models.Channel;
import http.HttpResponse;

public class ChannelsResource extends TwitchResource {

    public ChannelsResource() {
        super();
    }

    public TwitchResponse<Channel> getChannel(String name) {
        String url = String.format("%s/channels/%s", BASE_URL, name);
        HttpResponse response = getRequest(url);

        Channel channel = null;

        int statusCode = response.getCode();
        TwitchResponse<Channel> twitchResponse = new TwitchResponse<>(response);

        if (statusCode == HttpResponse.HTTP_OK) {
            channel = parseResponse(response.getContent(), Channel.class);
        } else { // Not found
            ErrorResponse error = parseResponse(response.getContent(), ErrorResponse.class);
            twitchResponse.setErrorMessage(error.getMessage());
        }

        twitchResponse.setObject(channel);

        return twitchResponse;
    }
}
