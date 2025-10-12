package com.mohit.model;

import lombok.Data;

import java.time.LocalDateTime;

import com.mohit.payload_DTO.BookingDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Boolean isRead=false;
    private String message;

    private Long userId;
    private Long bookingId;
    private Long salonId;

    private  LocalDateTime createdAt;
    
}
