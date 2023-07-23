package com.purnabhu.sales.services;

import com.purnabhu.sales.entities.Lead;
import com.purnabhu.sales.repository.LeadRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService{
    private static final Logger logger = LogManager.getLogger(LeadServiceImpl.class);
    @Autowired
    LeadRepository leadRepository;
    @Override
    public Lead createLead(Lead lead) {
        logger.info("Creating Lead...");
        return leadRepository.save(lead);
    }

    @Override
    public Lead updateLead(Lead lead) {
        return leadRepository.save(lead);
    }

    @Override
    public Lead searchLead(Integer leadId, String clientName) {
        return leadRepository.findByLeadIdOrClientName(leadId, clientName);
    }

    @Override
    public List<Lead> getAllLead() {
        logger.info("Fetching all Leads...");
        return leadRepository.findAll();
    }

    @Override
    public boolean existsByClientName(String clientName) {
        return leadRepository.existsByClientName(clientName);
    }

    @Override
    public boolean existsByClientEmailId(String userEmailId) {
        return leadRepository.existsByClientEmailId(userEmailId);
    }

    @Override
    public void deleteLead(Integer leadId) {
        leadRepository.deleteById(leadId);
    }

}
