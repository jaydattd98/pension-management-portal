package com.cognizant.springboot.pensionerdetail.services;

public interface AuthService {

    boolean isTokenValid(String jwtToken);
}
