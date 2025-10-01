package com.mohitmac.controller;

import com.mohitmac.model.ServiceOffering;
import com.mohitmac.payload_DTO.CategoryDTO;
import com.mohitmac.payload_DTO.SalonDTO;
import com.mohitmac.payload_DTO.ServiceOfferingDTO;
import com.mohitmac.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    @Autowired
    private ServiceOfferingService serviceOfferingService;

    @PostMapping()
    ResponseEntity<ServiceOffering> create( @RequestBody  ServiceOfferingDTO serviceOfferingDTO){
        SalonDTO salonDTO =new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceOfferingDTO.getCategoryId());
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
