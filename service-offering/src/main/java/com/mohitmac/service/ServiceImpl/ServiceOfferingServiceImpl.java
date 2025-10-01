package com.mohitmac.service.ServiceImpl;

import com.mohitmac.model.ServiceOffering;
import com.mohitmac.payload_DTO.CategoryDTO;
import com.mohitmac.payload_DTO.SalonDTO;
import com.mohitmac.payload_DTO.ServiceOfferingDTO;
import com.mohitmac.repository.ServiceOfferingRespository;
import com.mohitmac.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    @Autowired
    private ServiceOfferingRespository serviceOfferingRespository;
    @Override
    public ServiceOffering createService(ServiceOfferingDTO serviceOfferingDTO, SalonDTO salonDTO, CategoryDTO categoryDTO) {
        ServiceOffering serviceOffering=new ServiceOffering();
        serviceOffering.setName(serviceOfferingDTO.getName());
        serviceOffering.setDescription(serviceOfferingDTO.getDescription());
        serviceOffering.setPrice(serviceOfferingDTO.getPrice());
        serviceOffering.setDuration(serviceOfferingDTO.getDuration());
        serviceOffering.setSalonId(salonDTO.getId());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setImage(serviceOfferingDTO.getImage());
        return serviceOfferingRespository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOfferingDTO serviceOfferingDTO) {
        ServiceOffering serviceOffering1=serviceOfferingRespository.findById(serviceId).orElseThrow(()->new RuntimeException("not  found service offering "+serviceId));

        serviceOffering1.setName(serviceOfferingDTO.getName());
        serviceOffering1.setDescription(serviceOfferingDTO.getDescription());
        serviceOffering1.setPrice(serviceOfferingDTO.getPrice());
        serviceOffering1.setDuration(serviceOfferingDTO.getDuration());
        serviceOffering1.setImage(serviceOfferingDTO.getImage());
        return serviceOfferingRespository.save(serviceOffering1);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {
        Set<ServiceOffering> serviceOfferings = serviceOfferingRespository.findBySalonId(salonId);
        if(categoryId!=null){
            serviceOfferings= serviceOfferings.stream().filter((service)->service.getCategoryId()!=null&&service.getCategoryId()==categoryId).collect(Collectors.toSet());
        }
        return serviceOfferings;

    }

    @Override
    public Set<ServiceOffering> getServiceByIds(Set<Long> ids) {
        List<ServiceOffering> serviceOfferingList= serviceOfferingRespository.findAllById(ids);
        return new HashSet<>(serviceOfferingList);
    }

    @Override
    public ServiceOffering getServiceById(Long id)  throws  RuntimeException{
        ServiceOffering serviceOfferings = serviceOfferingRespository.findById(id).orElseThrow(()->new RuntimeException("no service Exist by this id "+id));

        return serviceOfferings;
    }
}
