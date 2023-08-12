package com.purnabhu.sales.controllers;

import com.purnabhu.sales.entities.Agent;
import com.purnabhu.sales.entities.Client;
import com.purnabhu.sales.response.ResponseEntityObject;
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
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = LogManager.getLogger(ClientController.class);
    @Autowired
    ClientService clientService;

    @Autowired
    ResponseEntityObject responseEntityObject;

    @PostMapping("/addClient")
    private ResponseEntity<Object>  createClient(@Valid @RequestBody Client client){
        if (clientService.existsByClientName(client.getClientName())) {
            return responseEntityObject.generateResponse("Error: Client is already exist!",HttpStatus.NOT_ACCEPTABLE,"");
        }

        if (clientService.existsByClientEmailId(client.getClientEmailId())) {
            return responseEntityObject.generateResponse("Error: Email is already in use!",HttpStatus.NOT_ACCEPTABLE,"");
        }
        logger.info("Client creation start....");
        Client createdClient = null;
        try {
            createdClient = clientService.createClient(client);
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("Client created successfully",HttpStatus.OK,createdClient);
    }

    @PutMapping("/updateClient")
    private ResponseEntity<Object>  updateClient(@Valid @RequestBody Client client){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (clientService.existsByClientName(client.getClientName())) {
            return responseEntityObject.generateResponse("Error: Clinet Name is already exist!", HttpStatus.NOT_ACCEPTABLE, "");
        }

        if (clientService.existsByClientEmailId(client.getClientEmailId())) {
            return responseEntityObject.generateResponse("Error: Email is already in use!", HttpStatus.NOT_ACCEPTABLE, "");
        }
        logger.info("Client updation start....");
        Client createdClient = null;
        try{
            createdClient = clientService.updateClient(client);
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("User updated successfully",HttpStatus.OK,createdClient);
    }

    @GetMapping("/searchClient/{clientName}")
    private ResponseEntity<Object> searchClient(@PathVariable String clientName){
         logger.info("Search Clinet by name and id....");
        Optional<Client> searchClient = null;
        try{
            searchClient = clientService.searchClient(clientName);
            if(searchClient.isEmpty())
                return responseEntityObject.generateResponse("Client Not Found",HttpStatus.OK,"");
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("Clients Search successfully",HttpStatus.OK,searchClient);
    }
    @GetMapping("/getAllClients")
    private ResponseEntity<Object> getAllClients(){
        logger.info("Fetch All Clients....");
        List<Client> clientList = null;
        try {
            clientList = clientService.getAllClient();
            if(clientList.isEmpty())
                return responseEntityObject.generateResponse("Clients Not Available",HttpStatus.OK,"");
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("All Clients",HttpStatus.OK,clientList);
    }

    @PostMapping("/deleteClient/{clientId}")
    private ResponseEntity<Object> deleteClient(@PathVariable Integer clientId){
        logger.info("Delete client....");
        try{
            clientService.deleteClient(clientId);
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("Client deleted successfully",HttpStatus.OK,"");
    }
}
