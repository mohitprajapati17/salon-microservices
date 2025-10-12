package com.mohit.payload_DTO;

import lombok.Data;

@Data
public class ReviewRequest {
    private String text;
    private double rating;
}
