package com.mohitmac.userService.payload_response.response;

import com.mohitmac.userService.domain.UserRole;

import lombok.Data;

@Data
public class AuthResponse {
      private String jwt;
    private String refreshToken;
    private String message;
    private String  title;
    private UserRole userRole;
}
