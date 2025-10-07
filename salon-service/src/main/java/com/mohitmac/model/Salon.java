package com.mohitmac.model;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Long ownerId;
    
    private boolean active;
    private LocalTime openTime;

    private LocalTime closeTime;

    @Column(nullable = false)
    private String email;
    @ElementCollection
    private List<String> images;
    
}
