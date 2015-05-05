# Async Twitch API Wrapper
JTAW is a complete asynchronous java wrapper for interaction with v3 of the [Twitch API](https://github.com/justintv/Twitch-API).

Please feel free to report any issues or contribute code.

## Basics

Using the wrapper is as simple as instantiating the `Twitch` object and then calling the appropriate endpoint functions.

For example, a `GET /streams/featured` request would map to the `twitch.streams().getFeatured()` function; and `GET /channels/lirik` would map to `twitch.channels().get("lirik")`.

Responses are handled via callbacks passed via a handler with each function call. This process is outlined in the following examples.

#### Basic Example

```java
Twitch twitch = new Twitch();
twitch.setClientId("shjdkashjkfdl"); // This is your registered application's client ID

twitch.channels().get("lirik", new ChannelResponseHandler() {
    @Override
    public void onSuccess(Channel channel) {
        /* Successful response from the Twitch API */
        System.out.println(channel);
    }

    @Override
    public void onFailure(int statusCode, String statusMessage, String errorMessage) {
        /* Twitch API responded with an error message */
    }

    @Override
    public void onFailure(Throwable e) {
        /* Unable to access Twitch, or error parsing the response */
    }
});
```

#### Basic Example with Parameters

Some endpoints accept optional parameters as specified in the [Twitch API](https://github.com/justintv/Twitch-API). These parameters can be passed with a `RequestParams` object and passed to the request method.

```java
/* Update my stream */
RequestParams params = new RequestParams();
params.put("status", "Let's kill some zombies!");
params.put("game", "DayZ");

client.channels().put("my-user-name", params, new ChannelResponseHandler() {
    @Override
    public void onSuccess(Channel channel) {
        /* Success, we got the updated Channel object */
    }

    @Override
    public void onFailure(int statusCode, String statusMessage, String errorMessage) {
        /* Twitch denied the request */
    }

    @Override
    public void onFailure(Throwable e) {
        /* Unable to access Twitch, or error parsing the response */
    }
});
```

## Authentication

### Implicit Grant Flow

The wrapper provides the functionality for authenticating users of your application following the [Implicit Grant Flow](https://github.com/justintv/Twitch-API/blob/master/authentication.md#implicit-grant-flow). 

To use authentication, your application must be registered with twitch.tv and the callback URI should be set like the following:
`http://127.0.0.1:23522/authorize.html`. You may choose a different port other than `23522` if you wish, but the rest of the URI must remain exactly as specified above.

The authentication process is explained in the following code example.

```java
Twitch twitch = new Twitch();
twitch.setClientId("shjdkashjkfdl"); // This is your registered application's client ID

/* Specify your registered callback URI */
URI callbackUri = new URI("http://127.0.0.1:23522/authorize.html");

/* Get the authentication URL. Note: you will set the required scopes needed here. */
String authUrl = twitch.auth().getAuthenticationUrl(twitch.getClientID(), callbackUri, Scopes.USER_READ, Scopes.CHANNEL_READ);

/* Send the user to the webpage somehow so that they can authorize your application */
openWebpage(authUrl);

/* Waits for the user to authorize or deny your application. Note: this function will block until a response is received! */
boolean authSuccess = twitch.auth().awaitAccessToken();

/* Check if authentication was successful */
if (authSuccess) {
  /* The access token is automatically set in the Twitch object and will be sent with all further API requests! */
  String accessToken = twitch.auth().getAccessToken(); // if we want to explicitly get it for some reason
  System.out.println("Access Token: " + accessToken);
} else {
  /* Authentication failed, most likely because the user denied the authorization request */
  System.out.println(twitch.auth().getAuthenticationError());
}
```

### Explicitly Setting Access Token

If you are just creating an application for yourself and already have an access token for your account, you can explicitly set it. This _**should not**_ be done if the application is being distributed as the access token is directly linked to your Twitch account.

```java
twitch.auth().setAccessToken("my-access-token-289489");
```

## Dependencies

* [Java Async HTTP Client](https://github.com/mb3364/java-async-http)
* [Jackson JSON Processor](http://wiki.fasterxml.com/JacksonHome) ver. 2.4.4

## Roadmap

* Allow custom authorization callback views.
* Android and Gradle support.

## Changelog

#### v. 0.10

* Full asynchronous support.

#### v. 0.02

* Twitch(baseUrl, apiVersion) constructor added for easier testing and future proofing by allowing user to override defaults.

#### v. 0.01

* Support for JDK 7 and non-diamond types.
