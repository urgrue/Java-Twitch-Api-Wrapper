package api.channels;

import api.ErrorResponse;
import api.TwitchResource;
import api.TwitchResponse;
import api.channels.models.Channel;
import api.channels.models.Editors;
import api.users.models.User;
import http.HttpResponse;

import java.util.List;

public class ChannelsResource extends TwitchResource {

    public ChannelsResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<Channel> get(String name) {
        String url = String.format("%s/channels/%s", getBaseUrl(), name);
        return requestGet(url, HttpResponse.HTTP_OK, Channel.class);
    }

    public TwitchResponse<Channel> get() {
        String url = String.format("%s/channel", getBaseUrl());
        return requestGet(url, HttpResponse.HTTP_OK, Channel.class);
    }

    public TwitchResponse<List<User>> getEditors(String name) {
        String url = String.format("%s/channels/%s/editors", getBaseUrl(), name);
        TwitchResponse<Editors> container = requestGet(url, HttpResponse.HTTP_OK, Editors.class);

        // Create object with list rather than the container class
        TwitchResponse<List<User>> response = new TwitchResponse<List<User>>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getUsers());
        }

        return response;
    }
}
