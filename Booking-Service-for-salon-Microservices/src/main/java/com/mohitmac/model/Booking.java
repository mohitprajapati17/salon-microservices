package com.mohitmac.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.mohitmac.domain.BookingStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long salonId;

    private Long customerId;


    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ElementCollection
    private Set<Long> serviceIds;

    private BookingStatus status=BookingStatus.PENDING;
    private Integer totalPrice;

}
