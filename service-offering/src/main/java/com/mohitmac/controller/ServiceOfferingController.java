package com.mohitmac.controller;

import com.mohitmac.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    @Autowired
    private ServiceOfferingService serviceOfferingService;


}
