package api.chat;

import api.ErrorResponse;
import api.TwitchResource;
import api.TwitchResponse;
import http.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class ChatResource extends TwitchResource {

    public ChatResource() {
        super();
    }

    public TwitchResponse<List<Emoticon>> getEmoticons() {
        String url = String.format("%s/chat/emoticons", BASE_URL);
        HttpResponse response = getRequest(url);

        List<Emoticon> emoticons = null;

        int statusCode = response.getCode();
        TwitchResponse<List<Emoticon>> twitchResponse = new TwitchResponse<>(response);

        if (statusCode == HttpResponse.HTTP_OK) {
            Emoticons emoticonsContainer = parseResponse(response.getContent(), Emoticons.class);
            emoticons = emoticonsContainer.getEmoticons();
        } else { // Error
            ErrorResponse error = parseResponse(response.getContent(), ErrorResponse.class);
            twitchResponse.setErrorMessage(error.getMessage());
        }

        twitchResponse.setObject(emoticons);

        return twitchResponse;
    }

    public TwitchResponse<ChannelBadges> getChannelBadges(String channelName) {
        String url = String.format("%s/chat/%s/badges", BASE_URL, channelName);
        HttpResponse response = getRequest(url);

        ChannelBadges badges = null;

        int statusCode = response.getCode();
        TwitchResponse<ChannelBadges> twitchResponse = new TwitchResponse<>(response);

        if (response.getCode() == HttpResponse.HTTP_OK) {
            badges = parseResponse(response.getContent(), ChannelBadges.class);
        } else {
            ErrorResponse error = parseResponse(response.getContent(), ErrorResponse.class);
            twitchResponse.setErrorMessage(error.getMessage());
        }

        twitchResponse.setObject(badges);

        return twitchResponse;
    }
}
