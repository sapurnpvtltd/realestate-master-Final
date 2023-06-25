package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  propertyRepository extends JpaRepository<property,Integer> {

    public property findBypropTitle(String projectName);

}
