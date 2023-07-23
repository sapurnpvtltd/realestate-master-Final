package com.purnabhu.sales.controllers;

import com.purnabhu.sales.entities.Lead;
import com.purnabhu.sales.response.ResponseEntityObject;
import com.purnabhu.sales.services.LeadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/lead")
public class LeadController {

    private static final Logger logger = LogManager.getLogger(LeadController.class);
    @Autowired
    LeadService leadService;

    @PostMapping("/addLead")
    private ResponseEntityObject  createLead(@RequestBody Lead lead){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (leadService.existsByClientName(lead.getClientName())) {
            responseEntityObject.setResponseMessage("Error: Client is already taken!");
            return responseEntityObject;
        }

        if (leadService.existsByClientEmailId(lead.getClientEmailId())) {
            responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject;
        }
        logger.info("Client creation start....");
        Lead createdLead = leadService.createLead(lead);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("Lead created successfully");
        return responseEntityObject;
    }

    @PutMapping("/updateLead")
    private ResponseEntityObject  updateLead(@RequestBody Lead lead){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (leadService.existsByClientName(lead.getClientName())) {
            responseEntityObject.setResponseMessage("Error: Lead Name is already taken!");
            return responseEntityObject;
        }

        if (leadService.existsByClientEmailId(lead.getClientEmailId())) {
            responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject;
        }
        logger.info("Lead updation start....");
        Lead createdLead = leadService.updateLead(lead);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("Lead updated successfully");
        return responseEntityObject;
    }

    @GetMapping(value = {"/searchLead/{leadId}/{name}", "/searchUser/{searchVal}"})
    private ResponseEntity<Lead> searchLead(@PathVariable Map<String, String> leadSearch){
        Integer leadId = Integer.valueOf(leadSearch.get("leadId"));
        String clientName = leadSearch.get("name");
        if(leadId==null && clientName == null) {
            leadId = Integer.valueOf(leadSearch.get("searchVal"));
            clientName = leadSearch.get("searchVal");
        }
        logger.info("Search Lead by name and id....");
        Lead searchLead = leadService.searchLead(leadId, clientName);
        return new ResponseEntity<Lead>(searchLead,HttpStatus.OK);
    }
    @GetMapping("/getAllLeads")
    private ResponseEntity<List<Lead>> getAllLeads(){
        logger.info("Fetch All Leads....");
        List<Lead> leadList = leadService.getAllLead();
        return new ResponseEntity<List<Lead>>(leadList,HttpStatus.OK);
    }
    @PostMapping("/deleteLead/{leadId}")
    private ResponseEntityObject deleteUser(@PathVariable Integer leadId){
        logger.info("Delete Lead....");
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        leadService.deleteLead(leadId);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("Lead deleted successfully");
        return responseEntityObject;
    }
}
