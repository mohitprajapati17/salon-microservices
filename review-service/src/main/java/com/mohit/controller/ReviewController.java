package com.mohit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mohit.model.Review;
import com.mohit.payload_DTO.ApiResponse;
import com.mohit.payload_DTO.ReviewDTO;
import com.mohit.payload_DTO.ReviewRequest;
import com.mohit.payload_DTO.SalonDTO;
import com.mohit.payload_DTO.UserDTO;
import com.mohit.service.ReviewService;
import com.mohit.service.clients.SalonFeignClient;
import com.mohit.service.clients.UserFeingClient;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserFeingClient userFeingClient;
    @Autowired
    private SalonFeignClient salonFeignClient;

    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest reviewRequest ,@RequestHeader("Authorization") String jwt) throws Exception{

        UserDTO userDTO=userFeingClient.getUserByProfile(jwt).getBody();
        SalonDTO salonDTO=salonFeignClient.getSalonByOwnerId(jwt).getBody();
        Review review=reviewService.createReview(reviewRequest,userDTO,salonDTO);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long salonId){
        List<Review> reviews=reviewService.getReviewsBySalonId(salonId);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId , @RequestBody ReviewRequest reviewRequest,@RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO=userFeingClient.getUserByProfile(jwt).getBody();
        return ResponseEntity.ok(reviewService.updateReview(reviewId, reviewRequest, userDTO));
    }


    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId ,@RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO=userFeingClient.getUserByProfile(jwt).getBody();
        reviewService.deleteReview(reviewId, userDTO.getId());
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Review deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
        
    

    
}
