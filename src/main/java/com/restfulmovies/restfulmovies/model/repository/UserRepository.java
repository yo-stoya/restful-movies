package com.restfulmovies.restfulmovies.model.repository;

import com.restfulmovies.restfulmovies.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
