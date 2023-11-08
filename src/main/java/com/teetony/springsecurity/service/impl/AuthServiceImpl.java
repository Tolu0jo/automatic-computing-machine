package com.teetony.springsecurity.service.impl;

import com.teetony.springsecurity.dto.JwtAuthResponse;
import com.teetony.springsecurity.dto.RefreshTokenDto;
import com.teetony.springsecurity.dto.SignInDto;
import com.teetony.springsecurity.dto.SignUpDto;
import com.teetony.springsecurity.entities.Role;
import com.teetony.springsecurity.entities.User;
import com.teetony.springsecurity.repository.UserRepository;
import com.teetony.springsecurity.service.AuthService;
import com.teetony.springsecurity.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public User signup(SignUpDto signUpDto){
      User user = new User();
      user.setEmail(signUpDto.getEmail());
      user.setFirstname(signUpDto.getFirstName());
      user.setLastname(signUpDto.getLastName());
      user.setRole(Role.USER);
      user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
    return userRepository.save(user);
    }

    public User signupAdmin(SignUpDto signUpDto){
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setFirstname(signUpDto.getFirstName());
        user.setLastname(signUpDto.getLastName());
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        return userRepository.save(user);
    }

    public JwtAuthResponse signIn(SignInDto signInDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(),signInDto.getPassword()));
     var user = userRepository.findByEmail(signInDto.getEmail())
             .orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
     String token = jwtService.generateToken(user);
     String refreshToken= jwtService.generateRefreshToken(new HashMap<>(),user);

     JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
     jwtAuthResponse.setToken(token);
     jwtAuthResponse.setRefreshToken(refreshToken);
     return jwtAuthResponse;
    }

    public JwtAuthResponse refreshToken(RefreshTokenDto refreshTokenDto) {
        String userEmail = jwtService.extractUsername(refreshTokenDto.getToken());
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();
        if (jwtService.isTokenValid(refreshTokenDto.getToken(), user)) {
            var token = jwtService.generateToken(user);
            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setRefreshToken(refreshTokenDto.getToken());
            jwtAuthResponse.setToken(token);
            return jwtAuthResponse;
        }
     return null;
    }
}
