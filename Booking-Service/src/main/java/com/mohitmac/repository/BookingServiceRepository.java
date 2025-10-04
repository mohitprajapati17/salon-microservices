

package com.mohitmac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohitmac.model.Booking;


@Repository
public interface BookingServiceRepository extends JpaRepository<Booking, Long>  {

    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findBySalonId(Long salonId);
    
}
