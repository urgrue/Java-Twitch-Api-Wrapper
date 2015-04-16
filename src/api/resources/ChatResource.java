package api.resources;

import api.TwitchResponse;
import api.models.ChannelBadges;
import api.models.Emoticon;
import api.models.Emoticons;
import http.HttpResponse;

import java.io.IOException;
import java.util.List;

public class ChatResource extends AbstractResource {

    public ChatResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<List<Emoticon>> getEmoticons() throws IOException {
        String url = String.format("%s/chat/emoticons", getBaseUrl());
        TwitchResponse<Emoticons> container = requestGet(url, HttpResponse.HTTP_OK, Emoticons.class);

        // Create object with list rather than the container class
        TwitchResponse<List<Emoticon>> response = new TwitchResponse<List<Emoticon>>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getEmoticons());
        }

        return response;
    }

    public TwitchResponse<ChannelBadges> getChannelBadges(String channelName) throws IOException {
        String url = String.format("%s/chat/%s/badges", getBaseUrl(), channelName);
        return requestGet(url, HttpResponse.HTTP_OK, ChannelBadges.class);
    }
}
