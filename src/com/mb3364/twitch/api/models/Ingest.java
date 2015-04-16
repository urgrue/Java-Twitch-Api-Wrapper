package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingest {

    @JsonProperty("_id")
    private long id;
    private String name;
    @JsonProperty("default")
    private boolean _default;
    private String urlTemplate;
    private String availability;

    @Override
    public String toString() {
        return "Ingest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", default=" + _default +
                ", urlTemplate='" + urlTemplate + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingest ingest = (Ingest) o;

        return id == ingest.id;

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

    public boolean isDefault() {
        return _default;
    }

    public void set_default(boolean _default) {
        this._default = _default;
    }

    public String getUrlTemplate() {
        return urlTemplate;
    }

    public void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
