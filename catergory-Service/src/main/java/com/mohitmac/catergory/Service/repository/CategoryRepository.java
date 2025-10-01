package com.mohitmac.catergory.Service.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohitmac.catergory.Service.model.Catergory;

@Repository
public interface CategoryRepository extends JpaRepository<Catergory,Long> {
    Set<Catergory> findBySalonId(Long id);
    
}
