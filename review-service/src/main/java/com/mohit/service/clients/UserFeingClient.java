package com.mohit.service.clients;

import com.mohit.payload_DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("USERSERVICE")
public interface UserFeingClient {

    @GetMapping("/api/users/{userid}")
    public UserDTO getUser(@PathVariable("userid") Long userid ) throws Exception;

    @GetMapping("/api/users/profile")
    public ResponseEntity<UserDTO> getUserByProfile(@RequestHeader("Authorization") String jwt) throws Exception;

}
