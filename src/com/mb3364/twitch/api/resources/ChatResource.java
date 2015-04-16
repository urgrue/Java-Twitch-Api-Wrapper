package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.ChannelBadges;
import com.mb3364.twitch.api.models.Emoticon;
import com.mb3364.twitch.api.models.Emoticons;
import com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;
import java.util.List;

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
    public ChatResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a list of all emoticon objects for Twitch.
     *
     * @return a TwitchResponse containing a list of {@link Emoticon}'s
     * @throws IOException if an error occurs during the request
     */
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

    /**
     * Returns a list of chat badges that can be used in the specified channel's chat.
     *
     * @param channelName the name of the channel
     * @return a TwitchResponse containing a {@link ChannelBadges} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<ChannelBadges> getChannelBadges(String channelName) throws IOException {
        String url = String.format("%s/chat/%s/badges", getBaseUrl(), channelName);
        return requestGet(url, HttpResponse.HTTP_OK, ChannelBadges.class);
    }
}
