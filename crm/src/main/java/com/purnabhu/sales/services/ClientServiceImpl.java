package com.purnabhu.sales.services;

import com.purnabhu.sales.entities.Client;
import com.purnabhu.sales.repository.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{
    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);
    @Autowired
    ClientRepository clientRepository;
    @Override
    public Client createClient(Client client) {
        logger.info("Creating Client...");
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> searchClient(String clientName) {
        return clientRepository.findByClientName(clientName);
    }

    @Override
    public List<Client> getAllClient() {
        logger.info("Fetching all users...");
        return clientRepository.findAll();
    }

    @Override
    public boolean existsByClientName(String clientName) {
        return clientRepository.existsByClientName(clientName);
    }

    @Override
    public boolean existsByClientEmailId(String clientEmailId) {
        return clientRepository.existsByClientEmailId(clientEmailId);
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }
}
