package api.channels;

import api.TwitchResource;
import http.HttpClient;
import http.HttpResponse;

import java.io.IOException;

public class ChannelsResource extends TwitchResource {

    public ChannelsResource() {
        super();
    }

    public Channel getChannel(String name) {
        HttpClient request = new HttpClient();
        Channel channel = new Channel();

        try {
            HttpResponse response = request.get(BASE_URL + "/channels/" + name, getHeaders());
            String content = response.getContent();

            if (content != null) {
                channel = parseResponse(content, Channel.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return channel;
    }
}
