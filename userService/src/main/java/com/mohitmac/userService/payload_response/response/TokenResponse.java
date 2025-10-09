package com.mohitmac.userService.payload_response.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("scope")
    private String scope;
    
}
