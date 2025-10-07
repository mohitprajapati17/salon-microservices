package com.mohitmac.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohitmac.model.Salon;
import com.mohitmac.payload_DTO.SalonDTO;
import com.mohitmac.payload_DTO.UserDTO;
import com.mohitmac.repository.SalonReposotory;
import com.mohitmac.service.ServiceSalon;
@Service
public class SalonServiceImpl implements ServiceSalon {

    @Autowired
    private SalonReposotory salonRepository;
    @Override
    public Salon createSalon(SalonDTO salonDTO, UserDTO userDTO) {
        Salon salon = new Salon();
        salon.setName(salonDTO.getName());
        salon.setAddress(salonDTO.getAddress());
        salon.setPhone(salonDTO.getPhone());
        salon.setEmail(salonDTO.getEmail());
        salon.setCity(salonDTO.getCity());
        salon.setOwnerId(userDTO.getId());
        salon.setOpenTime(salonDTO.getOpenTime());
        salon.setCloseTime(salonDTO.getCloseTime());
        salon.setImages(salonDTO.getImages());
        salon.setActive(true);
        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, Long salonId) throws Exception {
        Salon existing  =salonRepository.findById(salonId).orElseThrow(() -> new Exception("Salon not found with id: " + salonId));
        existing.setName(salon.getName());
        existing.setAddress(salon.getAddress());
        existing.setPhone(salon.getPhone());
        existing.setEmail(salon.getEmail());
        existing.setCity(salon.getCity());
        existing.setOpenTime(salon.getOpenTime());
        existing.setCloseTime(salon.getCloseTime());
        existing.setImages(salon.getImages());
        return salonRepository.save(existing);
    }

    @Override
    public List<Salon> getAllSalons() {
       return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception {
        Salon salon =salonRepository.findById(salonId).orElseThrow(() -> new Exception("Salon not found with id: " + salonId));
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> getSalonsByCity(String city) {
        return salonRepository.findByCity(city);
    }

    
    
}
