
package com.mohit.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import lombok.Data;
@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String reviewText;
        
    @Column(nullable = false)
    private double rating;
    
    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private Long salonId;
    
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt=LocalDateTime.now();
    
}
