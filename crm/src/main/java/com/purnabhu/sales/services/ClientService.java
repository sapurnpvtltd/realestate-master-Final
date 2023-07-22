package com.purnabhu.sales.services;

import com.purnabhu.sales.entities.Client;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientService {
    public Client createClient(Client client);
    public Client updateClient(Client client);
    public Client searchClient(String clientId, String clientName);
    public List<Client> getAllClient();
    public boolean existsByClientName(String clientName);
    public boolean existsByClientEmailId(String clientEmailId);
    public void deleteClient(String clientId);
}
