package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Games {

    @JsonProperty("_total")
    private int total;
    private List<TopGame> top;

    @Override
    public String toString() {
        return "Games{" +
                "total=" + total +
                ", top=" + top +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TopGame> getTop() {
        return top;
    }

    public void setTop(List<TopGame> top) {
        this.top = top;
    }
}
