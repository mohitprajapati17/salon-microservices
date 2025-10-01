package com.mohitmac.repository;

import com.mohitmac.model.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ServiceOfferingRespository extends JpaRepository<ServiceOffering ,Long> {
    Set<ServiceOffering> findBySalonId(Long salonId);
    Set<ServiceOffering> findAllById(Long Id);

}
