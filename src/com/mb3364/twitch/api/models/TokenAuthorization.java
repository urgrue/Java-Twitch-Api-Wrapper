package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mb3364.twitch.api.auth.Scopes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenAuthorization {

    private List<Scopes> scopes;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return "TokenAuthorization{" +
                "scopes=" + scopes +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenAuthorization that = (TokenAuthorization) o;

        if (scopes != null ? !scopes.equals(that.scopes) : that.scopes != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        return !(updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null);
    }

    @Override
    public int hashCode() {
        int result = scopes != null ? scopes.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    public List<Scopes> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = new ArrayList<Scopes>();
        for (String s : scopes) {
            this.scopes.add(Scopes.fromString(s));
        }
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
