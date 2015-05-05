package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelBadges {

    private Badge globalMod;
    private Badge admin;
    private Badge broadcaster;
    private Badge mod;
    private Badge staff;
    private Badge turbo;
    private Badge subscriber;

    @Override
    public String toString() {
        return "ChannelBadges{" +
                "globalMod=" + globalMod +
                ", admin=" + admin +
                ", broadcaster=" + broadcaster +
                ", mod=" + mod +
                ", staff=" + staff +
                ", turbo=" + turbo +
                ", subscriber=" + subscriber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChannelBadges that = (ChannelBadges) o;

        if (globalMod != null ? !globalMod.equals(that.globalMod) : that.globalMod != null) return false;
        if (admin != null ? !admin.equals(that.admin) : that.admin != null) return false;
        if (broadcaster != null ? !broadcaster.equals(that.broadcaster) : that.broadcaster != null) return false;
        if (mod != null ? !mod.equals(that.mod) : that.mod != null) return false;
        if (staff != null ? !staff.equals(that.staff) : that.staff != null) return false;
        if (turbo != null ? !turbo.equals(that.turbo) : that.turbo != null) return false;
        return !(subscriber != null ? !subscriber.equals(that.subscriber) : that.subscriber != null);
    }

    @Override
    public int hashCode() {
        int result = globalMod != null ? globalMod.hashCode() : 0;
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (broadcaster != null ? broadcaster.hashCode() : 0);
        result = 31 * result + (mod != null ? mod.hashCode() : 0);
        result = 31 * result + (staff != null ? staff.hashCode() : 0);
        result = 31 * result + (turbo != null ? turbo.hashCode() : 0);
        result = 31 * result + (subscriber != null ? subscriber.hashCode() : 0);
        return result;
    }

    public Badge getGlobalMod() {

        return globalMod;
    }

    public void setGlobalMod(Badge globalMod) {
        this.globalMod = globalMod;
    }

    public Badge getAdmin() {
        return admin;
    }

    public void setAdmin(Badge admin) {
        this.admin = admin;
    }

    public Badge getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(Badge broadcaster) {
        this.broadcaster = broadcaster;
    }

    public Badge getMod() {
        return mod;
    }

    public void setMod(Badge mod) {
        this.mod = mod;
    }

    public Badge getStaff() {
        return staff;
    }

    public void setStaff(Badge staff) {
        this.staff = staff;
    }

    public Badge getTurbo() {
        return turbo;
    }

    public void setTurbo(Badge turbo) {
        this.turbo = turbo;
    }

    public Badge getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Badge subscriber) {
        this.subscriber = subscriber;
    }
}
