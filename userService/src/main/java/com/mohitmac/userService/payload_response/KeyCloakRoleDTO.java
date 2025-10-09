package com.mohitmac.userService.payload_response;

import java.util.Map;

import lombok.Data;

@Data
public class KeyCloakRoleDTO {
    
    private String id;
    private String name;
    private String description;
    private String containerId;
    private String composite;
    private String clientRole;
    private Map<String  ,Object> attributes;
 
}
