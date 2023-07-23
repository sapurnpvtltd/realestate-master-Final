package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source,Integer> {
}
