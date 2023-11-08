package com.teetony.springsecurity.service;

import com.teetony.springsecurity.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JWTService  {
    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String,Object> extractClaims, UserDetails userDetails);
}
