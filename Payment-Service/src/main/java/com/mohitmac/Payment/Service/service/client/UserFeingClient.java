package com.mohitmac.Payment.Service.service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.UserDTO;

@FeignClient("USERSERVICE")
public interface UserFeingClient {

    @GetMapping("/api/users/{userid}")
    public UserDTO getUser(@PathVariable("userid") Long userid ) throws Exception;

    @GetMapping("/api/users/profile")
    public ResponseEntity<UserDTO> getUserByProfile(@RequestHeader("Authorization") String jwt) throws Exception;

}
