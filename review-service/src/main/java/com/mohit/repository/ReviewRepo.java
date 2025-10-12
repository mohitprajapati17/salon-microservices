package com.mohit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findBySalonId(Long salonId);
    List<Review> findByUserId(Long userId);
}
