package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.Agent;
import com.purnabhu.sales.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Integer> {
    Optional<Agent> findByAgentName(String agentName);

    Boolean existsByAgentName(String agentName);

    Boolean existsByAgentEmailId(String email);
}
