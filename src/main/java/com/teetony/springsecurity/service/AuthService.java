package com.teetony.springsecurity.service;

import com.teetony.springsecurity.dto.JwtAuthResponse;
import com.teetony.springsecurity.dto.RefreshTokenDto;
import com.teetony.springsecurity.dto.SignInDto;
import com.teetony.springsecurity.dto.SignUpDto;
import com.teetony.springsecurity.entities.User;

public interface AuthService {
    User signup(SignUpDto signUpDto);
    User signupAdmin(SignUpDto signUpDto);

    JwtAuthResponse signIn(SignInDto signInDto);

    JwtAuthResponse refreshToken(RefreshTokenDto refreshTokenDto);
}

