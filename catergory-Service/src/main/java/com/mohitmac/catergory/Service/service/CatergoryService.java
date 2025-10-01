package com.mohitmac.catergory.Service.service;

import java.util.Set;


import com.mohitmac.catergory.Service.model.Catergory;
import com.mohitmac.catergory.Service.payload_DTO.SalonDTO;

public interface CatergoryService {
    Catergory saveCategory (Catergory catergory,SalonDTO salonDTO);
    Set<Catergory> getAllbySalon(Long id);
    Catergory getById(Long id) throws Exception;
    void deleteById(Long id,SalonDTO
     salonDTO) throws Exception;
    
}
