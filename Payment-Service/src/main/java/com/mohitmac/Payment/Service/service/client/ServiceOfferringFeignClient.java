package com.mohitmac.Payment.Service.service.client;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.ServiceOfferingDTO;



@FeignClient("SERVICE-OFFERING")
public interface ServiceOfferringFeignClient {
    @GetMapping("/api/service-offering/list/{id}")
     ResponseEntity<Set<ServiceOfferingDTO>> getAllByIds(@PathVariable Set<Long> id);
      

} 
