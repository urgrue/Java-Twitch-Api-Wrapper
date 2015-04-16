package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingests {

    private List<Ingest> ingests;

    @Override
    public String toString() {
        return "Ingests{" +
                "ingests=" + ingests +
                '}';
    }

    public List<Ingest> getIngests() {
        return ingests;
    }

    public void setIngests(List<Ingest> ingests) {
        this.ingests = ingests;
    }
}
