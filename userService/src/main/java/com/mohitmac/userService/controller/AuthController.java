package com.mohitmac.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mohitmac.userService.payload_response.dto.LoginDTO;
import com.mohitmac.userService.payload_response.dto.SignupDTO;
import com.mohitmac.userService.payload_response.response.AuthResponse;
import com.mohitmac.userService.service.AuthService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController

@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    
    
    @PostMapping("/signup")
    public   ResponseEntity<AuthResponse> signup(@RequestBody SignupDTO req) throws Exception{
        AuthResponse res=authService.signup(req);
        return ResponseEntity.ok(res);


    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDTO loginDTO) throws Exception{
        AuthResponse l=authService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return ResponseEntity.ok(l);

    }

    @GetMapping("/access-token/refresh-token/{refresh-token}")
    public ResponseEntity<AuthResponse>  getAccessToken(@PathVariable("refresh-token") String refreshToken) throws Exception{
        AuthResponse res=authService.getAccessTokenFromRefreshToken(refreshToken);
        return ResponseEntity.ok(res);
    }
    
}
