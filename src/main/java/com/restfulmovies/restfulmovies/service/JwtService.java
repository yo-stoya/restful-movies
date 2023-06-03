package com.restfulmovies.restfulmovies.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.io.Decoders.BASE64;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Service
public class JwtService {

    @Value("${app.security.jwt.secretKey}")
    private String secretKey;

    @Value("${app.security.jwt.expiration}")
    private Long tokenExpiration;

    @Value("${app.security.jwt.refreshToken.expiration}")
    private Long refreshTokenExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimToExtract) {
        Claims claims = extractAllClaims(token);
        return claimToExtract.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String username) {
        return buildToken(new HashMap<>(), username);
    }

    public String generateRefreshToken(String username) {
        return buildToken(new HashMap<>(), username, refreshTokenExpiration);
    }


    private String buildToken(Map<String, Object> claims, String username) {
        return buildToken(claims, username, tokenExpiration);
    }

    private String buildToken(Map<String, Object> claims, String username, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBites = BASE64.decode(secretKey);
        return hmacShaKeyFor(keyBites);
    }
}
