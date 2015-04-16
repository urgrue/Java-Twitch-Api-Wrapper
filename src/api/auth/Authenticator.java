package api.auth;

import api.auth.grants.implicit.AuthenticationCallbackServer;
import api.auth.grants.implicit.AuthenticationError;

import java.io.IOException;
import java.net.URI;

/**
 * The authenticator object allows a user to authenticate with the Twitch.tv servers.
 *
 * @author Matthew Bell
 */
public class Authenticator {

    private String twitchBaseUrl; // Base twitch api url
    private int listenPort; // The port to listen for the authentication callback on
    private String clientId;
    private URI redirectUri;

    private String accessToken;
    private AuthenticationError authenticationError;

    public Authenticator(String twitchBaseUrl) {
        this.twitchBaseUrl = twitchBaseUrl;
    }

    /**
     * Returns the authentication URL that you can redirect the user to in order
     * to authorize your application to access their account and retrieve an
     * access token.
     *
     * @param clientId    the Twitch application client ID
     * @param redirectURI the redirect URI for your Twitch application
     * @param scopes      the scopes needed for your application
     * @return
     */
    public String getAuthenticationUrl(String clientId, URI redirectURI, Scopes... scopes) {
        this.clientId = clientId;
        this.redirectUri = redirectURI;

        // Set the listening port for the callback, default to 80 if not specified
        this.listenPort = redirectUri.getPort();
        if (this.listenPort == -1) {
            this.listenPort = 80; // HTTP default
        }

        return String.format("%s/oauth2/authorize?response_type=token" +
                        "&client_id=%s&redirect_uri=%s&scope=%s",
                twitchBaseUrl, clientId, redirectUri, Scopes.join(scopes));
    }

    /**
     * Listens for callback from twitch server with the access token.
     * <code>getAuthenticationUrl()</code> must be called prior to this function!
     *
     * @return <code>true</code> if access token was received, <code>false</code> otherwise
     */
    public boolean awaitAccessToken() {
        if (clientId == null || redirectUri == null) return false;

        AuthenticationCallbackServer server = new AuthenticationCallbackServer(listenPort);
        try {
            server.start();
        } catch (IOException e) {
            authenticationError = new AuthenticationError("JavaException", e.toString());
            return false;
        }

        if (server.hasAuthenticationError() || server.getAccessToken() == null) {
            authenticationError = server.getAuthenticationError();
            return false;
        }

        accessToken = server.getAccessToken();
        return true;
    }

    public String getClientId() {
        return clientId;
    }

    public URI getRedirectUri() {
        return redirectUri;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Check if an access token has been received.
     *
     * @return <code>true</code> if an access token has been received; <code>false</code> otherwise
     */
    public boolean hasAccessToken() {
        return accessToken != null;
    }

    /**
     * Get the authentication error if it failed.
     *
     * @return <code>AuthenticationError</code> if authentication failed. <code>null</code> otherwise
     */
    public AuthenticationError getAuthenticationError() {
        return authenticationError;
    }

    /**
     * Check if there was an authentication error.
     *
     * @return <code>true</code> if an error exists; <code>false</code> otherwise
     */
    public boolean hasAuthenticationError() {
        return authenticationError != null;
    }

}
