package com.mohitmac.Payment.Service.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.CategoryDTO;

@FeignClient("CATEGORY-SERVICE")
public interface CategoryFeignClient {

    
    @GetMapping("/api/categories/salon-owner/salon/{salonId}/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoriesByIdAndSalon(@PathVariable Long salonId , @PathVariable Long id ) throws Exception;

}
