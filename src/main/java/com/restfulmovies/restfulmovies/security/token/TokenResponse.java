package com.restfulmovies.restfulmovies.security.token;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
