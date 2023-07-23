package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.Amenities;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities,Integer> {



}
