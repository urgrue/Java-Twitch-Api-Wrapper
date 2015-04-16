package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamsSummary {

    private int channels;
    private int viewers;

    @Override
    public String toString() {
        return "StreamsSummary{" +
                "channels=" + channels +
                ", viewers=" + viewers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StreamsSummary that = (StreamsSummary) o;

        if (channels != that.channels) return false;
        return viewers == that.viewers;
    }

    @Override
    public int hashCode() {
        int result = channels;
        result = 31 * result + viewers;
        return result;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }
}
