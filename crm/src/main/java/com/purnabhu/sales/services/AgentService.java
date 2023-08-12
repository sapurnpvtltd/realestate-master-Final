package com.purnabhu.sales.services;

import com.purnabhu.sales.entities.Agent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AgentService {
    public Agent createAgent(Agent agent);
    public Agent updateAgent(Agent agent);
    public Optional<Agent> searchAgent(String agentName);
    public List<Agent> getAllAgent();
    public boolean existsByAgentName(String agentName);
    public boolean existsByAgentEmailId(String agentEmailId);
    public void deleteAgent(Integer agentId);
}
