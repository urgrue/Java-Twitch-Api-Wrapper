package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    private String userName;
    private boolean valid;
    private TokenAuthorization authorization;

    @Override
    public String toString() {
        return "Token{" +
                "userName='" + userName + '\'' +
                ", valid=" + valid +
                ", authorization=" + authorization +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        return !(userName != null ? !userName.equals(token.userName) : token.userName != null);

    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public TokenAuthorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(TokenAuthorization authorization) {
        this.authorization = authorization;
    }
}
