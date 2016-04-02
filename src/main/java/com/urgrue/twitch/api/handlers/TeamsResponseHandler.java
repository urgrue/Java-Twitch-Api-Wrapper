package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Team;

import java.util.List;

public interface TeamsResponseHandler extends BaseFailureHandler {
    void onSuccess(List<Team> teams);
}
