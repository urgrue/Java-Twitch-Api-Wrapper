# A Java Twitch API Wrapper
A complete java wrapper for interaction with v3 of the [Twitch API](https://github.com/justintv/Twitch-API).

This project is still being improved and major changes may occur breaking backwards compatibility. Feel free to submit issues or contribute code.

## Basics

Using the wrapper is as simple as instantiating the `Twitch` object and then calling the appropriate endpoint functions.

For example, a `GET /streams/featured` request would map to the `twitch.streams().getFeatured()` function; and `GET /channels/lirik` would map to `twitch.channels().get("lirik")`.

#### Return Type

Each request will return a `TwitchResponse<T>` object, where `T` is the object type of the expected response data (eg: `Channel` or `Stream`).

The `TwitchResponse` object contains the HTTP status code, HTTP status text, the response data object, and an error message if the twitch.tv API responded with an error. For example, if trying to start a commercial on the stream the error message may be _"Commercials breaks are allowed every 8 min and only when you are online."_

If the twitch.tv API responded with an error, the response data object will be `null` and an error message will be set. You can easily check for an error using the `twitchResponse.hasError()` function.

##### IOException

Each endpoint function can throw an `IOException` if there was error access the twitch.tv API or if their was an error parsing the response.

#### Example

```java
Twitch twitch = new Twitch();
twitch.setClientId("shjdkashjkfdl"); // This is your registered application's client ID

try {
    TwitchResponse<Channel> response = twitch.channels().get();
    if (!response.hasError()) {
        // Successful!
        Channel channel = response.getObject();
    } else {
        // Error; Twitch responded with an error, let's see what it is.
        System.out.println(response.getErrorMessage());
    }
} catch (IOException e) {
    /*
        An error occurred while making the request to the Twitch API.
        Display a message to the user.
     */
    System.out.println("Error retrieving data from twitch.tv");
}
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

If you are just creating an application for yourself and already have an access token for your account, you can explictly set it. This _**should not**_ be done if the application is being distributed as the access token is directly linked to your twitch.tv account.

```java
twitch.auth().setAccessToken("my-access-token-289489");
```

## Requirements

[Jackson JSON Processor](http://wiki.fasterxml.com/JacksonHome) ver. 2.4.4

## In Progress

* Complete Javadoc documentation.
* Allow custom authorization callback views.

## Changelog

#### v. 0.01

* Support for JDK 7 and non-diamond types.
