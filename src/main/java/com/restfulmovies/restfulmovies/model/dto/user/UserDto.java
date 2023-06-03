package com.restfulmovies.restfulmovies.model.dto.user;

import java.util.Set;

public record UserDto(
        String username,
        String password,
        Set<String> roles
) {
}
