package com.cognizant.springboot.processpension.services;

public interface AuthService {
    boolean isTokenValid(String jwtToken);
}
