package com.mb3364.twitch.api.models;

public class TopGame {

    private Game game;
    private int viewers;
    private int channels;

    @Override
    public String toString() {
        return "TopGame{" +
                "game=" + game +
                ", viewers=" + viewers +
                ", channels=" + channels +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopGame topGame = (TopGame) o;

        if (viewers != topGame.viewers) return false;
        if (channels != topGame.channels) return false;
        return !(game != null ? !game.equals(topGame.game) : topGame.game != null);

    }

    @Override
    public int hashCode() {
        int result = game != null ? game.hashCode() : 0;
        result = 31 * result + viewers;
        result = 31 * result + channels;
        return result;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }
}
