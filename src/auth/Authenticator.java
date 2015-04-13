package auth;

import auth.grants.implicit.AuthenticationCallbackServer;
import auth.grants.implicit.AuthenticationError;

import java.io.IOException;
import java.net.URI;

public class Authenticator {

    private String twitchBaseUrl; // Base twitch api url
    private int listenPort; // The port to listen for the authentication callback on
    private String clientId;
    private URI redirectUri;

    private String accessToken;
    private AuthenticationError authenticationError;

    public Authenticator(String twitchBaseUrl, String clientId, URI redirectUri) {
        this.twitchBaseUrl = twitchBaseUrl;
        this.clientId = clientId;
        this.redirectUri = redirectUri;

        // Set the listening port for the callback, default to 80 if not specified
        this.listenPort = redirectUri.getPort();
        if (this.listenPort == -1) {
            this.listenPort = 80; // HTTP default
        }
    }

    public String getAuthenticationUrl(Scopes... scopes) {
        return String.format("%s/oauth2/authorize" +
                "?response_type=token" +
                "&client_id=%s" +
                "&redirect_uri=%s" +
                "&scope=%s",
                twitchBaseUrl, clientId, redirectUri, Scopes.join(scopes));
    }

    // TODO return an AuthenticationStatus object rather than using the auth error members and boolean return
    /**
     * Listens for callback from twitch server with the access token.
     * @return <code>true</code> if access token was received, <code>false</code> otherwise
     */
    public boolean awaitAccessToken() {
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
     * Get the authentication error if it failed.
     * @return <code>AuthenticationError</code> if authentication failed. <code>null</code> otherwise
     */
    public AuthenticationError getAuthenticationError() {
        return authenticationError;
    }

    /**
     * Check if there was an authentication error
     * @return <code>true</code> if an error exists, <code>false</code> otherwise
     */
    public boolean hasAuthenticationError() {
        return authenticationError != null;
    }

}
