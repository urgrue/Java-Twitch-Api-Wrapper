package api.chat;

import api.ErrorResponse;
import api.Twitch;
import api.TwitchResource;
import api.TwitchResponse;
import api.chat.models.ChannelBadges;
import api.chat.models.Emoticon;
import api.chat.models.Emoticons;
import http.HttpResponse;

import java.util.List;

public class ChatResource extends TwitchResource {

    public ChatResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<List<Emoticon>> getEmoticons() {
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

    public TwitchResponse<ChannelBadges> getChannelBadges(String channelName) {
        String url = String.format("%s/chat/%s/badges", getBaseUrl(), channelName);
        return requestGet(url, HttpResponse.HTTP_OK, ChannelBadges.class);
    }
}
