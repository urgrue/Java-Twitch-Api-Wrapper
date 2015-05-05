package com.mb3364.twitch.api.resources;

import com.mb3364.http.HttpResponse;
import com.mb3364.twitch.api.handlers.BadgesResponseHandler;
import com.mb3364.twitch.api.handlers.EmoticonsResponseHandler;
import com.mb3364.twitch.api.models.ChannelBadges;
import com.mb3364.twitch.api.models.Emoticons;

import java.io.IOException;

/**
 * The {@link ChatResource} provides the functionality
 * to access the <code>/chat</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class ChatResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public ChatResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a list of all emoticon objects.
     *
     * @param handler the Response Handler
     */
    public void getEmoticons(final EmoticonsResponseHandler handler) {
        String url = String.format("%s/chat/emoticons", getBaseUrl());

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(HttpResponse response) {
                try {
                    Emoticons value = objectMapper.readValue(response.getContent(), Emoticons.class);
                    handler.onSuccess(value.getEmoticons());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of chat badges that can be used in the specified channel's chat.
     *
     * @param channel the name of the channel
     * @param handler the Response Handler
     */
    public void getBadges(final String channel, final BadgesResponseHandler handler) {
        String url = String.format("%s/chat/%s/badges", getBaseUrl(), channel);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(HttpResponse response) {
                try {
                    ChannelBadges value = objectMapper.readValue(response.getContent(), ChannelBadges.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }
}
