package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.Emoticon;
import main.java.com.mb3364.twitch.api.models.Emoticon;

import java.io.IOException;
import java.util.List;

public interface EmoticonsResponseHandler {
    void onSuccess(List<Emoticon> emoticons);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
