package api.search.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult<T> {

    @JsonProperty("_total") private int total;
    private List<T> results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    @JsonSetter("channels")
    public void setChannels(List<T> results) {
        this.results = results;
    }

    @JsonSetter("streams")
    public void setStreams(List<T> results) {
        this.results = results;
    }

    @JsonSetter("games")
    public void setGames(List<T> results) {
        this.results = results;
    }
}
