package com.mohitmac.userService.service;

import com.mohitmac.userService.payload_response.dto.SignupDTO;
import com.mohitmac.userService.payload_response.response.AuthResponse;


public interface AuthService {
    
    AuthResponse login(String emailOrUsername, String password) throws Exception;
    AuthResponse signup(SignupDTO signupDTO) throws Exception ;
    AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception;
    
  

}
