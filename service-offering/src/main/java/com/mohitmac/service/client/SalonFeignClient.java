package com.mohitmac.service.client;

import com.mohitmac.payload_DTO.SalonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient
public interface SalonFeignClient {

    @GetMapping("/api/salon/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(@RequestHeader("Authorization") String jwt) throws Exception;
}
