package com.mohitmac.service.client;

import com.mohitmac.payload_DTO.SalonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("SALON-SERVICE")
public interface SalonFeignClient {

    @GetMapping("/api/salon/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long id) throws Exception;
        
    

}
