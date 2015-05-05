package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFollows {

    @JsonProperty("_total")
    private int total;
    private List<UserFollow> follows;

    @Override
    public String toString() {
        return "UserFollows{" +
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

    public List<UserFollow> getFollows() {
        return follows;
    }

    public void setFollows(List<UserFollow> follows) {
        this.follows = follows;
    }
}
