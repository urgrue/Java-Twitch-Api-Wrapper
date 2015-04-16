package api;

import api.auth.Authenticator;
import api.resources.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Enables the ability to interact with the Twitch.tv REST API.
 *
 * @author Matthew Bell
 */
public class Twitch {

    public static final String BASE_URL = "https://api.twitch.tv/kraken";
    public static final String API_VERSION = "v3";
    private String clientId; // User's app client Id
    private Authenticator authenticator;
    Map<String, AbstractResource> resources;

    /**
     * Constructs a Twitch application instance.
     */
    public Twitch() {
        authenticator = new Authenticator(BASE_URL);
        // Instantiate resource connectors
        resources = new HashMap<String, AbstractResource>();
        resources.put("channels", new ChannelsResource(BASE_URL, API_VERSION));
        resources.put("chat", new ChatResource(BASE_URL, API_VERSION));
        resources.put("games", new GamesResource(BASE_URL, API_VERSION));
        resources.put("ingests", new IngestsResource(BASE_URL, API_VERSION));
        resources.put("root", new RootResource(BASE_URL, API_VERSION));
        resources.put("search", new SearchResource(BASE_URL, API_VERSION));
        resources.put("streams", new StreamsResource(BASE_URL, API_VERSION));
        resources.put("teams", new TeamsResource(BASE_URL, API_VERSION));
        resources.put("users", new UsersResource(BASE_URL, API_VERSION));
        resources.put("videos", new VideosResource(BASE_URL, API_VERSION));
    }

    /**
     * Get the set Twitch client ID.
     *
     * @return The Twitch client ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Set the Twitch client ID. Register your application on Twitch.tv to retrieve
     * a client ID.
     * <p>Passed to authorization endpoints to identify your application.</p>
     *
     * @param clientId Twitch client ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
        // Update client id in all resources
        for (AbstractResource r : resources.values()) {
            r.setClientId(clientId);
        }
    }

    private AbstractResource getResource(String key) {
        AbstractResource r = resources.get(key);
        r.setAuthAccessToken(authenticator.getAccessToken());
        return r;
    }

    /**
     * Get the authenticator object. The authenticator object allows a user to
     * authenticate with the Twitch.tv servers.
     *
     * @return the authenticator object
     * @see Authenticator
     */
    public Authenticator auth() {
        return authenticator;
    }

    /**
     * Get the {@link ChannelsResource} object. The {@link ChannelsResource} provides
     * the functionality to access the <code>/channels</code> endpoints of the Twitch API.
     *
     * @return the {@link ChannelsResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public ChannelsResource channels() {
        return (ChannelsResource) getResource("channels");
    }

    /**
     * Get the {@link ChatResource} object. The {@link ChatResource} provides
     * the functionality to access the <code>/chat</code> endpoints of the Twitch API.
     *
     * @return the {@link ChatResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public ChatResource chat() {
        return (ChatResource) getResource("chat");
    }

    /**
     * Get the {@link GamesResource} object. The {@link GamesResource} provides
     * the functionality to access the <code>/games</code> endpoints of the Twitch API.
     *
     * @return the {@link GamesResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public GamesResource games() {
        return (GamesResource) getResource("games");
    }

    /**
     * Get the {@link IngestsResource} object. The {@link IngestsResource} provides
     * the functionality to access the <code>/ingests</code> endpoints of the Twitch API.
     *
     * @return the {@link IngestsResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public IngestsResource ingests() {
        return (IngestsResource) getResource("ingests");
    }

    /**
     * Get the {@link RootResource} object. The {@link RootResource} provides
     * the functionality to access the root <code>/</code> endpoints of the Twitch API.
     *
     * @return the {@link RootResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public RootResource root() {
        return (RootResource) getResource("root");
    }

    /**
     * Get the {@link SearchResource} object. The {@link SearchResource} provides
     * the functionality to access the <code>/search</code> endpoints of the Twitch API.
     *
     * @return the {@link SearchResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public SearchResource search() {
        return (SearchResource) getResource("search");
    }

    /**
     * Get the {@link StreamsResource} object. The {@link StreamsResource} provides
     * the functionality to access the <code>/streams</code> endpoints of the Twitch API.
     *
     * @return the {@link StreamsResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public StreamsResource streams() {
        return (StreamsResource) getResource("streams");
    }

    /**
     * Get the {@link TeamsResource} object. The {@link TeamsResource} provides
     * the functionality to access the <code>/teams</code> endpoints of the Twitch API.
     *
     * @return the {@link TeamsResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public TeamsResource teams() {
        return (TeamsResource) getResource("teams");
    }

    /**
     * Get the {@link UsersResource} object. The {@link UsersResource} provides
     * the functionality to access the <code>/users</code> endpoints of the Twitch API.
     *
     * @return the {@link UsersResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public UsersResource users() {
        return (UsersResource) getResource("users");
    }

    /**
     * Get the {@link VideosResource} object. The {@link VideosResource} provides
     * the functionality to access the <code>/videos</code> endpoints of the Twitch API.
     *
     * @return the {@link VideosResource} object
     * @see ChannelsResource
     * @see ChatResource
     * @see GamesResource
     * @see IngestsResource
     * @see RootResource
     * @see SearchResource
     * @see StreamsResource
     * @see TeamsResource
     * @see UsersResource
     * @see VideosResource
     */
    public VideosResource videos() {
        return (VideosResource) getResource("videos");
    }
}
