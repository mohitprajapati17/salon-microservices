package com.mohit.service;

import java.util.List;

import com.mohit.payload_DTO.ReviewRequest;
import com.mohit.payload_DTO.SalonDTO;
import com.mohit.payload_DTO.UserDTO;
import com.mohit.model.Review;

public interface ReviewService {
    Review createReview(ReviewRequest reviewRequest, UserDTO userDTO, SalonDTO salonDTO);
    
    Review updateReview(Long reviewId, ReviewRequest reviewRequest, UserDTO userDTO) throws Exception;
    
    void deleteReview(Long reviewId, Long userId) throws Exception;
    
    List<Review> getReviewsBySalonId(Long salonId);
    
}
