package com.restfulmovies.restfulmovies.web;

import com.restfulmovies.restfulmovies.model.dto.user.AuthUserDto;
import com.restfulmovies.restfulmovies.model.dto.user.UserDto;
import com.restfulmovies.restfulmovies.security.token.TokenResponse;
import com.restfulmovies.restfulmovies.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthUserDto dto) {
        return ResponseEntity.ok(userService.authenticate(dto));
    }

    @PostMapping("/refresh")
    public void authenticate(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        userService.refreshToken(request, response);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("We are sad to see you go :(");
    }

}
