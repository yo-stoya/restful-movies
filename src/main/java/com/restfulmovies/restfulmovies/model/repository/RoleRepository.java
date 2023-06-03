package com.restfulmovies.restfulmovies.model.repository;

import com.restfulmovies.restfulmovies.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface RoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByName(String name);

    Set<UserRole> findAllByNameIn(Collection<String> name);
}
