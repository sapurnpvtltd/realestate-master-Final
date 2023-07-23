package com.purnabhu.sales.services;

import com.purnabhu.sales.entities.Agent;
import com.purnabhu.sales.repository.AgentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService{
    private static final Logger logger = LogManager.getLogger(AgentServiceImpl.class);
    @Autowired
    AgentRepository agentRepository;
    @Override
    public Agent createAgent(Agent agent) {
        logger.info("Creating user...");
        return agentRepository.save(agent);
    }

    @Override
    public Agent updateAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Agent searchAgent(Integer agentId, String agentName) {
        return agentRepository.findByAgentIdOrAgentName(agentId, agentName);
    }

    @Override
    public List<Agent> getAllAgent() {
        logger.info("Fetching all users...");
        return agentRepository.findAll();
    }

    @Override
    public boolean existsByAgentName(String userName) {
        return agentRepository.existsByAgentName(userName);
    }

    @Override
    public boolean existsByAgentEmailId(String userEmailId) {
        return agentRepository.existsByAgentEmailId(userEmailId);
    }

    @Override
    public void deleteAgent(Integer agentId) {
        agentRepository.deleteById(agentId);
    }

}
