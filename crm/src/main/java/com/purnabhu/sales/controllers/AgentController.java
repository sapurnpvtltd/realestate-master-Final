package com.purnabhu.sales.controllers;

import com.purnabhu.sales.entities.Agent;
import com.purnabhu.sales.entities.Client;
import com.purnabhu.sales.response.ResponseEntityObject;
import com.purnabhu.sales.services.AgentService;
import com.purnabhu.sales.services.ClientService;
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
@RequestMapping("/agent")
public class AgentController {

    private static final Logger logger = LogManager.getLogger(AgentController.class);
    @Autowired
    AgentService agentService;

    @PostMapping("/addAgent")
    private ResponseEntityObject  createAgent(@RequestBody Agent agent){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (agentService.existsByAgentName(agent.getAgentName())) {
            responseEntityObject.setResponseMessage("Error: Agent is already taken!");
            return responseEntityObject;
        }

        if (agentService.existsByAgentEmailId(agent.getAgentEmailId())) {
            responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject;
        }
        logger.info("Agent creation start....");
        Agent createdAgent = agentService.createAgent(agent);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("Agent created successfully");
        return responseEntityObject;
    }

    @PutMapping("/updateAgent")
    private ResponseEntityObject  updateAgent(@RequestBody Agent agent){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (agentService.existsByAgentName(agent.getAgentName())) {
            responseEntityObject.setResponseMessage("Error: Agent Name is already taken!");
            return responseEntityObject;
        }

        if (agentService.existsByAgentEmailId(agent.getAgentEmailId())) {
            responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject;
        }
        logger.info("Agent updation start....");
        Agent createdAgent = agentService.updateAgent(agent);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("Agent updated successfully");
        return responseEntityObject;
    }

    @GetMapping(value = {"/searchAgent/{agentId}/{name}","/searchClient/{searchVal}"})
    private ResponseEntity<Agent> searchAgent(@PathVariable Map<String, String> agentSearch){
        Integer agentId = Integer.valueOf(agentSearch.get("clientId"));
        String agentName = agentSearch.get("name");
        if(agentId==null && agentName == null) {
            agentId = Integer.valueOf(agentSearch.get("searchVal"));
            agentName = agentSearch.get("searchVal");
        }
        logger.info("Search Agent by name and id....");
        Agent searchAgent = agentService.searchAgent(agentId, agentName);
        return new ResponseEntity<Agent>(searchAgent,HttpStatus.OK);
    }
    @GetMapping("/getAllAgents")
    private ResponseEntity<List<Agent>> getAllAgents(){
        logger.info("Fetch All Agents....");
        List<Agent> agentList = agentService.getAllAgent();
        return new ResponseEntity<List<Agent>>(agentList,HttpStatus.OK);
    }

    @PostMapping("/deleteAgent/{agentId}")
    private ResponseEntityObject deleteAgent(@PathVariable Integer agentId){
        logger.info("Delete Agent....");
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        agentService.deleteAgent(agentId);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("Agent deleted successfully");
        return responseEntityObject;
    }
}
