package api.chat;

import api.TwitchResource;
import api.TwitchResponse;

import java.util.ArrayList;
import java.util.List;

public class ChatResource extends TwitchResource {

    public ChatResource() {
        super();
    }

    public List<Emoticon> getEmoticons() {
        String url = String.format("%s/chat/emoticons", BASE_URL);
        TwitchResponse response = getRequest(url);

        List<Emoticon> emoticons = new ArrayList<>();
        if (response.getCode() == TwitchResponse.HTTP_OK) {
            Emoticons emoticonsContainer = parseResponse(response.getContent(), Emoticons.class);
            emoticons = emoticonsContainer.getEmoticons();
        }

        return emoticons;
    }

    public ChannelBadges getChannelBadges(String channelName) {
        String url = String.format("%s/chat/%s/badges", BASE_URL, channelName);
        TwitchResponse response = getRequest(url);

        ChannelBadges badges = new ChannelBadges();
        if (response.getCode() == TwitchResponse.HTTP_OK) {
            badges = parseResponse(response.getContent(), ChannelBadges.class);
        }

        return badges;
    }
}
