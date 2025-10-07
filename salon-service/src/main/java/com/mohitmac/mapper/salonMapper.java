package com.mohitmac.mapper;

import com.mohitmac.model.Salon;
import com.mohitmac.payload_DTO.SalonDTO;

public class salonMapper {
    public static SalonDTO toSalonDTO(Salon salon){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salon.getId());
        salonDTO.setName(salon.getName());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setPhone(salon.getPhone());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setCity(salon.getCity());
        salonDTO.setOpenTime(salon.getOpenTime());
        salonDTO.setCloseTime(salon.getCloseTime());
        salonDTO.setImages(salon.getImages());
        return salonDTO;
    }
}
