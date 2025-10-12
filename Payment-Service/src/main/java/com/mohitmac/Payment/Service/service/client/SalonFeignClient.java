
package com.mohitmac.Payment.Service.service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.SalonDTO;

@FeignClient("SALON-SERVICE")
public interface SalonFeignClient {

    @GetMapping("/api/salon/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(@RequestHeader("Authorization") String jwt) throws Exception;

    @GetMapping("/api/salon/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long id) throws Exception;
        
}
