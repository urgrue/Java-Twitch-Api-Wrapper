package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoResolutions {

    private String medium;
    private String mobile;
    private String high;
    private String low;
    private String chunked;

    @Override
    public String toString() {
        return "VideoResolutions{" +
                "medium='" + medium + '\'' +
                ", mobile='" + mobile + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", chunked='" + chunked + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoResolutions that = (VideoResolutions) o;

        if (medium != null ? !medium.equals(that.medium) : that.medium != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (high != null ? !high.equals(that.high) : that.high != null) return false;
        if (low != null ? !low.equals(that.low) : that.low != null) return false;
        return !(chunked != null ? !chunked.equals(that.chunked) : that.chunked != null);

    }

    @Override
    public int hashCode() {
        int result = medium != null ? medium.hashCode() : 0;
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (high != null ? high.hashCode() : 0);
        result = 31 * result + (low != null ? low.hashCode() : 0);
        result = 31 * result + (chunked != null ? chunked.hashCode() : 0);
        return result;
    }

    public String getMedium() {

        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getChunked() {
        return chunked;
    }

    public void setChunked(String chunked) {
        this.chunked = chunked;
    }
}
