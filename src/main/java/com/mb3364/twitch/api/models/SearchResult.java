package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResult<T> {

    @JsonProperty("_total")
    private int total;
    private List<T> results;

    @Override
    public String toString() {
        return "SearchResult{" +
                "total=" + total +
                ", results=" + results +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResult<?> that = (SearchResult<?>) o;

        if (total != that.total) return false;
        return !(results != null ? !results.equals(that.results) : that.results != null);

    }

    @Override
    public int hashCode() {
        int result = total;
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
