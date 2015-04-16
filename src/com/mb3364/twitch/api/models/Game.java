package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

    @JsonProperty("_id")
    private long id;
    private String name;
    private GameBox box;
    private GameLogo logo;
    private long giantbombId;
    private int popularity; // From search results

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", box=" + box +
                ", logo=" + logo +
                ", giantbombId=" + giantbombId +
                ", popularity=" + popularity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return id == game.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameBox getBox() {
        return box;
    }

    public void setBox(GameBox box) {
        this.box = box;
    }

    public GameLogo getLogo() {
        return logo;
    }

    public void setLogo(GameLogo logo) {
        this.logo = logo;
    }

    public long getGiantbombId() {
        return giantbombId;
    }

    public void setGiantbombId(long giantbombId) {
        this.giantbombId = giantbombId;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
