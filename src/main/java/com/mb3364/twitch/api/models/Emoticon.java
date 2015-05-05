package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Emoticon {

    private String regex;
    private List<EmoticonImage> images;

    @Override
    public String toString() {
        return "Emoticon{" +
                "regex='" + regex + '\'' +
                ", images=" + images +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Emoticon emoticon = (Emoticon) o;

        if (regex != null ? !regex.equals(emoticon.regex) : emoticon.regex != null) return false;
        return !(images != null ? !images.equals(emoticon.images) : emoticon.images != null);

    }

    @Override
    public int hashCode() {
        int result = regex != null ? regex.hashCode() : 0;
        result = 31 * result + (images != null ? images.hashCode() : 0);
        return result;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public List<EmoticonImage> getImages() {
        return images;
    }

    public void setImages(List<EmoticonImage> images) {
        this.images = images;
    }
}
