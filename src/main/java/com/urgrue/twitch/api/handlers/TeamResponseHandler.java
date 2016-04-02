package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Team;

public interface TeamResponseHandler extends BaseFailureHandler {
    void onSuccess(Team team);
}
