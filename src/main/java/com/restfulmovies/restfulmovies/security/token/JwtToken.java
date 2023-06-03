package com.restfulmovies.restfulmovies.security.token;

import com.restfulmovies.restfulmovies.model.entity.BaseEntity;
import com.restfulmovies.restfulmovies.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken extends BaseEntity {

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    private boolean expired;

    // upon server restart
    private boolean revoked;

    @ManyToOne
    private User user;
}
