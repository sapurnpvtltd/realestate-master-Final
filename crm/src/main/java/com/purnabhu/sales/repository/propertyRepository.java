package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  propertyRepository extends JpaRepository<property,Integer> {

    public List<property> findBypropTitle(String projectName);

}
