package com.restfulmovies.restfulmovies.model.repository;

import com.restfulmovies.restfulmovies.security.token.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {

    Set<JwtToken> findAllByUserIdAndExpiredFalseAndRevokedFalse(Long userId);

    Optional<JwtToken> findByToken(String token);

    @Modifying
    @Query("UPDATE JwtToken t set t.expired = TRUE, t.revoked = TRUE where t.user.id = :userId")
    void revokeUserTokens(Long userId);

    @Modifying
    @Query("UPDATE JwtToken t set t.expired = TRUE, t.revoked = TRUE where t.token = :token")
    void revokeToken(String token);

}
