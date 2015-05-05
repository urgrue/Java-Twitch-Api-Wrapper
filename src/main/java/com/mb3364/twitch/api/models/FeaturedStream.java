package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeaturedStream {

    private String text;
    private String image;
    private boolean sponsored;
    private int priority;
    private boolean scheduled;
    private Stream stream;

    @Override
    public String toString() {
        return "FeaturedStream{" +
                "text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", sponsored=" + sponsored +
                ", priority=" + priority +
                ", scheduled=" + scheduled +
                ", stream=" + stream +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeaturedStream that = (FeaturedStream) o;

        return !(stream != null ? !stream.equals(that.stream) : that.stream != null);

    }

    @Override
    public int hashCode() {
        return stream != null ? stream.hashCode() : 0;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSponsored() {
        return sponsored;
    }

    public void setSponsored(boolean sponsored) {
        this.sponsored = sponsored;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }
}
