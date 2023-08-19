package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.PropertyImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyImageRepository extends JpaRepository<PropertyImages, Long> {

        Optional<PropertyImages> findByName(String fileName);
        }