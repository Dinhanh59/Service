package com.example.phatdang.auth.controller;

import com.example.phatdang.auth.Role;
import com.example.phatdang.auth.User;
import com.example.phatdang.auth.UserRepository;
import com.example.phatdang.auth.config.JwtSerivice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationSevice {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private  final JwtSerivice jwtSerivice;
    private final AuthenticationManager authenticationManager;
    public AuthentionResponse register(RegisterRequest request) {
        String pass=request.getPassword();
        var user = User.builder()
                .firstName(request.getFistname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(pass))
                .role(Role.valueOf("USER"))
                .build();
        repository.save(user);
        var jwtToken = jwtSerivice.generateToken(user);
        return AuthentionResponse.builder()
                .token(jwtToken)
                .build();

    }


    public AuthentionResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken= jwtSerivice.generateToken(user);
        return AuthentionResponse.builder()
                .token(jwtToken)
                .build();

    }
}
