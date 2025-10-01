package com.mohitmac.catergory.Service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohitmac.catergory.Service.model.Catergory;
import com.mohitmac.catergory.Service.payload_DTO.SalonDTO;
import com.mohitmac.catergory.Service.service.CatergoryService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {
    @Autowired
    private CatergoryService catergoryService;

    
    
    @PostMapping()
    public ResponseEntity<Catergory> getCategoriesBySalon(@RequestBody Catergory catergory){
        SalonDTO salonDTO =new SalonDTO();
        salonDTO.setId(1L);
        Catergory catergory2=catergoryService.saveCategory(catergory, salonDTO);
        return  ResponseEntity.ok(catergory2);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception{
        SalonDTO salonDTO =new SalonDTO();
        salonDTO.setId(1L);
        catergoryService.deleteById(id, salonDTO.getId());
        return ResponseEntity.ok("Delete  Category Succesfully");
       
    }

}
