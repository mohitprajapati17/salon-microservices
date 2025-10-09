package com.mohitmac.userService.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohitmac.userService.model.Users;
import com.mohitmac.userService.payload_response.dto.SignupDTO;
import com.mohitmac.userService.payload_response.response.AuthResponse;
import com.mohitmac.userService.payload_response.response.TokenResponse;
import com.mohitmac.userService.repository.UserRepository;
import com.mohitmac.userService.service.AuthService;
import com.mohitmac.userService.service.KeycloakService;


@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeycloakService keycloakService;
    
    @Override
    public AuthResponse login(String emailOrUsername, String password) throws Exception {
        // Try to find user by email or username
        Users user = userRepository.findByEmail(emailOrUsername)
            .or(() -> userRepository.findByUsername(emailOrUsername))
            .orElseThrow(() -> new Exception("User not found"));

        // Authenticate with Keycloak using username
        TokenResponse tokenResponse = keycloakService.getAdminAccessToken(user.getUsername(), password, "password", null);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setRefreshToken(tokenResponse.getRefreshToken());
        authResponse.setTitle("success");
        authResponse.setMessage("login success");
        authResponse.setUserRole(user.getRole());

        return authResponse;
        
    }
    
    
    
    @Override
    public AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception {



        TokenResponse tokenResponse = keycloakService.getAdminAccessToken(null, null, "refresh_token", refreshToken);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setRefreshToken(tokenResponse.getRefreshToken());
        authResponse.setTitle("success");
        authResponse.setMessage("User created successfully");


        return authResponse;
        
    }

    @Override
    public AuthResponse signup(SignupDTO req) throws Exception  {

        
        keycloakService.createUser(req);
       

        Users u=new Users();
        u.setFullName(req.getFullName());
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(req.getPassword());
        u.setRole(req.getUserRole());
        
        userRepository.save(u);

        TokenResponse tokenResponse = keycloakService.getAdminAccessToken(req.getUsername(), req.getPassword(), "password", null);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setRefreshToken(tokenResponse.getRefreshToken());
        authResponse.setTitle("success");
        authResponse.setMessage("User created successfully");
        authResponse.setUserRole(req.getUserRole());

        return authResponse;
    }
    
}
