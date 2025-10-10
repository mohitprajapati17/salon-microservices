package com.mohitmac.catergory.Service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mohitmac.catergory.Service.model.Catergory;
import com.mohitmac.catergory.Service.payload_DTO.SalonDTO;
import com.mohitmac.catergory.Service.service.CatergoryService;

import java.util.Set;


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



    @GetMapping("/salon/{salonId}/category/{id}")
    public ResponseEntity<Catergory> getCategoriesByIdAndSalon(@PathVariable Long id  , @PathVariable Long salonId ){
        Catergory catergory=catergoryService.findBySalonIdAndId(id,salonId);
        return ResponseEntity.ok(catergory);

    }

}
