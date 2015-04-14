package api.blocks;

import api.EmptyResponse;
import api.TwitchResource;
import api.TwitchResponse;
import api.blocks.models.Block;
import api.blocks.models.Blocks;
import http.HttpResponse;

import java.util.List;

public class BlocksResource extends TwitchResource {

    public BlocksResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<List<Block>> get(String user, int limit, int offset) {
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/users/%s/blocks?limit=%s&offset=%s",
                getBaseUrl(), user, limit, offset);

        TwitchResponse<Blocks> container = requestGet(url, HttpResponse.HTTP_OK, Blocks.class);

        // Create object with list rather than the container class
        TwitchResponse<List<Block>> response = new TwitchResponse<List<Block>>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getBlocks());
        }

        return response;
    }

    public TwitchResponse<List<Block>> get(String user) {
        return get(user, 25, 0);
    }

    public TwitchResponse<Block> add(String user, String target) {
        String url = String.format("%s/users/%s/blocks/%s",
                getBaseUrl(), user, target);

        return requestGet(url, HttpResponse.HTTP_OK, Block.class);
    }

    public TwitchResponse<EmptyResponse> remove(String user, String target) {
        String url = String.format("%s/users/%s/blocks/%s",
                getBaseUrl(), user, target);

        return requestPut(url, HttpResponse.HTTP_NO_CONTENT, EmptyResponse.class);
    }
}
