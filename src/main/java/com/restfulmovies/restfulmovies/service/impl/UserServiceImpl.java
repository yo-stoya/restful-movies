package com.restfulmovies.restfulmovies.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfulmovies.restfulmovies.exception.notfound.UserNotFoundException;
import com.restfulmovies.restfulmovies.model.dto.user.UserDto;
import com.restfulmovies.restfulmovies.model.dto.user.AuthUserDto;
import com.restfulmovies.restfulmovies.model.entity.User;
import com.restfulmovies.restfulmovies.model.mapper.UserMapper;
import com.restfulmovies.restfulmovies.model.repository.JwtTokenRepository;
import com.restfulmovies.restfulmovies.model.repository.UserRepository;
import com.restfulmovies.restfulmovies.security.token.JwtToken;
import com.restfulmovies.restfulmovies.security.token.TokenResponse;
import com.restfulmovies.restfulmovies.service.JwtService;
import com.restfulmovies.restfulmovies.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.restfulmovies.restfulmovies.security.token.TokenType.BEARER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final JwtTokenRepository tokenRepository;

    @Override
    @Transactional
    public TokenResponse register(UserDto dto) {
        final User user = userRepository.save(userMapper.toEntity(dto));
        final String jwtToken = jwtService.generateToken(user.getUsername());
        final String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        tokenRepository.revokeUserTokens(user.getId());
        tokenRepository.save(mapJwtToken(user, jwtToken));

        return new TokenResponse(jwtToken, refreshToken);
    }

    @Override
    @Transactional
    public TokenResponse authenticate(AuthUserDto dto) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

        final User user = findUserByName(dto.username());
        final String jwtToken = jwtService.generateToken(user.getUsername());
        final String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        tokenRepository.revokeUserTokens(user.getId());
        tokenRepository.save(mapJwtToken(user, jwtToken));

        return new TokenResponse(jwtToken, refreshToken);
    }

    @Override
    @Transactional
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String refreshToken = authHeader.substring(7);
        final String username = jwtService.extractUsername(refreshToken);

        if (username != null) {

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                final String accessToken = jwtService.generateToken(username);
                final TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken);
                final User user = findUserByName(username);

                tokenRepository.revokeUserTokens(user.getId());
                tokenRepository.save(mapJwtToken(user, accessToken));

                new ObjectMapper().writeValue(response.getOutputStream(), tokenResponse);
            }
        }

    }

    private User findUserByName(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    private JwtToken mapJwtToken(User newUser, String jwtToken) {
        return new JwtToken(jwtToken, BEARER, false, false, newUser);
    }

}
