package com.mohit.payload_DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String reviewText;
    private double rating;
    private Long userId;
    private Long salonId;
    private LocalDateTime createdAt;
    private String userName;
    private String salonName;
}
