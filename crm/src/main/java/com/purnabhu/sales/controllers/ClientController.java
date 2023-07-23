package com.purnabhu.sales.controllers;

import com.purnabhu.sales.entities.Client;
import com.purnabhu.sales.response.ResponseEntityObject;
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
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = LogManager.getLogger(ClientController.class);
    @Autowired
    ClientService clientService;

    @PostMapping("/addClient")
    private ResponseEntityObject  createClient(@RequestBody Client client){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (clientService.existsByClientName(client.getClientName())) {
            responseEntityObject.setResponseMessage("Error: Client is already taken!");
            return responseEntityObject;
        }

        if (clientService.existsByClientEmailId(client.getClientEmailId())) {
            responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject;
        }
        logger.info("Client creation start....");
        Client createdClient = clientService.createClient(client);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("Client created successfully");
        return responseEntityObject;
    }

    @PutMapping("/updateClient")
    private ResponseEntityObject  updateClient(@RequestBody Client client){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (clientService.existsByClientName(client.getClientName())) {
            responseEntityObject.setResponseMessage("Error: Clinet Name is already taken!");
            return responseEntityObject;
        }

        if (clientService.existsByClientEmailId(client.getClientEmailId())) {
            responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject;
        }
        logger.info("Client updation start....");
        Client createdClient = clientService.updateClient(client);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("User updated successfully");
        return responseEntityObject;
    }

    @GetMapping(value = {"/searchClient/{clientId}/{name}", "/searchClient/{searchVal}"})
    private ResponseEntity<Client> searchClient(@PathVariable Map<String, String> clientSearch){
        Integer clientId = Integer.valueOf(clientSearch.get("clientId"));
        String clientName = clientSearch.get("name");
        if(clientId==null && clientName == null) {
            clientId = Integer.valueOf(clientSearch.get("searchVal"));
            clientName = clientSearch.get("searchVal");
        }
        logger.info("Search Clinet by name and id....");
        Client searchClient = clientService.searchClient(clientId, clientName);
        return new ResponseEntity<Client>(searchClient,HttpStatus.OK);
    }
    @GetMapping("/getAllClients")
    private ResponseEntity<List<Client>> getAllClients(){
        logger.info("Fetch All Clients....");
        List<Client> clientList = clientService.getAllClient();
        return new ResponseEntity<List<Client>>(clientList,HttpStatus.OK);
    }

    @PostMapping("/deleteClient/{clientId}")
    private ResponseEntityObject deleteClient(@PathVariable Integer clientId){
        logger.info("Delete client....");
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        clientService.deleteClient(clientId);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("Client deleted successfully");
        return responseEntityObject;
    }

}
