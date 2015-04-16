package api.videos;

import api.TwitchResource;
import api.TwitchResponse;
import api.videos.models.Video;
import api.videos.models.Videos;
import http.HttpResponse;

import java.util.List;

public class VideosResource extends TwitchResource {

    public VideosResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<Video> get(String id) {
        String url = String.format("%s/videos/%s", getBaseUrl(), id);
        return requestGet(url, HttpResponse.HTTP_OK, Video.class);
    }

    public TwitchResponse<List<Video>> getTop(int limit, int offset, String game, String period) {
        String url = String.format("%s/videos/top?limit=%s&offset=%s",
                getBaseUrl(), limit, offset);

        if (game != null) {
            url += "&game=" + game;
        }

        if (period != null) {
            url += "&period=" + period;
        }

        TwitchResponse<Videos> c = requestGet(url, HttpResponse.HTTP_OK, Videos.class);

        // Create object rather than the container class
        TwitchResponse<List<Video>> r = new TwitchResponse<List<Video>>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            r.setObject(c.getObject().getVideos());
        }

        return r;
    }

    public TwitchResponse<List<Video>> getTop(int limit, int offset) {
        return getTop(limit, offset, null, null);
    }

    public TwitchResponse<List<Video>> getTopByGame(String game, int limit, int offset) {
        return getTop(limit, offset, game, null);
    }

    public TwitchResponse<List<Video>> getTopByGame(String game, int limit) {
        return getTop(limit, 0, game, null);
    }

    public TwitchResponse<List<Video>> getTopByPeriod(String period, int limit, int offset) {
        return getTop(limit, offset, null, period);
    }

    public TwitchResponse<List<Video>> getTopByPeriod(String period, int limit) {
        return getTop(limit, 0, null, period);
    }

    public TwitchResponse<List<Video>> getFollowed(int limit, int offset) {
        String url = String.format("%s/videos/followed?limit=%s&offset=%s",
                getBaseUrl(), limit, offset);

        TwitchResponse<Videos> c = requestGet(url, HttpResponse.HTTP_OK, Videos.class);

        // Create object rather than the container class
        TwitchResponse<List<Video>> r = new TwitchResponse<List<Video>>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            r.setObject(c.getObject().getVideos());
        }

        return r;
    }

}
