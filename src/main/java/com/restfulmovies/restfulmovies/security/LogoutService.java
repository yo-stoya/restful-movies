package com.restfulmovies.restfulmovies.security;

import com.restfulmovies.restfulmovies.model.repository.JwtTokenRepository;
import com.restfulmovies.restfulmovies.security.token.JwtToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final JwtTokenRepository jwtTokenRepository;

    @Override
    @Transactional
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String token = authHeader.substring(7);
        jwtTokenRepository.revokeToken(token);
    }
}
