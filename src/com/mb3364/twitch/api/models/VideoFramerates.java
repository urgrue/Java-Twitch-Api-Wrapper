package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoFramerates {

    private double audioOnly;
    private double medium;
    private double mobile;
    private double high;
    private double low;
    private double chunked;

    @Override
    public String toString() {
        return "VideoFramerates{" +
                "audioOnly=" + audioOnly +
                ", medium=" + medium +
                ", mobile=" + mobile +
                ", high=" + high +
                ", low=" + low +
                ", chunked=" + chunked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoFramerates videoFramerates = (VideoFramerates) o;

        if (Double.compare(videoFramerates.audioOnly, audioOnly) != 0) return false;
        if (Double.compare(videoFramerates.medium, medium) != 0) return false;
        if (Double.compare(videoFramerates.mobile, mobile) != 0) return false;
        if (Double.compare(videoFramerates.high, high) != 0) return false;
        if (Double.compare(videoFramerates.low, low) != 0) return false;
        return Double.compare(videoFramerates.chunked, chunked) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(audioOnly);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(medium);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(mobile);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(high);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(low);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(chunked);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getAudioOnly() {

        return audioOnly;
    }

    public void setAudioOnly(double audioOnly) {
        this.audioOnly = audioOnly;
    }

    public double getMedium() {
        return medium;
    }

    public void setMedium(double medium) {
        this.medium = medium;
    }

    public double getMobile() {
        return mobile;
    }

    public void setMobile(double mobile) {
        this.mobile = mobile;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getChunked() {
        return chunked;
    }

    public void setChunked(double chunked) {
        this.chunked = chunked;
    }
}
