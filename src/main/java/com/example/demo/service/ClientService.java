package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllActiveClients() {
        return clientRepository.findByIsActiveTrue();
    }

    public void deactivateClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setIsActive(false);
        clientRepository.save(client);
    }
}
