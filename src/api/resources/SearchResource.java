package api.resources;

import api.TwitchResponse;
import api.models.Channel;
import api.models.SearchResult;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SearchResource extends TwitchResource {

    public SearchResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<SearchResult<Channel>> channels(String query, int limit, int offset) {
        String url = String.format("%s/search/channels?q=%s&limit=%s&offset=%s",
                getBaseUrl(), query, limit, offset);

        throw new NotImplementedException();

        //return requestGet(url, HttpResponse.HTTP_OK, SearchResult.class);
    }
}
