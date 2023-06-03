package com.restfulmovies.restfulmovies.service.impl;

import com.restfulmovies.restfulmovies.exception.NotFoundException;
import com.restfulmovies.restfulmovies.model.repository.UserRepository;
import com.restfulmovies.restfulmovies.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new NotFoundException("User", username));
    }
}
