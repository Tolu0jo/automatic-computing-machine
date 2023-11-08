package com.teetony.springsecurity.controller;

import com.teetony.springsecurity.dto.JwtAuthResponse;
import com.teetony.springsecurity.dto.RefreshTokenDto;
import com.teetony.springsecurity.dto.SignInDto;
import com.teetony.springsecurity.dto.SignUpDto;
import com.teetony.springsecurity.entities.User;
import com.teetony.springsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpDto signUpDto){
       return ResponseEntity.ok(authService.signup(signUpDto));
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<User> signUpAdmin(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(authService.signupAdmin(signUpDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignInDto signInDto){
        return ResponseEntity.ok(authService.signIn(signInDto));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenDto));
    }

}
