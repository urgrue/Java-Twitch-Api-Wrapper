package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeaturedStreamContainer {

    private List<FeaturedStream> featured;

    @Override
    public String toString() {
        return "FeaturedStreamContainer{" +
                "featured=" + featured +
                '}';
    }

    public List<FeaturedStream> getFeatured() {
        return featured;
    }

    public void setFeatured(List<FeaturedStream> featured) {
        this.featured = featured;
    }
}
