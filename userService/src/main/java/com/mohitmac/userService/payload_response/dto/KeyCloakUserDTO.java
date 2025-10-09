package com.mohitmac.userService.payload_response.dto;

import lombok.Data;

@Data
public class KeyCloakUserDTO {
    private String id;
   
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    
}
