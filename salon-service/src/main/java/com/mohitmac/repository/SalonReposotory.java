package com.mohitmac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mohitmac.model.Salon;

@Repository
public interface SalonReposotory extends JpaRepository<Salon, Long> {
    Salon findByOwnerId(Long ownerId);

    @Query("SELECT s FROM Salon s WHERE " +
    "(LOWER(s.city) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER(s.address) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
    "s.active = true")
    public List<Salon> findByCity(@Param("keyword") String keyword);
    
}
