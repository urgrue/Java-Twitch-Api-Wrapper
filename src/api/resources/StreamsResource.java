package api.resources;

import api.models.*;
import api.TwitchResponse;
import http.HttpResponse;

import java.util.List;

public class StreamsResource extends TwitchResource {

    public StreamsResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<Stream> get(String channelName) {
        String url = String.format("%s/streams/%s", getBaseUrl(), channelName);

        TwitchResponse<StreamContainer> c = requestGet(url, HttpResponse.HTTP_OK, StreamContainer.class);

        // Create object rather than the container class
        TwitchResponse<Stream> r = new TwitchResponse<Stream>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            r.setObject(c.getObject().getStream());
        }

        // Put an empty stream object if the stream is not online
        if (r.getObject() == null && r.getErrorMessage() == null) {
            r.setObject(new Stream());
        }

        return r;
    }

    public TwitchResponse<Streams> get(String game, List<String> channels, int limit, int offset, String clientId) {
        // Concat channels into comma-separated list
        String cs = "";
        if (channels != null) {
            StringBuilder sb = new StringBuilder();
            for (String s : channels) {
                sb.append(s).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            cs = sb.toString();
        }

        // Convert all null values to empty strings
        if (game == null) {
            game = "";
        }

        String ls = Integer.toString(limit);
        if (limit <= 0) {
            ls = "";
        }

        if (clientId == null) {
            clientId = "";
        }

        String url = String.format("%s/streams?game=%s&channel=%s&limit=%s&offset=%s&client_id=%s",
                getBaseUrl(), game, cs, ls, offset, clientId);

        return requestGet(url, HttpResponse.HTTP_OK, Streams.class);
    }

    public TwitchResponse<List<FeaturedStream>> getFeatured(int limit, int offset) {
        String url = String.format("%s/streams/featured", getBaseUrl());

        TwitchResponse<FeaturedStreamContainer> c = requestGet(url, HttpResponse.HTTP_OK, FeaturedStreamContainer.class);

        // Create object rather than the container class
        TwitchResponse<List<FeaturedStream>> r = new TwitchResponse<List<FeaturedStream>>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            r.setObject(c.getObject().getFeatured());
        }

        return r;
    }

    public TwitchResponse<StreamsSummary> getSummary(String game) {
        String url = String.format("%s/streams/summary?game=%s", getBaseUrl(), game);
        return requestGet(url, HttpResponse.HTTP_OK, StreamsSummary.class);
    }

    public TwitchResponse<StreamsSummary> getSummary() {
        String url = String.format("%s/streams/summary", getBaseUrl());
        return requestGet(url, HttpResponse.HTTP_OK, StreamsSummary.class);
    }

    public TwitchResponse<Streams> getFollowed(int limit, int offset) {
        String url = String.format("%s/streams/followed?limit=%s&offset=%s",
                getBaseUrl(), limit, offset);

        return requestGet(url, HttpResponse.HTTP_OK, Streams.class);
    }

    public TwitchResponse<Streams> getFollowed(int limit) {
        return getFollowed(limit, 0);
    }

    public TwitchResponse<Streams> getFollowed() {
        return getFollowed(25, 0);
    }
}
