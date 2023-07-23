package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    Optional<Client> findByClientName(String clientName);

    Boolean existsByClientName(String clientName);

    Boolean existsByClientEmailId(String email);

    Client findByClientIdOrClientName(Integer clientId, String clientName);
}
