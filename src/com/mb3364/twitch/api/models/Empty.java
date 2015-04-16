package com.mb3364.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is purposefully left empty. It is for successful
 * responses from the Twitch API that contain no body data.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Empty {
}
