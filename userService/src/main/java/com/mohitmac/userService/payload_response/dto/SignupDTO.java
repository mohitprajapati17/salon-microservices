package com.mohitmac.userService.payload_response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mohitmac.userService.domain.UserRole;
import lombok.Data;

@Data
public class SignupDTO {
    private  String fullName;
    
    
    
    private String email;
    private String password;
    private String username;
    
    @JsonProperty("role")
    private UserRole userRole;
   
}
