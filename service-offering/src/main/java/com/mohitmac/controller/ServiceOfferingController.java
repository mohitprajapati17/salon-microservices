package com.mohitmac.controller;

import com.mohitmac.model.ServiceOffering;
import com.mohitmac.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    @Autowired
    private ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{id}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(@PathVariable Long id , @RequestParam(required=false) Long categoryId ){
        Set<ServiceOffering> serviceOffering=serviceOfferingService.getAllServiceBySalonId(id,categoryId);
        return ResponseEntity.ok(serviceOffering);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getAllById(@PathVariable Long id){
        return ResponseEntity.ok(serviceOfferingService.getServiceById(id));
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Set<ServiceOffering>> getAllByIds(@PathVariable Set<Long> id){
        return ResponseEntity.ok(serviceOfferingService.getServiceByIds(id));
    }








}
