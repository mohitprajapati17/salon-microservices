package com.mohitmac.userService.payload_response.dto;

import java.util.ArrayList;

import java.util.List;

import lombok.Data;

@Data
public class UserRequest {

    private  String username;
    private Boolean  enabled;
    private String  firstName;
    private String  lastName;
    private String  email;
    private List<Credentials> credentials =new  ArrayList<>();
    
}
