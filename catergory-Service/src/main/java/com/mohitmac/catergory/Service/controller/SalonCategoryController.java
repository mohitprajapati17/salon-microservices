package com.mohitmac.catergory.Service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mohitmac.catergory.Service.model.Catergory;
import com.mohitmac.catergory.Service.payload_DTO.SalonDTO;
import com.mohitmac.catergory.Service.service.CatergoryService;
import com.mohitmac.catergory.Service.service.client.SalonFeignClient;

import java.util.Set;


@RestController
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {
    @Autowired
    private CatergoryService catergoryService;
    @Autowired
    private SalonFeignClient salonFeignClient;

    
    
    @PostMapping()
    public ResponseEntity<Catergory> createCategory(@RequestBody Catergory catergory,@RequestHeader("Authorization") String jwt) throws Exception{
        SalonDTO salonDTO =salonFeignClient.getSalonByOwnerId(jwt).getBody();
        Catergory catergory2=catergoryService.saveCategory(catergory, salonDTO);
        return  ResponseEntity.ok(catergory2);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id , @RequestHeader("Authorization") String jwt) throws Exception{
        SalonDTO salonDTO =salonFeignClient.getSalonByOwnerId(jwt).getBody();
        catergoryService.deleteById(id, salonDTO.getId());
        return ResponseEntity.ok("Delete  Category Succesfully");
       
    }



    @GetMapping("/salon/{salonId}/category/{id}")
    public ResponseEntity<Catergory> getCategoriesByIdAndSalon(@PathVariable Long salonId  , @PathVariable Long id ) throws Exception{
        Catergory catergory=catergoryService.findBySalonIdAndId(id,salonId);
        return ResponseEntity.ok(catergory);

    }

}
