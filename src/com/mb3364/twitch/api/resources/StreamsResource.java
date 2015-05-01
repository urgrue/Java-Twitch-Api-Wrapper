package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.handlers.FeaturedStreamResponseHandler;
import com.mb3364.twitch.api.handlers.StreamResponseHandler;
import com.mb3364.twitch.api.handlers.StreamsResponseHandler;
import com.mb3364.twitch.api.handlers.StreamsSummaryResponseHandler;
import com.mb3364.twitch.api.models.FeaturedStreamContainer;
import com.mb3364.twitch.api.models.StreamContainer;
import com.mb3364.twitch.api.models.Streams;
import com.mb3364.twitch.api.models.StreamsSummary;
import com.mb3364.twitch.http.HttpClient;
import com.mb3364.twitch.http.HttpResponse;
import com.mb3364.twitch.http.JsonParams;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * The {@link StreamsResource} provides the functionality
 * to access the <code>/streams</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class StreamsResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public StreamsResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a stream object.
     * <p/>
     * <p>The stream object in the onSuccess() response will be <code>null</code> if the stream is offline.</p>
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void get(String channelName, StreamResponseHandler handler) {
        String url = String.format("%s/streams/%s", getBaseUrl(), channelName);

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                StreamContainer value = objectMapper.readValue(response.getContent(), StreamContainer.class);
                handler.onSuccess(value.getStream());
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a list of stream objects that are queried by a number of parameters
     * sorted by number of viewers descending.
     *
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>game</code>:  Streams categorized under <code>game</code>.</li>
     *                <li><code>channel</code>:  Streams from a comma separated list of channels.</li>
     *                <li><code>limit</code>:  Maximum number of objects in array. Default is 25. Maximum is 100.</li>
     *                <li><code>offset</code>: Object offset for pagination. Default is 0.</li>
     *                <li><code>client_id</code>: Only shows streams from applications of <code>client_id</code>.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void get(JsonParams params, StreamsResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/streams?%s", getBaseUrl(), params.toQueryString());
        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Streams value = objectMapper.readValue(response.getContent(), Streams.class);
                handler.onSuccess(value.getTotal(), value.getStreams());
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a list of stream objects that are queried by a number of parameters
     * sorted by number of viewers descending.
     *
     * @param handler the response handler
     */
    public void get(StreamsResponseHandler handler) {
        get(null, handler);
    }

    /**
     * Returns a list of featured (promoted) stream objects.
     *
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>limit</code>:  Maximum number of objects in array. Default is 25. Maximum is 100.</li>
     *                <li><code>offset</code>: Object offset for pagination. Default is 0.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void getFeatured(JsonParams params, FeaturedStreamResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/streams/featured?%s", getBaseUrl(), params.toQueryString());
        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                FeaturedStreamContainer value = objectMapper.readValue(response.getContent(), FeaturedStreamContainer.class);
                handler.onSuccess(value.getFeatured());
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a list of featured (promoted) stream objects.
     *
     * @param handler the response handler
     */
    public void getFeatured(FeaturedStreamResponseHandler handler) {
        getFeatured(null, handler);
    }

    /**
     * Returns a summary of current streams.
     *
     * @param game    Only show stats for the set game
     * @param handler the response handler
     */
    public void getSummary(String game, StreamsSummaryResponseHandler handler) {
        // Encode game in case their is a space, etc.
        try {
            game = URLEncoder.encode(game, "UTF-8");
        } catch (IOException e) {
            game = "";
        }

        String url = String.format("%s/streams/summary", getBaseUrl());
        if (game.length() > 0) url += "?game=" + game;

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                StreamsSummary value = objectMapper.readValue(response.getContent(), StreamsSummary.class);
                handler.onSuccess(value);
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a summary of current streams.
     *
     * @param handler the response handler
     */
    public void getSummary(StreamsSummaryResponseHandler handler) {
        getSummary("", handler);
    }

    /**
     * Returns a list of stream objects that the authenticated user is following.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_READ}
     *
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>limit</code>:  Maximum number of objects in array. Default is 25. Maximum is 100.</li>
     *                <li><code>offset</code>: Object offset for pagination. Default is 0.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void getFollowed(JsonParams params, StreamsResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/streams/followed?%s", getBaseUrl(), params.toQueryString());
        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Streams value = objectMapper.readValue(response.getContent(), Streams.class);
                handler.onSuccess(value.getTotal(), value.getStreams());
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a list of stream objects that the authenticated user is following.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_READ}
     *
     * @param handler the response handler
     */
    public void getFollowed(StreamsResponseHandler handler) {
        getFollowed(handler);
    }
}
