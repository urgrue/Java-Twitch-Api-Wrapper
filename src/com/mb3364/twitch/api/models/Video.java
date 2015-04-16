package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    @JsonProperty("_id")
    private String id;
    private String title;
    private String description;
    private long broadcastId;
    private String status;
    private String tagList; // Possibly used for exporting to YouTube. No real use.
    private Date recordedAt;
    private String game;
    private int length;
    private String preview;
    private String url;
    private int views;
    private VideoFramerates fps;
    private VideoResolutions resolutions;
    private String broadcastType;
    private ChannelSummary channel;

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", broadcastId=" + broadcastId +
                ", status='" + status + '\'' +
                ", tagList='" + tagList + '\'' +
                ", recordedAt=" + recordedAt +
                ", game='" + game + '\'' +
                ", length=" + length +
                ", preview='" + preview + '\'' +
                ", url='" + url + '\'' +
                ", views=" + views +
                ", fps=" + fps +
                ", resolutions=" + resolutions +
                ", broadcastType='" + broadcastType + '\'' +
                ", channel=" + channel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        return !(id != null ? !id.equals(video.id) : video.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(long broadcastId) {
        this.broadcastId = broadcastId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public Date getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(Date recordedAt) {
        this.recordedAt = recordedAt;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public VideoFramerates getFps() {
        return fps;
    }

    public void setFps(VideoFramerates fps) {
        this.fps = fps;
    }

    public VideoResolutions getResolutions() {
        return resolutions;
    }

    public void setResolutions(VideoResolutions resolutions) {
        this.resolutions = resolutions;
    }

    public String getBroadcastType() {
        return broadcastType;
    }

    public void setBroadcastType(String broadcastType) {
        this.broadcastType = broadcastType;
    }

    public String getChannelName() {
        return channel.getName();
    }

    public String getChannelDisplayName() {
        return channel.getDisplayName();
    }

    public void setChannel(ChannelSummary channel) {
        this.channel = channel;
    }

    public class ChannelSummary {
        private String name;
        private String displayName;

        @Override
        public String toString() {
            return "ChannelSummary{" +
                    "name='" + name + '\'' +
                    ", displayName='" + displayName + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }

}
