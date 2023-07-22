package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead,String> {
    Optional<Lead> findByClientName(String clientName);

    Boolean existsByClientName(String clientName);

    Boolean existsByClientEmailId(String email);

    Lead findByLeadIdOrClientName(String leadId, String clientName);

}
