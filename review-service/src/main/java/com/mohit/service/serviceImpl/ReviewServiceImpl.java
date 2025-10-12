package com.mohit.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.model.Review;
import com.mohit.payload_DTO.ReviewRequest;
import com.mohit.payload_DTO.SalonDTO;
import com.mohit.payload_DTO.UserDTO;
import com.mohit.repository.ReviewRepo;
import com.mohit.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    @Autowired
    private ReviewRepo reviewRepo;
    
    @Override
    public Review createReview(ReviewRequest reviewRequest, UserDTO userDTO, SalonDTO salonDTO) {
        Review review = new Review();
        review.setReviewText(reviewRequest.getText());
        review.setRating(reviewRequest.getRating());
        review.setUserId(userDTO.getId());
        review.setSalonId(salonDTO.getId());
        return reviewRepo.save(review);
    }
    
    @Override
    public Review updateReview(Long reviewId, ReviewRequest reviewRequest, UserDTO userDTO) throws Exception {
        Review review = reviewRepo.findById(reviewId)
            .orElseThrow(() -> new Exception("Review not found"));
        
        if(!review.getUserId().equals(userDTO.getId())) {
            throw new Exception("You are not authorized to update this review");
        }
        
        review.setReviewText(reviewRequest.getText());
        review.setRating(reviewRequest.getRating());
        return reviewRepo.save(review);
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = reviewRepo.findById(reviewId)
            .orElseThrow(() -> new Exception("Review not found"));
        
        if(!review.getUserId().equals(userId)) {
            throw new Exception("You are not authorized to delete this review");
        }
        
        reviewRepo.delete(review);
    }
    
    @Override
    public List<Review> getReviewsBySalonId(Long salonId) {
        return reviewRepo.findBySalonId(salonId);
    }
}
