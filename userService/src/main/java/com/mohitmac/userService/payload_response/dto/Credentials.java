package com.mohitmac.userService.payload_response.dto;

import lombok.Data;

@Data
public class Credentials {
    private String type;
    private String value;
    private  boolean temporary;

    
}
