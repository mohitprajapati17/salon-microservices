package com.mohitmac.controller;

import com.mohitmac.model.ServiceOffering;
import com.mohitmac.payload_DTO.CategoryDTO;
import com.mohitmac.payload_DTO.SalonDTO;
import com.mohitmac.payload_DTO.ServiceOfferingDTO;
import com.mohitmac.service.ServiceOfferingService;
import com.mohitmac.service.client.CategoryFeignClient;
import com.mohitmac.service.client.SalonFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    @Autowired
    private ServiceOfferingService serviceOfferingService;

    @Autowired
    private SalonFeignClient salonFeignClient;

    @Autowired
    private CategoryFeignClient categoryFeignClient;

    @PostMapping()
    ResponseEntity<ServiceOffering> create( @RequestBody  ServiceOfferingDTO serviceOfferingDTO,@RequestHeader("Authorization")  String jwt) throws Exception {
        SalonDTO salonDTO =salonFeignClient.getSalonByOwnerId(jwt).getBody();

        CategoryDTO categoryDTO = categoryFeignClient.getCategoriesByIdAndSalon(serviceOfferingDTO.getCategoryId(), salonDTO.getId()).getBody();

        ServiceOffering serviceOfferingDTO1=serviceOfferingService.createService(serviceOfferingDTO, salonDTO, categoryDTO);
        return ResponseEntity.ok(serviceOfferingDTO1);

    }


    @PostMapping("/{id}") //this does not match with tutorial here i use DTO but he use plain Entity
    ResponseEntity<ServiceOffering> update( @RequestBody  ServiceOfferingDTO serviceOfferingDTO , @PathVariable Long id){

        ServiceOffering serviceOffering=new ServiceOffering();


        ServiceOffering serviceOfferingDTO1=serviceOfferingService.updateService(id,serviceOfferingDTO);
        return ResponseEntity.ok(serviceOfferingDTO1);

    }
}
