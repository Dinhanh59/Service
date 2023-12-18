package com.example.phatdang.auth.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private  final AuthenticationSevice sevice;
    @PostMapping("/register")
    public ResponseEntity<AuthentionResponse> register(
            @RequestBody RegisterRequest request
    )
    {
        return ResponseEntity.ok(sevice.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthentionResponse> authenticate(
            @RequestBody AuthenticationRequest request
    )
    {
        return ResponseEntity.ok(sevice.authenticate(request));
    }
}
