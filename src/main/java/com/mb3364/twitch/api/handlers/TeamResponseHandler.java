package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Team;

public interface TeamResponseHandler extends BaseFailureHandler {
    void onSuccess(Team team);
}
