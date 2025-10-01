package com.mohitmac.catergory.Service.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohitmac.catergory.Service.model.Catergory;
import com.mohitmac.catergory.Service.service.CatergoryService;

@RestController
@RequestMapping("/api/categories")
public class CateforyController {

    @Autowired
    private CatergoryService catergoryService;

    @GetMapping("/salon/{id}")
    public ResponseEntity<Set<Catergory>> getCategoriesBySalon(@PathVariable Long id){
        Set<Catergory> catergories =catergoryService.getAllbySalon(id);
        return ResponseEntity.ok(catergories);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Catergory> getCategoriesById(@PathVariable Long id) throws Throwable{
        Catergory catergory =catergoryService.getById(id);
        return ResponseEntity.ok(catergory);

    }


    
}
