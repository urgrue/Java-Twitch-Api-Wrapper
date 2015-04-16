package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamPreview {

    private String small;
    private String medium;
    private String large;
    private String template;

    @Override
    public String toString() {
        return "StreamPreview{" +
                "small='" + small + '\'' +
                ", medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                ", template='" + template + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StreamPreview that = (StreamPreview) o;

        if (small != null ? !small.equals(that.small) : that.small != null) return false;
        if (medium != null ? !medium.equals(that.medium) : that.medium != null) return false;
        if (large != null ? !large.equals(that.large) : that.large != null) return false;
        return !(template != null ? !template.equals(that.template) : that.template != null);

    }

    @Override
    public int hashCode() {
        int result = small != null ? small.hashCode() : 0;
        result = 31 * result + (medium != null ? medium.hashCode() : 0);
        result = 31 * result + (large != null ? large.hashCode() : 0);
        result = 31 * result + (template != null ? template.hashCode() : 0);
        return result;
    }

    public String getSmall() {

        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
