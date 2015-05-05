package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelFollow {

    private Date createdAt;
    private boolean notifications;
    private User user;

    @Override
    public String toString() {
        return "ChannelFollow{" +
                "createdAt=" + createdAt +
                ", notifications=" + notifications +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChannelFollow channelFollow = (ChannelFollow) o;

        if (notifications != channelFollow.notifications) return false;
        if (createdAt != null ? !createdAt.equals(channelFollow.createdAt) : channelFollow.createdAt != null)
            return false;
        return !(user != null ? !user.equals(channelFollow.user) : channelFollow.user != null);
    }

    @Override
    public int hashCode() {
        int result = createdAt != null ? createdAt.hashCode() : 0;
        result = 31 * result + (notifications ? 1 : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean notificationsEnabled() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
