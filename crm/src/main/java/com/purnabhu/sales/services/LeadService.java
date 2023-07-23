package com.purnabhu.sales.services;

import com.purnabhu.sales.entities.Lead;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LeadService {
    public Lead createLead(Lead lead);
    public Lead updateLead(Lead lead);
    public Lead searchLead(Integer leadId, String clientName);
    public List<Lead> getAllLead();
    public boolean existsByClientName(String clientName);
    public boolean existsByClientEmailId(String clientEmailId);

    public void deleteLead(Integer leadId);

}
