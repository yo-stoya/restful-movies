package com.restfulmovies.restfulmovies.security;

import com.restfulmovies.restfulmovies.model.entity.User;
import com.restfulmovies.restfulmovies.model.entity.UserRole;
import com.restfulmovies.restfulmovies.model.repository.RoleRepository;
import com.restfulmovies.restfulmovies.model.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class Init implements CommandLineRunner {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {


        roleRepository.save(new UserRole("ADMIN"));
        roleRepository.save(new UserRole("USER"));

        User u1 = new User("admin", encoder.encode("123"), Set.of(roleRepository.findByName("ADMIN")), Set.of());
        User u2 = new User("user", encoder.encode("123"), Set.of(roleRepository.findByName("USER")), Set.of());

        userRepository.save(u1);
        userRepository.save(u2);
    }
}
