package com.mohitmac.service;


import com.mohitmac.model.ServiceOffering;
import com.mohitmac.payload_DTO.CategoryDTO;
import com.mohitmac.payload_DTO.SalonDTO;
import com.mohitmac.payload_DTO.ServiceOfferingDTO;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(ServiceOfferingDTO serviceOfferingDTO, SalonDTO salonDTO, CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId,ServiceOfferingDTO serviceOfferingDTO);
    Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long  categoryId);
    Set<ServiceOffering> getServiceByIds(Set<Long> ids);
    ServiceOffering getServiceById(Long id);
}
