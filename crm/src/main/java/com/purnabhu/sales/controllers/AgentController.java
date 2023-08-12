package com.purnabhu.sales.controllers;

import com.purnabhu.sales.entities.Agent;
import com.purnabhu.sales.entities.Client;
import com.purnabhu.sales.entities.User;
import com.purnabhu.sales.response.ResponseEntityObject;
import com.purnabhu.sales.services.AgentService;
import com.purnabhu.sales.services.ClientService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/agent")
public class AgentController {

    private static final Logger logger = LogManager.getLogger(AgentController.class);
    @Autowired
    AgentService agentService;

    @Autowired
    ResponseEntityObject responseEntityObject;

    @PostMapping("/addAgent")
    private ResponseEntity  createAgent(@Valid @RequestBody Agent agent){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (agentService.existsByAgentName(agent.getAgentName())) {
            //responseEntityObject.setResponseMessage("Error: Agent is already taken!");
            return responseEntityObject.generateResponse("Error: Agent is already exist!",HttpStatus.NOT_ACCEPTABLE,"");
        }

        if (agentService.existsByAgentEmailId(agent.getAgentEmailId())) {
            //responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject.generateResponse("Error: Email is already in use!",HttpStatus.NOT_ACCEPTABLE,"");
        }
        logger.info("Agent creation start....");
        Agent createdAgent = null;
        try {
            createdAgent = agentService.createAgent(agent);
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("Agent created successfully",HttpStatus.OK,createdAgent);
    }

    @PutMapping("/updateAgent")
    private ResponseEntity  updateAgent(@Valid @RequestBody Agent agent) {
        if (agentService.existsByAgentName(agent.getAgentName())) {
            return responseEntityObject.generateResponse("Error: Agent Name is already exist!", HttpStatus.NOT_ACCEPTABLE, "");
        }

        if (agentService.existsByAgentEmailId(agent.getAgentEmailId())) {
            return responseEntityObject.generateResponse("Error: Email is already in use!", HttpStatus.NOT_ACCEPTABLE, "");
        }
        logger.info("Agent updation start....");
        Agent createdAgent = null;
        try{
            createdAgent = agentService.updateAgent(agent);
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("Agent updated successfully",HttpStatus.OK,createdAgent);
    }

    @GetMapping("/searchAgent/{agentName}")
    private ResponseEntity<Object> searchAgent(@PathVariable String agentName){
        logger.info("Search Agent by name and id....");
        Optional<Agent> searchAgent = null;
        try{
          searchAgent = agentService.searchAgent(agentName);
          if(searchAgent.isEmpty())
              return responseEntityObject.generateResponse("Agent Not Found",HttpStatus.OK,"");
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("Agent Searched successfully",HttpStatus.OK,searchAgent);
    }
    @GetMapping("/getAllAgents")
    private ResponseEntity<Object> getAllAgents(){
        logger.info("Fetch All Agents....");
        List<Agent> agentList = null;
        try {
            agentList = agentService.getAllAgent();
            if(agentList.isEmpty())
                return responseEntityObject.generateResponse("Agents Not Available",HttpStatus.OK,"");
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("All Agents",HttpStatus.OK,agentList);
    }

    @PostMapping("/deleteAgent/{agentId}")
    private ResponseEntity<Object> deleteAgent(@PathVariable Integer agentId){
        logger.info("Delete Agent....");
        try {
            agentService.deleteAgent(agentId);
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("Agent deleted successfully",HttpStatus.OK,"");
    }
}
