package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmoticonImage {

    private int emoticonSet;
    private int height;
    private int width;
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmoticonImage that = (EmoticonImage) o;

        if (emoticonSet != that.emoticonSet) return false;
        if (height != that.height) return false;
        if (width != that.width) return false;
        return url.equals(that.url);

    }

    @Override
    public int hashCode() {
        int result = emoticonSet;
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EmoticonImage{" +
                "emoticonSet=" + emoticonSet +
                ", height=" + height +
                ", width=" + width +
                ", url='" + url + '\'' +
                '}';
    }

    public int getEmoticonSet() {
        return emoticonSet;
    }

    public void setEmoticonSet(int emoticonSet) {
        this.emoticonSet = emoticonSet;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
