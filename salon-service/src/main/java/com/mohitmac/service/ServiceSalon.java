package com.mohitmac.service;

import java.util.List;

import com.mohitmac.model.Salon;
import com.mohitmac.payload_DTO.SalonDTO;
import com.mohitmac.payload_DTO.UserDTO;

public interface ServiceSalon {

    public Salon createSalon(SalonDTO salonDTO,UserDTO userDTO);
    public Salon updateSalon(SalonDTO salon,UserDTO userDTO,Long salonId) throws Exception;
    public List<Salon> getAllSalons();
    public Salon getSalonById(Long salonId) throws Exception;
    public Salon getSalonByOwnerId(Long ownerId);
    public List<Salon> getSalonsByCity(String city);
    
}
