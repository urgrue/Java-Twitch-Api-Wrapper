package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelFollows {

    @JsonProperty("_total")
    private int total;
    private List<ChannelFollow> follows;

    @Override
    public String toString() {
        return "ChannelFollows{" +
                "total=" + total +
                ", follows=" + follows +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ChannelFollow> getFollows() {
        return follows;
    }

    public void setFollows(List<ChannelFollow> follows) {
        this.follows = follows;
    }
}
