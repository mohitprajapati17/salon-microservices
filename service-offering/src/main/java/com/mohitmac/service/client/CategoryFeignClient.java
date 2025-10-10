package com.mohitmac.service.client;


import com.mohitmac.payload_DTO.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CATEGORY-SERVICE")
public interface CategoryFeignClient {

    // @GetMapping("/api/categories/{id}")
    // public ResponseEntity<CategoryDTO> getCategoriesById(@PathVariable Long id) throws Throwable;

    @GetMapping("api/categories/salon-owner/salon/{salonId}/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoriesByIdAndSalon(@PathVariable Long id  , @PathVariable Long salonId ) throws Exception;

}
