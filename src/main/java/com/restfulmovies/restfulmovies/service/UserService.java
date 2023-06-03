package com.restfulmovies.restfulmovies.service;

import com.restfulmovies.restfulmovies.model.dto.user.UserDto;
import com.restfulmovies.restfulmovies.model.dto.user.AuthUserDto;
import com.restfulmovies.restfulmovies.security.token.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface UserService {

    TokenResponse register(UserDto dto);

    TokenResponse authenticate(AuthUserDto dto);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
