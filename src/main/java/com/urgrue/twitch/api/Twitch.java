package com.urgrue.twitch.api;

import com.urgrue.twitch.api.auth.Authenticator;
import com.urgrue.twitch.api.resources.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Enables the ability to interact with the Twitch.tv REST API.
 *
 * @author Matthew Bell
 */
public class Twitch {

    public static final String DEFAULT_BASE_URL = "https://api.twitch.tv/kraken";
    public static final int DEFAULT_API_VERSION = 3;
    private String clientId; // User's app client Id
    private Authenticator authenticator;
    private Map<ResourceTypes, AbstractResource> resources;

    private enum ResourceTypes {
        Channels,
        Chat,
        Games,
        Ingests,
        Root,
        Search,
        Streams,
        Teams,
        Users,
        Videos
    }

    /**
     * Constructs a Twitch application instance with a set API base URL and API version number.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the API version number to request
     */
    public Twitch(String baseUrl, int apiVersion) {
        authenticator = new Authenticator(DEFAULT_BASE_URL);
        // Instantiate resource connectors
        resources = new HashMap<ResourceTypes, AbstractResource>();
        resources.put(ResourceTypes.Channels, new ChannelsResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
        resources.put(ResourceTypes.Chat, new ChatResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
        resources.put(ResourceTypes.Games, new GamesResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
        resources.put(ResourceTypes.Ingests, new IngestsResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
        resources.put(ResourceTypes.Root, new RootResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
        resources.put(ResourceTypes.Search, new SearchResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
        resources.put(ResourceTypes.Streams, new StreamsResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
        resources.put(ResourceTypes.Teams, new TeamsResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
        resources.put(ResourceTypes.Users, new UsersResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
        resources.put(ResourceTypes.Videos, new VideosResource(DEFAULT_BASE_URL, DEFAULT_API_VERSION));
    }

    /**
     * Constructs a Twitch application instance.
     */
    public Twitch() {
        this(DEFAULT_BASE_URL, DEFAULT_API_VERSION);
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

    private AbstractResource getResource(ResourceTypes key) {
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
        return (ChannelsResource) getResource(ResourceTypes.Channels);
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
        return (ChatResource) getResource(ResourceTypes.Chat);
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
        return (GamesResource) getResource(ResourceTypes.Games);
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
        return (IngestsResource) getResource(ResourceTypes.Ingests);
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
        return (RootResource) getResource(ResourceTypes.Root);
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
        return (SearchResource) getResource(ResourceTypes.Search);
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
        return (StreamsResource) getResource(ResourceTypes.Streams);
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
        return (TeamsResource) getResource(ResourceTypes.Teams);
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
        return (UsersResource) getResource(ResourceTypes.Users);
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
        return (VideosResource) getResource(ResourceTypes.Videos);
    }
}
