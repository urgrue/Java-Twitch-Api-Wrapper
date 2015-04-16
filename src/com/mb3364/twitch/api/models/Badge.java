package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Badge {

    private String alpha;
    private String image;
    private String svg;

    @Override
    public String toString() {
        return "Badge{" +
                "alpha='" + alpha + '\'' +
                ", image='" + image + '\'' +
                ", svg='" + svg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Badge badge = (Badge) o;

        if (alpha != null ? !alpha.equals(badge.alpha) : badge.alpha != null) return false;
        if (image != null ? !image.equals(badge.image) : badge.image != null) return false;
        return !(svg != null ? !svg.equals(badge.svg) : badge.svg != null);
    }

    @Override
    public int hashCode() {
        int result = alpha != null ? alpha.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (svg != null ? svg.hashCode() : 0);
        return result;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
}
