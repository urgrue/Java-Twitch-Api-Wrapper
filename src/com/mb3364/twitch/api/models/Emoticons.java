package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Emoticons {

    private List<Emoticon> emoticons = new ArrayList<>();

    @Override
    public String toString() {
        return "Emoticons{" +
                "emoticons=" + emoticons +
                '}';
    }

    public List<Emoticon> getEmoticons() {
        return emoticons;
    }

    public void setEmoticons(List<Emoticon> emoticons) {
        this.emoticons = emoticons;
    }
}
